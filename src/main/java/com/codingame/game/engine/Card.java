package com.codingame.game.engine;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kot on 2018-03-20.
 */
public class Card {
    
  public enum Type {
    CREATURE("creature"),
    ITEM_GREEN("itemGreen"),
    ITEM_RED("itemRed"),
    ITEM_BLUE("itemBlue");

    private String description;

    public String getDescription() {
      return description;
    }

    public static Type fromDescription(String description) {
      for (Type type : values()) {
        if (type.description.equals(description)) {
          return type;
        }
      }
      return null;
    }

    Type(String description) {
      this.description = description;
    }

  }

  public int id;
  public int baseId;
  public Type type;
  public int cost;
  public int attack;
  public int defense;
  public Keywords keywords;
  //TODO maybe myHealthChange, oppHealthChange, cardDraw should be moved into Summon class?
  public int myHealthChange;
  public int oppHealthChange;
  public int cardDraw;
  public String name;
  public String text;
  private String tooltipTextBase;
  public String comment;



  // todo copy constructor with id; ?
  // todo constructor with text (id-based)

  // copy constructor
  public Card(Card card)
  {
    this.id = card.id;
    this.baseId = card.baseId;
    this.name = card.name;
    this.type = card.type;
    this.cost =   card.cost;
    this.attack = card.attack;
    this.defense = card.defense;
    this.keywords = new Keywords(card.keywords);
    this.myHealthChange =  card.myHealthChange;
    this.oppHealthChange = card.oppHealthChange;
    this.cardDraw =        card.cardDraw;
    this.comment = card.comment;
    generateText();
    this.tooltipTextBase = card.tooltipTextBase;
  }

  // data = {baseId, name, type, cost, attack, defense, keywords, myHealthChange, oppHealthChange, cardDraw, comment}
  public Card(String[] data)
  {
    this.id = -1;
    this.baseId = Integer.parseInt(data[0]);
    this.name = data[1];
    this.type = Type.fromDescription(data[2]);
    this.cost =   Integer.parseInt(data[3]);
    this.attack = Integer.parseInt(data[4]);
    this.defense = Integer.parseInt(data[5]);
    this.keywords = new Keywords(data[6]);
    this.myHealthChange =  Integer.parseInt(data[7]);
    this.oppHealthChange = Integer.parseInt(data[8]);
    this.cardDraw =        Integer.parseInt(data[9]);

    this.comment = ""; //data[11]; // comments deprecated as we are far from TESL in many cards
    generateText();
    try
    {
      this.tooltipTextBase = data[10];
    }
    catch (java.lang.ArrayIndexOutOfBoundsException e)
    {
      this.tooltipTextBase = "";
    }
  }

  public void generateText()
  {
    StringBuilder sb = new StringBuilder();

    List<String> keywords = this.keywords.getListOfKeywords();

    ArrayList<String> summon = new ArrayList<>();

    if (myHealthChange > 0)
      summon.add("gain "+myHealthChange+" health");
    if (myHealthChange < 0)
      summon.add("deal "+Math.abs(myHealthChange)+" damage to yourself");
    if (oppHealthChange < 0)
      summon.add("deal "+Math.abs(oppHealthChange)+" damage to your opponent");
    if (cardDraw > 0)
      summon.add("draw " + (cardDraw==1 ? "a card" : (cardDraw+" cards")));

    if (type == Type.CREATURE)
    {
      sb.append(String.join(", ", keywords));
      if (myHealthChange!=0 || oppHealthChange!=0 || cardDraw !=0)
      {
        sb.append(keywords.isEmpty() ? "" : "; ").append("Summon: ");
        sb.append(String.join(", ", summon)).append(".");
      }
    }
    else if(type == Type.ITEM_GREEN)
    {
      if (!keywords.isEmpty())
        sb.append("Give ").append(String.join(", ", keywords));
      if (myHealthChange!=0 || oppHealthChange!=0 || cardDraw !=0)
      {
        sb.append(keywords.isEmpty() ? "" : "; ");
        sb.append(String.join(", ", summon)).append(".");
      }
    }
    else if(type == Type.ITEM_RED)
    {
      if (!keywords.isEmpty())
        sb.append("Remove ").append(keywords.size()==7 ? "all keywords" : String.join(", ", keywords));
      if (myHealthChange!=0 || oppHealthChange!=0 || cardDraw !=0)
      {
        sb.append(keywords.isEmpty() ? "" : "; ");
        sb.append(String.join(", ", summon)).append(".");
      }
    }
    else
    {
      sb.append(String.join(", ", summon)).append(".");
    }

    this.text = sb.toString();
  }

