package com.codingame.game.engine;

/**
 * Created by aCat on 2018-03-21.
 */
public class ActionResult
{
  public boolean isValid;
  public CreatureOnBoard attacker;
  public CreatureOnBoard defender;
  public int attackerHealthChange;
  public int defenderHealthChange;
  public int attackerAttackChange;
  public int defenderAttackChange;
  public int attackerDefenseChange;
  public int defenderDefenseChange;
  public boolean attackerDied;
  public boolean defenderDied;

  public ActionResult(boolean isValid)
  {
    this.isValid = isValid;
    this.attacker = null;
    this.defender = null;
    this.attackerHealthChange = 0;
    this.defenderHealthChange = 0;
  }

  public ActionResult(CreatureOnBoard attacker, CreatureOnBoard defender, boolean attackerDied, boolean defenderDied, int attackerHealthChange, int defenderHealthChange)
  {
    this.isValid = true;
    this.attackerDied = attackerDied;
    this.defenderDied = defenderDied;    
    this.attacker = attacker;
    this.defender = defender;
    this.attackerHealthChange = attackerHealthChange;
    this.defenderHealthChange = defenderHealthChange;
  }

    public ActionResult(CreatureOnBoard attacker, CreatureOnBoard defender, int healthGain, int healthTaken) {
        this(attacker, defender, false, false, healthGain, healthTaken);
    }
}
