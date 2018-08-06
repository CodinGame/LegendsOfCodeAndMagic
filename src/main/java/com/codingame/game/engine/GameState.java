package com.codingame.game.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by aCat on 2018-03-20.
 */
public class GameState
{
  public int turn;
  public int winner;
  public int currentPlayer;
  public Gamer[] players;
  public HashMap<Integer, Card> cardIdMap;

  public GameState(DraftPhase draft)
  {
    
    assert( draft.decks[0].size()==Constants.CARDS_IN_DECK);
    assert( draft.decks[1].size()==Constants.CARDS_IN_DECK);

    turn = -1;
    winner = -1;
    currentPlayer = 1;
    players = new Gamer[] {new Gamer(0, draft.decks[0]), new Gamer(1, draft.decks[1])};

    cardIdMap = new HashMap<>();
    for (int i=0; i < 2; i++)
      for (Card c: draft.decks[i])
        cardIdMap.put(c.id, c);


  }



  public List<Action> computeLegalActions()
  {
    List<Action> legals = new ArrayList<>();
    legals.addAll(computeLegalSummons());
    legals.addAll(computeLegalAttacks());
    legals.addAll(computeLegalItems());
    legals.add(Action.newPass());
    return legals;
  }

  public List<Action> computeLegalSummons()
  {
    Gamer player = players[currentPlayer];
    ArrayList<Action> actions = new ArrayList<>();

    if (player.board.size() == Constants.MAX_CREATURES_IN_LINE)
      return actions;

    for (Card c:player.hand)
    {
      if (c.type != Card.Type.CREATURE || c.cost > player.currentMana)
        continue;
      actions.add(Action.newSummon(c.id));
    }

    return actions;
  }

  private List<Integer> computeLegalTargets()
  {
    Gamer enemyPlayer = players[1 - currentPlayer];

    ArrayList<Integer> targets = new ArrayList<>();

    for (CreatureOnBoard c : enemyPlayer.board) // First priority - guards
      if (c.keywords.hasGuard)
        targets.add(c.id);

    if (targets.isEmpty()) // if no guards we can freely attack any creature plus face
    {
      targets.add(-1);
      for (CreatureOnBoard c : enemyPlayer.board)
        targets.add(c.id);
    }

    return targets;
  }

  public List<Action> computeLegalAttacks()
  {
    Gamer player = players[currentPlayer];

    List<Integer> targets = computeLegalTargets();

    ArrayList<Action> actions = new ArrayList<>();

    for (CreatureOnBoard c:player.board)
    {
      if (!c.canAttack)
        continue;
      for (Integer tid: targets)
        actions.add(Action.newAttack(c.id, tid));
    }

    return actions;
  }

  public List<Action> computeLegalItems()
  {
    Gamer player = players[currentPlayer];

    ArrayList<Action> actions = new ArrayList<>();

    for (Card c:player.hand)
    {
      if (c.type == Card.Type.CREATURE || c.cost > player.currentMana)
        continue;

      if (c.type == Card.Type.ITEM_GREEN) // on friendly creatures
      {
        for (CreatureOnBoard cb : player.board)
          actions.add(Action.newUse(c.id, cb.id));
      }
      else // red or blue item: on enemy creatures
      {
        for (CreatureOnBoard cb : players[1-currentPlayer].board)
          actions.add(Action.newUse(c.id, cb.id));
        if (c.type == Card.Type.ITEM_BLUE) // blue also on the player
          actions.add(Action.newUse(c.id, -1));
      }
    }

    return actions;
  }



  public void AdvanceState()
  {
    CheckWinCondition();

    for (CreatureOnBoard c : players[currentPlayer].board) {
      c.canAttack = false;
      c.hasAttacked = false;
    }

    currentPlayer = 1 - currentPlayer;
    Gamer player = players[currentPlayer];
    player.performedActions.clear();
    turn++;


    if (player.maxMana < Constants.MAX_MANA)
    {
      player.maxMana += 1;
    }

    player.currentMana = player.maxMana;

    for (CreatureOnBoard c : player.board)
    {
      c.canAttack = true; // mark ALL creatures as ready to charge
      //if (c.keywords.hasRegenerate && c.defense < c.lastTurnDefense)
        //c.defense = c.lastTurnDefense;
      c.lastTurnDefense = c.defense; // for all creatures (just in case)
    }

    player.DrawCards(player.nextTurnDraw, turn/2);
    player.nextTurnDraw = 1;
    CheckWinCondition();
  }