  private String toTextDescription()
  {
    StringBuilder sb = new StringBuilder();
    NumberFormat nf = new DecimalFormat("+#;-#");

    if (type == Type.CREATURE)
    {
      sb.append(attack).append(" / ").append(defense).append(" creature. ").append(text);
    }
    else if(type == Type.ITEM_GREEN)
    {
      sb.append(text);
      if (attack != 0 || defense != 0)
        sb.append(" Give friendly creature ").append(nf.format(attack)).append(" / ").append(nf.format(defense));
    }
    else if(type == Type.ITEM_RED)
    {
      sb.append(text);
      if (attack != 0 || defense != 0)
        sb.append(" Give enemy creature ").append(attack).append(" / ").append(defense);
    }
    else
    {
      sb.append(defense != 0 ? ("Deal "+Math.abs(defense)+" damage. ") : "").append(text.length()>1 ? text : "");
    }

    return sb.toString();
  }
  
  private String toTooltipInnerText()
  {
    return toTooltipInnerText(null);
  }

  private String toTooltipInnerText(CreatureOnBoard creatureOnBoard)
  {
    StringBuilder sb = new StringBuilder();

    if (type == Type.CREATURE)
    {
      Keywords keywords;
      if (creatureOnBoard != null) {
          keywords = creatureOnBoard.keywords;
          int aDiff = creatureOnBoard.attack - attack;
          int dDiff = creatureOnBoard.defense - defense;
          if (aDiff != 0 || dDiff != 0) {
              sb.append(' ');  
          }
          sb.append(attack).append(" / ").append(defense).append(" Creature");
          if (aDiff != 0 || dDiff != 0) {
              sb.append("\n");
              if (aDiff > 0) {    
                  sb.append("+");
              } else if (aDiff == 0) {
                  sb.append(" ");
              }
              sb.append(aDiff == 0 ? " " : aDiff).append("  ");
              if (dDiff > 0) {    
                  sb.append("+");
              } else if (dDiff == 0) {
                  sb.append(" ");
              }
              sb.append(dDiff == 0 ? " " : dDiff).append(" ");
          }
      } else {
          keywords = this.keywords;
          sb.append(attack).append(" / ").append(defense).append(" Creature");
      }
      List<String> keywordsList = keywords.getListOfKeywords();
      
      if (!keywordsList.isEmpty())
        sb.append("\\n").append(String.join(", ", keywordsList));
      String tt = tooltipTextBase.trim();
      if (tt.length()> 0)
        sb.append("\\n").append(tt);
      return sb.toString();
    }

    if (type == Type.ITEM_GREEN)
      sb.append("Green Item\\n");
    if (type == Type.ITEM_RED)
      sb.append("Red Item\\n");
    if (type == Type.ITEM_BLUE)
      sb.append("Blue Item\\n");
    sb.append(tooltipTextBase.trim());

    return sb.toString();
  }

  public String toTooltipText(CreatureOnBoard creatureOnBoard)
  {
    StringBuilder sb = new StringBuilder();

    sb.append(this.name + " (#"+baseId+")").append("\n\n");

    if (id >= 0) sb.append("instanceId: ").append(this.id).append("\n");
    sb.append("cost: ").append(this.cost).append("\n");
    sb.append("\n");

    sb.append(toTooltipInnerText(creatureOnBoard).replace("\\n", "\n"));

    return sb.toString();
  }

