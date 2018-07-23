package com.codingame.game.engine;

import com.codingame.game.Player;
import com.codingame.gameengine.core.MultiplayerGameManager;

import java.util.Properties;
import java.util.Random;

public class RefereeParams
{
  public Random draftChoicesRNG;
  public Random shufflePlayer0RNG;
  public Random shufflePlayer1RNG;
  public Integer[][] predefinedDraftIds = null;
  private Properties params;

  public RefereeParams(long draftChoicesSeed, long shufflePlayer0Seed, long shufflePlayer1Seed)
  {
    draftChoicesRNG = new Random(draftChoicesSeed);
    shufflePlayer0RNG = new Random(shufflePlayer0Seed);
    shufflePlayer1RNG = new Random(shufflePlayer1Seed);
  }

  public RefereeParams(MultiplayerGameManager<Player> gameManager)
  {
    // pure initialization if seed set by the manager
    Long mainSeed = gameManager.getSeed();
    Random RNG = new Random(mainSeed);
    long draftChoicesSeed = RNG.nextLong();
    long shufflePlayer0Seed = RNG.nextLong();
    long shufflePlayer1Seed = RNG.nextLong();

    params = gameManager.getGameParameters();

    if ( isNumber(params.getProperty("seed"))) // overriding when seed given as parameter
    {
      mainSeed = Long.parseLong(params.getProperty("seed"));
      RNG = new Random(mainSeed);
      draftChoicesSeed = RNG.nextLong();
      shufflePlayer0Seed = RNG.nextLong();
      shufflePlayer1Seed = RNG.nextLong();
    }

    // overriding remaining seeds
    if ( isNumber(params.getProperty("draftChoicesSeed")))
      draftChoicesSeed = Long.parseLong(params.getProperty("draftChoicesSeed"));
    if ( isNumber(params.getProperty("shufflePlayer0Seed")))
      shufflePlayer0Seed = Long.parseLong(params.getProperty("shufflePlayer0Seed"));
    if ( isNumber(params.getProperty("shufflePlayer1Seed")))
      shufflePlayer1Seed = Long.parseLong(params.getProperty("shufflePlayer1Seed"));

    if ( params.getProperty("predefinedDraftIds")!=null)
    {
      predefinedDraftIds = new Integer[Constants.CARDS_IN_DECK][3];
      String[] picks = params.getProperty("predefinedDraftIds").split(",");

      assert (picks.length >= Constants.CARDS_IN_DECK);

      for(int pick=0; pick <  Constants.CARDS_IN_DECK; pick++)
      {
        String[] choice = picks[pick].trim().split("\\s+");
        for (int i=0; i < 3; i++)
        {
          predefinedDraftIds[pick][i] = Integer.parseInt(choice[i].trim());
        }
      }
    }

    // update params values
    // we can't update predefinedDraftIds if there were not set by the user...
    params.setProperty("draftChoicesSeed",  Long.toString(draftChoicesSeed));
    params.setProperty("shufflePlayer0Seed",  Long.toString(shufflePlayer0Seed));
    params.setProperty("shufflePlayer1Seed",  Long.toString(shufflePlayer1Seed));

    // set RNG's
    draftChoicesRNG = new Random(draftChoicesSeed);
    shufflePlayer0RNG = new Random(shufflePlayer0Seed);
    shufflePlayer1RNG = new Random(shufflePlayer1Seed);

    //System.out.println(toString());
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("draftChoicesSeed").append("=").append(params.getProperty("draftChoicesSeed")).append("\n");
    sb.append("shufflePlayer0Seed").append("=").append(params.getProperty("shufflePlayer0Seed")).append("\n");
    sb.append("shufflePlayer1Seed").append("=").append(params.getProperty("shufflePlayer1Seed")).append("\n");
    //sb.append("predefinedDraftIds").append("=").append(params.getProperty("predefinedDraftIds")).append("\n");
    return sb.toString();
  }
  // todo toString?

  private boolean isNumber(String str)
  {
    try {
      Long.parseLong(str);
      return true;
    } catch (NumberFormatException nfe) {}
    return false;
  }
}
