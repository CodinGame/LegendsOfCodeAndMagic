package com.codingame.game.engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aCat on 2018-03-22.
 */
public class Action
{
  public enum Type {SUMMON, ATTACK, USE, PASS};

  public Type type;
  public int arg1;
  public int arg2;
  public Card.Type cardType; // for items
  public String text; // for say
  public ActionResult result;

  public Action()
  {
  }


  public static List<Action> parseSequence(String data) throws InvalidActionHard
  {
    ArrayList<Action> actions = new ArrayList<>();

    for (String str : data.split(";"))
    {
      str = str.trim();
      if (str.isEmpty())
        continue; // empty action is a valid action
      actions.add(Action.parse(str));
    }

    return actions;
  }

  // todo copy constructor?


  public static Action parse (String data) throws InvalidActionHard
  {
    String[] str = data.split(" ", 2);

    Type type;
    switch (str[0].trim())
    {
      case "SUMMON": type = Type.SUMMON; break;
      case "ATTACK": type = Type.ATTACK; break;
      case "USE": type = Type.USE; break;
      case "PASS": type = Type.PASS; break;
      default: throw new InvalidActionHard("Invalid action name. Should be SUMMON, ATTACK, or USE.");
    }

    if (type==Type.SUMMON)
    {
      try
      {
      String[] args = str[1].split(" ", 2);
      int arg1;      
      arg1 = Integer.parseInt(args[0]);
      String text = args.length < 2 ? "" : args[1].trim();
      return Action.newSummon(arg1, text);      
      }
      catch (Exception e)
      {
        throw new InvalidActionHard("Invalid SUMMON argument. Expected integer (card id).");
      }
    }
    else if (type==Type.PASS) {
    	return Action.newPass();
    }
    else {
      

      try
      {
          String[] args = str[1].split(" ", 3);

          int arg1;
          int arg2;
        arg1 = Integer.parseInt(args[0]);
        arg2 = Integer.parseInt(args[1]);
        String text = args.length < 3 ? "" : args[2].trim();
        return type==Type.ATTACK ? Action.newAttack(arg1, arg2, text) : Action.newUse(arg1, arg2, text);
      }
      catch (Exception e)
      {
        throw new InvalidActionHard("Invalid "+type.toString()+" arguments. Expected two integers (card id and target id).");
      }

      
    }
  }

  public static Action newSummon(int arg1)
  {
    return newSummon(arg1, "");
  }

  public static Action newSummon(int arg1, String text) // todo private?
  {
    Action a = new Action();
    a.type = Type.SUMMON;
    a.cardType = Card.Type.CREATURE;
    a.arg1 = arg1;
    a.text = text;
    return a;
  }

  public static Action newAttack(int arg1, int arg2)
  {
    return newAttack(arg1, arg2, "");
  }

  public static Action newAttack(int arg1, int arg2, String text) // todo private?
  {
    Action a = new Action();
    a.type = Type.ATTACK;
    a.cardType = Card.Type.CREATURE;
    a.arg1 = arg1;
    a.arg2 = arg2;
    a.text = text;
    return a;
  }

  public static Action newPass() {
	Action a = new Action();
    a.type = Type.PASS;
    a.text = "";
    return a;
  }

  public static Action newUse(int arg1, int arg2)
  {
    return newUse(arg1, arg2, "");
  }



  // todo - it's just a shell now
  public static Action newUse(int arg1, int arg2, String text) // todo private?
  {
    Action a = new Action();
    a.type = Type.USE;
    //a.cardType = Card.Type.CREATURE; // todo here
    a.arg1 = arg1;
    a.arg2 = arg2;
    a.text = text;
    return a;
  }

  public String toStringNoText()
  {
    switch (type)
    {
      case SUMMON: return String.format("SUMMON %d", arg1);
      case ATTACK: return String.format("ATTACK %d %d", arg1, arg2);
      case USE: return String.format("USE %d %d", arg1, arg2);
      case PASS: return "PASS";
    }
    return super.toString();
  }

  @Override
  public String toString()
  {
    switch (type)
    {
      case SUMMON: return String.format("SUMMON %d %s", arg1, text);
      case ATTACK: return String.format("ATTACK %d %d %s", arg1, arg2, text);
      case USE: return String.format("USE %d %d %s", arg1, arg2, text);
      case PASS: return "PASS";
    }
    return super.toString();
  }

  @Override
  public boolean equals(Object other)
  {
    if (other == null) return false;
    if (other == this) return true;
    if (!(other instanceof Action))return false;
    Action a = (Action)other;
    return this.toStringNoText().equals(a.toStringNoText());
  }
}