  public String toHTMLString()
  {
    
    StringBuilder sb = new StringBuilder();
    sb.append("<tr>");
    sb.append("<td>").append(baseId).append("</td>");
    //String portraitpath = String.format("src=\"../assets/card_images/%03d.png\"", baseId);
    String portraitimage = String.format("src=\"portraits/%03d.png\"", baseId);
    String cardimage = String.format("src=\"cards/%03d.png\"", baseId);
    //sb.append("<td>").append("<a><img "+portraitimage+" width=\"50\"><img class=\"enlarge\" "+cardimage+" ></a>").append("</td>"); // width="300" -- deprecated for now
    sb.append("<td>").append("<a><img "+portraitimage+" width=\"50\"><img class=\"enlarge\" "+portraitimage+" ></a>").append("</td>"); // width="300" -- deprecated for now

    sb.append("<td><b><a>"+name+ "<img class=\"enlarge\" "+cardimage+" ></a></b></td>");

    sb.append("<td>").append(type.getDescription()).append("</td>");

    sb.append("<td>").append(cost).append("</td>");
    sb.append("<td>").append(attack).append("</td>");
    sb.append("<td>").append(defense).append("</td>");

    sb.append("<td><div class=\"keywords\">");
    sb.append(this.keywords);
    sb.append("</div></td>");

    sb.append("<td>").append(myHealthChange).append("</td>");
    sb.append("<td>").append(oppHealthChange).append("</td>");
    sb.append("<td>").append(cardDraw).append("</td>");

    Pattern tesllink = Pattern.compile("@(\\d+)=(['\\w-]+)");
    String htmlcomment = comment;
    Matcher m = tesllink.matcher(htmlcomment);
    while (m.find())
    {
      String lowname = m.group(2).replace("-", "").toLowerCase();
      String fullname = m.group(2).replace("-", " ");
      String link = String.format("<a href=\"https://www.legends-decks.com/card/%s/%s\">%s<img class=\"enlarge\" src=\"https://www.legends-decks.com/img_cards/%s.png\"></a>",
              m.group(1), lowname, fullname, lowname);
      htmlcomment = htmlcomment.replace(m.group(0), link);
    }

    String tt = toTooltipInnerText();
    if (tt.length()> 0)
      sb.append("<td><i>").append(tt.replace("\\n", "<br/>")).append("</i></td>");
    else
      sb.append("<td><i>").append(toTextDescription()).append("</i></td>");

    sb.append("<!--<td>").append(htmlcomment).append("</td>-->");


            // card = "<tr><td>" + card.replace(";", "</td><td>") + "</td></tr>\n";


    sb.append("</tr>");
  // todo
    return sb.toString();
  }

  public String toDescriptiveString()
  {
    StringBuilder sb = new StringBuilder();
    if (id >= 0) sb.append("id:").append(this.id).append(' ');
    if (!name.equals("?")) sb.append(this.name).append(' ');
    sb.append("(#").append(this.baseId).append(")").append(' ');
    sb.append(type.getDescription()).append(' ');

    sb.append("COST:").append(this.cost).append(' ');
    if (this.type == Type.CREATURE )
    {
      sb.append("ATT:").append(this.attack).append(' ');
      sb.append("DEF:").append(this.defense).append(' ');
    }
    else // items
    {
      sb.append("ATT:").append(String.format("%+d", this.attack)).append(' ');
      sb.append("DEF:").append(String.format("%+d", this.defense)).append(' ');
    }

    sb.append(" ").append(this.text);

    return sb.toString();
  }

  public String toStringWithoutId()
  {
    StringBuilder sb = new StringBuilder();
    sb.append(this.baseId).append(' ');
    //sb.append(this.type.getDescription()).append(' ');
    sb.append(this.type.ordinal()).append(' '); // todo test is it ok? 0, 1, 2, 3
    sb.append(this.cost).append(' ');
    sb.append(this.attack).append(' ');
    sb.append(this.defense).append(' ');
    sb.append(this.keywords);
    sb.append(' ');
    sb.append(this.myHealthChange).append(' ');
    sb.append(this.oppHealthChange).append(' ');
    sb.append(this.cardDraw).append(' ');
    return sb.toString();
  }

  public String toString()
  {
    return this.id+ " " + toStringWithoutId();
  }
  
  public String getAsInput() {
	  StringBuilder s = new StringBuilder();
	  
	  s.append(baseId).append(" ");
	  s.append(id).append(" ");
	  s.append(0).append(" ");
	  s.append(type.ordinal()).append(" ");
	  s.append(cost).append(" ");
	  s.append(attack).append(" ");
	  s.append(defense).append(" ");
	  s.append(keywords).append(" ");
	  s.append(myHealthChange).append(" ");
	  s.append(oppHealthChange).append(" ");
	  s.append(cardDraw).append(" ");
	  return s.toString();
  }
}
