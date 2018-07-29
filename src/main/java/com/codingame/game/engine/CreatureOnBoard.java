package com.codingame.game.engine;

import java.util.List;

import com.codingame.game.engine.Card.Type;

/**
 * Creature that is not a card anymore but it is placed on board.
 */
public class CreatureOnBoard
{
  public int id;
  public int baseId;
  public int attack;
  public int defense;
  public int cost;
  public int myHealthChange;
  public int oppHealthChange;
  public int cardDraw;
  public Keywords keywords;

  public boolean canAttack;
  public boolean hasAttacked;
  public int lastTurnDefense;
  
  public Card baseCard;

  public CreatureOnBoard (CreatureOnBoard creature)
  {
    this.id = creature.id;
    this.baseId = creature.baseId;
    this.cost = creature.cost;
    this.attack = creature.attack;
    this.defense = creature.defense;
    this.keywords = new Keywords(creature.keywords);
    this.lastTurnDefense = creature.lastTurnDefense;
    baseCard = creature.baseCard;
    this.canAttack = creature.canAttack;
    this.hasAttacked = creature.hasAttacked;
  }

  /**
   * @param data "id baseId attack defense keywords"
   */
  public CreatureOnBoard (String data)
  {
    String[] creature = data.split(" ");
    this.id = Integer.parseInt(creature[0]);
    this.baseId = Integer.parseInt(creature[1]);
    this.attack = Integer.parseInt(creature[2]);
    this.defense = Integer.parseInt(creature[3]);
    this.keywords = new Keywords(creature[4]);
    this.canAttack = this.keywords.hasCharge;
    this.lastTurnDefense = this.defense;
  }

  public CreatureOnBoard (Card card)
  {
    this.id = card.id;
    this.baseId = card.baseId;
    this.attack = card.attack;
    this.defense = card.defense;
    this.keywords = new Keywords(card.keywords);
    this.canAttack = this.keywords.hasCharge;
    this.lastTurnDefense = card.defense;
    this.cost = card.cost;
    this.myHealthChange = card.myHealthChange;
    this.oppHealthChange = card.oppHealthChange;
    this.cardDraw = card.cardDraw;
    baseCard = card;
  }

  public String generateText()
  {
    List<String> keywords = this.keywords.getListOfKeywords();

    return String.join(", ", keywords);
  }

  public String toDescriptiveString()
  {
    StringBuilder sb = new StringBuilder();
    if (id >= 0) sb.append("id:").append(this.id).append(' ');
    sb.append("(").append(this.baseId).append(")").append(' ');

    sb.append("ATT:").append(this.attack).append(' ');
    sb.append("DEF:").append(this.defense).append(' ');
    sb.append(generateText());

    return sb.toString();
  }

  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append(this.id).append(' ');
    sb.append(this.baseId).append(' ');
    sb.append(this.attack).append(' ');
    sb.append(this.defense).append(' ');
    sb.append(this.keywords);
    return sb.toString();
  }
  
  public String getAsInput(boolean isOpponentBoard) {
	  int position = isOpponentBoard? -1: 1;
	  StringBuilder s = new StringBuilder();
	  s.append(baseId).append(" ");
	  s.append(id).append(" ");
	  s.append(position).append(" ");
	  s.append(Type.CREATURE.ordinal()).append(" ");
	  s.append(cost).append(" ");
	  s.append(attack).append(" ");
	  s.append(defense).append(" ");
	  s.append(keywords).append(" ");
	  s.append(myHealthChange).append(" ");
	  s.append(oppHealthChange).append(" ");
	  s.append(cardDraw).append(" ");
	  return s.toString();
  }

public String toTooltipText() {
    return baseCard.toTooltipText(this);
}


}