  public void AdvanceState(Action action)  // ASSUMING THE ACTION IS LEGAL !
  {
    if (action.type == Action.Type.SUMMON) // SUMMON [id]
    {
      Card c = cardIdMap.get(action.arg1);

      players[currentPlayer].hand.remove(c);
      players[currentPlayer].currentMana -= c.cost;
      CreatureOnBoard creature = new CreatureOnBoard(c);
      players[currentPlayer].board.add(creature);

      players[currentPlayer].ModifyHealth(c.myHealthChange);
      players[1-currentPlayer].ModifyHealth(c.oppHealthChange);
      players[currentPlayer].nextTurnDraw += c.cardDraw;
      
      action.result = new ActionResult(creature, null, false, false, c.myHealthChange, c.oppHealthChange);
    }
    else if (action.type == Action.Type.ATTACK) // ATTACK [id1] [id2]
    {
      int indexatt = -1;
      for (int i=0; i < players[currentPlayer].board.size(); i++)
        if (players[currentPlayer].board.get(i).id==action.arg1)
          indexatt = i;
      CreatureOnBoard att = players[currentPlayer].board.get(indexatt);

      int indexdef = -1;
      CreatureOnBoard def;
      ActionResult result = null;

      if (action.arg2 == -1) // attacking player
      {
        result = ResolveAttack(att);
      }
      else
      {
        for (int i=0; i < players[1-currentPlayer].board.size(); i++)
          if (players[1-currentPlayer].board.get(i).id==action.arg2)
            indexdef = i;
        def = players[1-currentPlayer].board.get(indexdef);

        result = ResolveAttack(att, def);

        if (result.defenderDied) {
          players[1-currentPlayer].removeFromBoard(indexdef);

        }
        else
          players[1-currentPlayer].board.set(indexdef, result.defender);
      }

      if (result.attackerDied)
        players[currentPlayer].removeFromBoard(indexatt);
      else
        players[currentPlayer].board.set(indexatt, result.attacker);

      players[currentPlayer].ModifyHealth(result.attackerHealthChange);
      players[1-currentPlayer].ModifyHealth(result.defenderHealthChange);
      action.result = result;
    }
    else if (action.type == Action.Type.USE) // USE [id1] [id2]
    {
      Card item = cardIdMap.get(action.arg1);

      players[currentPlayer].hand.remove(item);
      players[currentPlayer].currentMana -= item.cost;

      if (item.type == Card.Type.ITEM_GREEN) // here we assume that green cards never remove friendly creatures!
      {
        int indextarg = -1;
        for (int i=0; i < players[currentPlayer].board.size(); i++)
          if (players[currentPlayer].board.get(i).id==action.arg2)
            indextarg = i;
        CreatureOnBoard targ = players[currentPlayer].board.get(indextarg);

        ActionResult result = ResolveUse(item, targ);

        players[currentPlayer].board.set(indextarg, result.defender);

        players[currentPlayer].ModifyHealth(result.attackerHealthChange);
        players[1-currentPlayer].ModifyHealth(result.defenderHealthChange);
        players[currentPlayer].nextTurnDraw += item.cardDraw;
        action.result = result;
      }
      else // red and blue cards
      {
        int indextarg = -1;
        ActionResult result = null;

        if (action.arg2 == -1) // using on player
        {
          result = ResolveUse(item);
        }
        else // using on creature
        {
          for (int i=0; i < players[1-currentPlayer].board.size(); i++)
            if (players[1-currentPlayer].board.get(i).id==action.arg2)
              indextarg = i;
          CreatureOnBoard targ = players[1-currentPlayer].board.get(indextarg);

          result = ResolveUse(item, targ);

          if (result.defenderDied)
            players[1-currentPlayer].removeFromBoard(indextarg);
          else
            players[1-currentPlayer].board.set(indextarg, result.defender);
        }

        players[currentPlayer].ModifyHealth(result.attackerHealthChange);
        players[1-currentPlayer].ModifyHealth(result.defenderHealthChange);
        players[currentPlayer].nextTurnDraw += item.cardDraw;
        action.result = result;
      }
    }

    players[currentPlayer].performedActions.add(action);
    CheckWinCondition();
  }


