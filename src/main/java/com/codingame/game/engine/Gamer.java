package com.codingame.game.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.codingame.game.engine.Constants.INITIAL_HAND_SIZE;

/**
 * Created by aCat on 2018-03-24.
 */
public class Gamer
{
  int id;
  //public boolean isSecond;
  public ArrayList<Card> hand;
  public ArrayList<Card> deck;
  public ArrayList<CreatureOnBoard> board;
  public ArrayList<CreatureOnBoard> graveyard;
  public int health;
  public int maxMana;
  public int currentMana;
  public int nextTurnDraw;

  public ArrayList<Integer> runes = new ArrayList<Integer>() {{ add(5);add(10);add(15);add(20);add(25); }};
  public ArrayList<Action> performedActions;
  public int handLimit;

  // todo rest



  public Gamer(int id, ArrayList<Card> deck)
  {
    this.id = id;
    this.hand = new ArrayList<>();
    this.deck = new ArrayList<>(deck);
    this.board = new ArrayList<>();
    this.graveyard = new ArrayList<>();
    this.performedActions = new ArrayList<>();
    this.health = Constants.INITIAL_HEALTH;
    this.maxMana = 0;
    this.currentMana = 0;
    this.nextTurnDraw = 1;

    handLimit = Constants.MAX_CARDS_IN_HAND + (id==0 ? 0 : Constants.SECOND_PLAYER_MAX_CARD_BONUS);
    DrawCards(INITIAL_HAND_SIZE + (id==0 ? 0 : Constants.SECOND_PLAYER_CARD_BONUS), 0);
  }

  private void suicideRunes()
  {
    if (!runes.isEmpty()) // first rune gone
    {
      Integer r = runes.remove(runes.size() - 1);
      health = r;
    }
    else // final run gone - suicide
    {
      health = 0;
    }
  }

  public void DrawCards(int n, int playerturn)
  {
    for (int i=0; i<n; i++)
    {
      if (deck.isEmpty() || playerturn>=Constants.PLAYER_TURNLIMIT)
      {
        suicideRunes();
        continue;
      }

      if (hand.size()==handLimit)
      {
        break; // additional draws are simply wasted
      }

      Card c = deck.remove(0);
      hand.add(c);
    }

  }


  public void ModifyHealth(int mod)
  {
    health += mod;

    if (mod >= 0)
      return;

    for (int r=runes.size()-1; r >=0; r--) // rune checking;
    {
      if (health <= runes.get(r))
      {
        nextTurnDraw += 1;
        runes.remove(r);
      }
    }
  }

  public int nextRune()
  {
    if (runes.isEmpty()) return 0;
    return runes.get(runes.size()-1);
  }

  public void removeFromBoard(int creatureIndex) {
      graveyard.add(board.remove(creatureIndex));
  }

  public String toDescriptiveString(boolean reverse)
  {
    String line1 = String.format("[Player %d] Health: %d %s     Mana: %d/%d", id, health, runes, currentMana, maxMana);
    String line2 = String.format("Cards in hand: %d   In deck: %d   Next turn draw: %d", hand.size(), deck.size(), nextTurnDraw);

    ArrayList<String> inhand = new ArrayList<>();
    inhand.add("Hand:");
    for (Card c: hand)
      inhand.add((c.cost <= this.currentMana ? " * " : "   ") + c.toDescriptiveString());

    ArrayList<String> onboard = new ArrayList<>();
    onboard.add("Board:");
    for (CreatureOnBoard c: board)
      onboard.add((c.canAttack ? " * " : "   ") + c.toDescriptiveString());

    ArrayList<String> description = new ArrayList<>();
    description.add(line1);
    description.add(line2);
    description.add(String.join("\n",inhand));
    description.add(String.join("\n",onboard));
    if (reverse)
      Collections.reverse(description);

    return String.join("\n", description);
  }

  // todo
  public String toString()
  {
    return super.toString();
  }

  public String getPlayerInput() {
	  StringBuilder s = new StringBuilder();
	  s.append(health).append(" ");
	  s.append(maxMana).append(" ");
	  s.append(deck.size()).append(" ");
	  s.append(nextRune());
	  return s.toString();
  }
}
