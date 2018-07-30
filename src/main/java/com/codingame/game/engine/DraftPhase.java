package com.codingame.game.engine;

//import javafx.util.Pair; :(

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by aCat on 2018-03-24.
 */
public class DraftPhase
{
  public enum Difficulty {NORMAL, LESS_EASY, EASY, VERY_EASY};

  public Difficulty difficulty;
  public List<Card> allowedCards;
  //TODO List should be used everywhere, apart from creation
  public List<Card> draftingCards;
  public Card[][] draft;
  //TODO we shouldn't mix arrays and collections, List<List<Card>> would be better
  public ArrayList<Card>[] chosenCards;
  public ArrayList<Card>[] decks; // after shuffle and assigning unique id's
  
  public String[] text = new String[2];

  private Random choicesRNG;
  private Random[] shufflesRNG;
  private RefereeParams params;

  // todo - add function and field documentation

  public DraftPhase(Difficulty difficulty, RefereeParams params)
  {
    this.difficulty = difficulty;
    this.params = params;

    chosenCards = new ArrayList[] {new ArrayList<Card>(), new ArrayList<Card>()};
    decks = new ArrayList[] {new ArrayList<Card>(), new ArrayList<Card>()};

    choicesRNG = params.draftChoicesRNG;
    shufflesRNG = new Random[] {params.shufflePlayer0RNG, params.shufflePlayer1RNG};
  }

  private boolean isVeryEasyCard(Card card)
  {
    return card.type == Card.Type.CREATURE
            && !card.keywords.hasAnyKeyword()
            && card.myHealthChange == 0 && card.oppHealthChange == 0 && card.cardDraw == 0;
  }

  private void prepareAllowedCards()
  {
    Collection<Card> cardBase = Constants.CARDSET.values();

    if (difficulty == Difficulty.NORMAL) {
      allowedCards = new ArrayList<>(cardBase);
    } else if (difficulty == Difficulty.LESS_EASY) {
        allowedCards = cardBase.stream()
            .filter(card -> !card.keywords.hasDrain && !card.keywords.hasLethal && !card.keywords.hasWard)
            .collect(Collectors.toList());
    } else if (difficulty == Difficulty.EASY) {
        allowedCards = cardBase.stream()
            .filter(card -> card.type == Card.Type.CREATURE)
            .filter(card -> !card.keywords.hasDrain && !card.keywords.hasLethal && !card.keywords.hasWard)
            .collect(Collectors.toList());
    } else {
        allowedCards = cardBase.stream()
            .filter(card -> isVeryEasyCard(card))
            .collect(Collectors.toList());
    }
  }

  public void PrepareChoices()
  {
    prepareAllowedCards();

    if (params.predefinedDraftIds != null) // parameter-forced draft choices
    {
      draftingCards = new ArrayList<>(); // 0 size is ok here? in hand-made draft this is meaningless variable
      draft = new Card[Constants.CARDS_IN_DECK][3];
      for(int pick=0; pick <  Constants.CARDS_IN_DECK; pick++)
      {
        for (int i=0; i < 3; i++)
        {
          draft[pick][i] = Constants.CARDSET.get(params.predefinedDraftIds[pick][i]);
        }
      }

      return;
    }

    ArrayList<Integer> drafting = new ArrayList<>();
    for (int pick = 0; pick < Math.min(Constants.CARDS_IN_DRAFT, allowedCards.size()); pick++)
    {
      int i = -1;
      do
      {
        i = choicesRNG.nextInt(allowedCards.size());
      } while (drafting.contains(i));
      drafting.add(i);
    }

    assert (drafting.size()>=3);

    draftingCards = new ArrayList<>();
    for (Integer i:drafting)
      draftingCards.add(allowedCards.get(i));


    draft = new Card[Constants.CARDS_IN_DECK][3];
    for (int pick = 0; pick < Constants.CARDS_IN_DECK; pick++)
    {
      int choice1 = drafting.get(choicesRNG.nextInt(drafting.size()));
      int choice2;
      do
      {
        choice2 = drafting.get(choicesRNG.nextInt(drafting.size()));
      } while (choice2==choice1);
      int choice3;
      do
      {
        choice3 = drafting.get(choicesRNG.nextInt(drafting.size()));
      } while (choice3==choice1 || choice3==choice2);

      draft[pick][0] = allowedCards.get(choice1);
      draft[pick][1] = allowedCards.get(choice2);
      draft[pick][2] = allowedCards.get(choice3);
    }

    //return draftCards;
  }

  public ChoiceResultPair PlayerChoice(int pickNumber, String action, int player) throws InvalidActionHard
  {
    Card[] cards = draft[pickNumber];
    Card choice = null;
    String text = "";
    try
    {
      String[] command = action.split(" ", 3);
      text = command.length < 3 ? "" : command[2].trim();

      if (!command[0].equals("PICK") && !command[0].equals("CHOOSE") && !command[0].equals("PASS"))
        throw new Exception();
      if (command[0].equals("PASS")) {
    	  choice = cards[0];
      }
      else if (command[0].equals("PICK"))
      {
    	int value = Integer.parseInt(command[1]);
        if (value < 0 || value > 2)
          throw new InvalidActionHard("Invalid action format. \"PICK\" argument should be 0, 1 or 2.");
        choice = cards[value];
      }
      else // "CHOOSE"
      {
        HashSet<Integer> ids = new HashSet<>();
        ids.add(cards[0].baseId);
        ids.add(cards[1].baseId);
        ids.add(cards[2].baseId);
        int value = Integer.parseInt(command[1]);
        
        if (!ids.contains(value))
          throw new InvalidActionHard("Invalid action format. \"CHOOSE\" argument should be valid card's base id " + ids + ".");
        choice = Constants.CARDSET.get(value);
      }
    }
    catch (InvalidActionHard e)
    {
      throw e;
    }
    catch (Exception e)
    {
      throw new InvalidActionHard("Invalid action. Expected  \"PICK [0,1,2]\" or \"PASS\".");
    }

    chosenCards[player].add(choice);
    return new ChoiceResultPair(choice, text);
  }

  public void ShuffleDecks()
  {
    for (int player=0; player <2; player++)
    {
      for (Card c : chosenCards[player])
      {
        decks[player].add(new Card(c));
      }
      Collections.shuffle(decks[player], shufflesRNG[player]);
      for (int i=0; i < decks[player].size(); i++)
        decks[player].get(i).id = 2 * i + player + 1;
    }
  }

  public class ChoiceResultPair
  {
    public Card card;
    public String text;

    public ChoiceResultPair(Card card, String text)
    {
      this.card = card;
      this.text = text;
    }
  }
  
  public String[] getMockPlayersInput() {
	    ArrayList<String> lines = new ArrayList<>();
	    String s = join(Constants.INITIAL_HEALTH, 0, decks[0].size(), 25);
	    lines.add(s);
	    lines.add(s);
	    lines.add("0");
	    lines.add("3");
	    
	    
	    return lines.stream().toArray(String[]::new);
	  }
  
  static public String join(Object... args) {
      return Stream.of(args).map(String::valueOf).collect(Collectors.joining(" "));
  }
}