  public void CheckWinCondition()
  {
    if (players[1-currentPlayer].health <= 0) // first proper win
      winner = currentPlayer;
    else if (players[currentPlayer].health <= 0) // second self-kill
      winner = 1-currentPlayer;
  }

  // when creature attacks creatures // run it ONLY on legal actions
  public static ActionResult ResolveAttack(CreatureOnBoard attacker, CreatureOnBoard defender)
  {
    if (!attacker.canAttack)
      return new ActionResult(false);

    CreatureOnBoard attackerAfter = new CreatureOnBoard(attacker);
    CreatureOnBoard defenderAfter = new CreatureOnBoard(defender);

    attackerAfter.canAttack = false;
    attackerAfter.hasAttacked = true;

    if (defender.keywords.hasWard) defenderAfter.keywords.hasWard = attacker.attack == 0;
    if (attacker.keywords.hasWard) attackerAfter.keywords.hasWard = defender.attack == 0;

    int damageGiven = defender.keywords.hasWard ? 0 : attacker.attack;
    int damageTaken = attacker.keywords.hasWard ? 0 : defender.attack;
    int healthGain = 0;
    int healthTaken = 0;

    // attacking
    if (damageGiven >= defender.defense) defenderAfter = null;
    if (attacker.keywords.hasBreakthrough && defenderAfter==null) healthTaken = defender.defense - damageGiven;
    if (attacker.keywords.hasLethal && damageGiven > 0) defenderAfter = null;
    if (attacker.keywords.hasDrain && damageGiven > 0) healthGain = attacker.attack;
    if (defenderAfter != null) defenderAfter.defense -= damageGiven;

    // defending
    if (damageTaken >= attacker.defense) attackerAfter = null;
    if (defender.keywords.hasLethal && damageTaken > 0) attackerAfter = null;
    if (attackerAfter != null) attackerAfter.defense -= damageTaken;
    ActionResult result = new ActionResult(attackerAfter == null ? attacker : attackerAfter, defenderAfter == null ? defender : defenderAfter, attackerAfter == null, defenderAfter == null, healthGain, healthTaken);
    result.attackerDefenseChange = -damageTaken;
    result.defenderDefenseChange = -damageGiven;
    return result;
  }

  // when creature attacks player // run it ONLY on legal actions
  public static ActionResult ResolveAttack(CreatureOnBoard attacker)
  {
    if (!attacker.canAttack)
      return new ActionResult(false);

    CreatureOnBoard attackerAfter = new CreatureOnBoard(attacker);

    attackerAfter.canAttack = false;
    attackerAfter.hasAttacked = true;

    int healthGain = attacker.keywords.hasDrain ? attacker.attack : 0;
    int healthTaken = -attacker.attack;

    ActionResult result = new ActionResult(attackerAfter, null, healthGain, healthTaken);
    result.defenderDefenseChange = healthTaken;
    return result;
  }

