package com.codingame.game.engine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by aCat on 2018-03-22.
 */
public final class Constants
{
  public static int VERBOSE_LEVEL = 3; // 3 - full, 2 - without turn details, 1 - results only, 0 - silent

  public static final int CARDS_IN_DECK = 30; // 30;
  public static final int CARDS_IN_DRAFT = 60; // 60;

  public static final int INITIAL_HAND_SIZE = 4;
  public static final int MAX_CARDS_IN_HAND = 8; // was 10
  public static final int SECOND_PLAYER_CARD_BONUS = 1;
  public static final int SECOND_PLAYER_MAX_CARD_BONUS = 0;
  //public static final int EMPTY_DECK_DAMAGE = 5;

  public static final int MAX_MANA = 12;
  public static final int INITIAL_HEALTH = 30;

  public static final int MAX_CREATURES_IN_LINE = 6; // was 8

  public static final int TIMELIMIT_FIRSTDRAFTTURN = 1000;
  public static final int TIMELIMIT_DRAFTTURN = 100;
  public static final int TIMELIMIT_FIRSTGAMETURN = 1000;
  public static final int TIMELIMIT_GAMETURN = 100;

  public static final int PLAYER_TURNLIMIT = 50;
  public static final int MAX_TURNS_HARDLIMIT = (2*CARDS_IN_DECK + 2*CARDS_IN_DECK + 2*10) * 10;

  public static final HashMap<Integer,Card> CARDSET = new HashMap<>();

  public static final int FRAME_DURATION_DRAFT = 500;
  public static final int FRAME_DURATION_BATTLE = 750;
  public static final int FRAME_DURATION_SUMMON = 600;

  public static void LoadCardlist(String cardsetPath)
  {
    BufferedReader bufferedReader = null;
    try
    {
      bufferedReader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(cardsetPath), "UTF-8"));
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return;
    }

    String line = null;
    try
    {
      while((line = bufferedReader.readLine())!=null)
      {
        line=line.replaceAll("//.*","").trim();
        if (line.length() > 0)
        {
          Card c = new Card(line.split("\\s*;\\s*"));
          CARDSET.put(c.baseId, c);
        }
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public static void GenerateCardlistHTML(String templatePath, String outHTMLPath) throws IOException
  {
    Scanner s = new Scanner(new InputStreamReader(ClassLoader.getSystemResourceAsStream(templatePath), "UTF-8")).useDelimiter("\\A");
    String template = s.hasNext() ? s.next() : "";
    StringBuilder sb = new StringBuilder();

    for (Card c : CARDSET.values())
    {
      sb.append(c.toHTMLString()+"\n");
    }

    template = String.format(template, sb.toString());

    try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outHTMLPath), "utf-8")))
    {
      writer.write(template);
    }
  }
}