  // when item is used on a creature // run it ONLY on legal actions
  public static ActionResult ResolveUse(Card item, CreatureOnBoard target)
  {
    CreatureOnBoard targetAfter = new CreatureOnBoard(target);

    if (item.type==Card.Type.ITEM_GREEN) // add keywords
    {
      targetAfter.keywords.hasCharge       = target.keywords.hasCharge       || item.keywords.hasCharge;
      if (item.keywords.hasCharge) 
          targetAfter.canAttack = !targetAfter.hasAttacked; // No Swift Strike hack
      targetAfter.keywords.hasBreakthrough = target.keywords.hasBreakthrough || item.keywords.hasBreakthrough;
      targetAfter.keywords.hasDrain        = target.keywords.hasDrain        || item.keywords.hasDrain;
      targetAfter.keywords.hasGuard        = target.keywords.hasGuard        || item.keywords.hasGuard;
      targetAfter.keywords.hasLethal       = target.keywords.hasLethal       || item.keywords.hasLethal;
      //targetAfter.keywords.hasRegenerate   = target.keywords.hasRegenerate   || item.keywords.hasRegenerate;
      targetAfter.keywords.hasWard         = target.keywords.hasWard         || item.keywords.hasWard;
    }
    else // Assumming ITEM_BLUE or ITEM_RED - remove keywords
    {
      targetAfter.keywords.hasCharge       = target.keywords.hasCharge       && !item.keywords.hasCharge;
      targetAfter.keywords.hasBreakthrough = target.keywords.hasBreakthrough && !item.keywords.hasBreakthrough;
      targetAfter.keywords.hasDrain        = target.keywords.hasDrain        && !item.keywords.hasDrain;
      targetAfter.keywords.hasGuard        = target.keywords.hasGuard        && !item.keywords.hasGuard;
      targetAfter.keywords.hasLethal       = target.keywords.hasLethal       && !item.keywords.hasLethal;
      //targetAfter.keywords.hasRegenerate   = target.keywords.hasRegenerate   && !item.keywords.hasRegenerate;
      targetAfter.keywords.hasWard         = target.keywords.hasWard         && !item.keywords.hasWard;
    }

    targetAfter.attack = Math.max(0, target.attack + item.attack);

    if (targetAfter.keywords.hasWard && item.defense < 0)
      targetAfter.keywords.hasWard = false;
    else
      targetAfter.defense += item.defense;
    if (targetAfter.defense <= 0) targetAfter = null;

    int itemgiverHealthChange = item.myHealthChange;
    int targetHealthChange = item.oppHealthChange;

    ActionResult result = new ActionResult(new CreatureOnBoard(item), targetAfter == null ? target : targetAfter, false, targetAfter == null, itemgiverHealthChange, targetHealthChange);
    result.defenderAttackChange  = item.attack;
    result.defenderDefenseChange = item.defense;
    return result;
  }

  // when item is used on a player // run it ONLY on legal actions
  public static ActionResult ResolveUse(Card item)
  {
    int itemgiverHealthChange = item.myHealthChange;
    int targetHealthChange = item.defense + item.oppHealthChange;

    return new ActionResult(null, null, itemgiverHealthChange, targetHealthChange);
  }

  // old method
  public String[] toStringLines()
  {
    ArrayList<String> lines = new ArrayList<>();

    Gamer player = players[currentPlayer];
    Gamer opponent = players[1-currentPlayer];

    lines.add(String.valueOf(turn));
    lines.add(String.format("%d %d %d %d %d %d %d", player.health, player.nextRune(), player.maxMana, player.nextTurnDraw, player.hand.size(), player.board.size(), player.deck.size()));
    for (Card c : player.hand)
      lines.add(c.toString());
    for (CreatureOnBoard b : player.board)
      lines.add(b.toString());
    lines.add(String.format("%d %d %d %d %d %d %d %d", opponent.health, opponent.nextRune(), opponent.maxMana, opponent.nextTurnDraw, opponent.hand.size(), opponent.board.size(), opponent.deck.size(), opponent.performedActions.size()));
    for (CreatureOnBoard b : opponent.board)
      lines.add(b.toString());
    for (Action a: opponent.performedActions)
      lines.add(cardIdMap.get(a.arg1).baseId + " " + a.toStringNoText());

    return lines.stream().toArray(String[]::new);
  }

  public String[] getPlayersInput() {
    ArrayList<String> lines = new ArrayList<>();

    Gamer player = players[currentPlayer];
    Gamer opponent = players[1-currentPlayer];
    lines.add(player.getPlayerInput());
    lines.add(opponent.getPlayerInput());

    return lines.stream().toArray(String[]::new);
  }

  public String[] getCardsInput() {
	  ArrayList<String> lines = new ArrayList<>();

    Gamer player = players[currentPlayer];
    Gamer opponent = players[1-currentPlayer];

    lines.add(String.valueOf(opponent.hand.size()));
    int cardCount = player.hand.size() + player.board.size() + opponent.board.size();
    lines.add(String.valueOf(cardCount));

    for (Card c : player.hand)
      lines.add(c.getAsInput());
    for (CreatureOnBoard b : player.board)
      lines.add(b.getAsInput(false));
    for (CreatureOnBoard b : opponent.board)
      lines.add(b.getAsInput(true));
    return lines.stream().toArray(String[]::new);
  }

  public String toString()
  {
    StringBuilder sb = new StringBuilder();

    for (String line: getPlayersInput())
      sb.append(line).append("\n");
    for (String line: getPlayersInput())
        sb.append(line).append("\n");

    return sb.toString();
  }
}
