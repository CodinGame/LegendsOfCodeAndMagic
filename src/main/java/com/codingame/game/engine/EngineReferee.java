package com.codingame.game.engine;

import java.util.ArrayList;
import java.util.List;

import com.codingame.game.Player;
import com.codingame.game.engine.Action.Type;
import com.codingame.game.ui.RefereeUI;
import com.codingame.gameengine.core.AbstractPlayer.TimeoutException;
import com.codingame.gameengine.core.MultiplayerGameManager;

public class EngineReferee {
    //private MultiplayerGameManager<Player> gameManager; // @Inject ?

    public DraftPhase draft;
    public GameState state = null;

    public int gamePlayer = 0;
    public int gameTurn = 0;
    public List<Action> actionsToHandle = new ArrayList<>();
    
    private boolean showStart = true;
    
    static final int ILLEGAL_ACTION_SUMMARY_LIMIT =3;

    public void refereeInit(MultiplayerGameManager<Player> gameManager) {
        if (Constants.VERBOSE_LEVEL > 1) System.out.println("New game");

        RefereeParams params = new RefereeParams(gameManager);

        DraftPhase.Difficulty difficulty;
        switch (gameManager.getLeagueLevel()) {
        case 1:
            difficulty = DraftPhase.Difficulty.VERY_EASY;
            break;
        case 2:
            difficulty = DraftPhase.Difficulty.EASY;
            break;
        case 3:
            difficulty = DraftPhase.Difficulty.LESS_EASY;
            break;
        default:
            difficulty = DraftPhase.Difficulty.NORMAL;
            break;
        }
        

        Constants.LoadCardlist("cardlist.txt");
        if (Constants.VERBOSE_LEVEL > 1) System.out.println("   CARDSET with " + Constants.CARDSET.size() + " cards loaded.");
        if (Constants.VERBOSE_LEVEL > 1) System.out.println("   Difficulty is set to: " + difficulty.name() + ".");

        draft = new DraftPhase(difficulty, params);
        draft.PrepareChoices();

        if (Constants.VERBOSE_LEVEL > 1) System.out.println("   Draw Phase Prepared. " + draft.allowedCards.size() + " cards allowed. ");
        if (Constants.VERBOSE_LEVEL > 1) System.out.println("   " + draft.draftingCards.size() + " cards selected to the draft.");

        gameManager.setMaxTurns(Constants.MAX_TURNS_HARDLIMIT); // should be never reached, not handled on the referee's side
    }
    
    
    public boolean refereeGameTurn(MultiplayerGameManager<Player> gameManager, int turn, RefereeUI ui) {
        if (showStart && gameTurn == Constants.CARDS_IN_DECK) {
            showStart = false;
            gameManager.addTooltip(gameManager.getPlayer(0), "Battle start!");
            
        }
        if (gameTurn < Constants.CARDS_IN_DECK) {
            DraftTurn(gameManager, () -> ui.draft(gameTurn));
            return false;
        } else {
            return GameTurn(gameManager, () -> ui.battle(gameTurn));
        }
    }

    private void DraftTurn(MultiplayerGameManager<Player> gameManager, Runnable render) {
        if (Constants.VERBOSE_LEVEL > 1 && gameTurn == 0) System.out.println("   Draft phase");
        if (Constants.VERBOSE_LEVEL > 2) System.out.println("      Draft turn " + gameTurn + "/" + Constants.CARDS_IN_DECK);

        gameManager.setTurnMaxTime(gameTurn == 0 ? Constants.TIMELIMIT_FIRSTDRAFTTURN : Constants.TIMELIMIT_DRAFTTURN);

        for (int player = 0; player < 2; player++) {
            Player sdkplayer = gameManager.getPlayer(player);
            for (String line : draft.getMockPlayersInput()) {
                sdkplayer.sendInputLine(line);
            }

            for (int card = 0; card < 3; card++)
                sdkplayer.sendInputLine(draft.draft[gameTurn][card].getAsInput());
            sdkplayer.execute();
        }

        for (int player = 0; player < 2; player++) {
            Player sdkplayer = gameManager.getPlayer(player);
            try {
                String output = sdkplayer.getOutputs().get(0);
                DraftPhase.ChoiceResultPair choice = draft.PlayerChoice(gameTurn, output, player);
                draft.text[player] = choice.text;
                gameManager.addToGameSummary(
                    String.format("Player %s chose %s", sdkplayer.getNicknameToken(), choice.card.toDescriptiveString())
                );
            } catch (InvalidActionHard e) {
                HandleError(gameManager, sdkplayer, sdkplayer.getNicknameToken() + ": " + e.getMessage());
                return;
            } catch (TimeoutException e) {
                HandleError(gameManager, sdkplayer, sdkplayer.getNicknameToken() + " timeout!");
                return;
            }
        }

        render.run();
        gameTurn++;
    }

    private boolean GameTurn(MultiplayerGameManager<Player> gameManager, Runnable render) {
        Player sdkplayer = gameManager.getPlayer(gamePlayer);
        gameManager.setFrameDuration(Constants.FRAME_DURATION_BATTLE);
        
        if (state == null) // frame-only turn for showing the initial state
        {
            draft.ShuffleDecks();
            if (Constants.VERBOSE_LEVEL > 1) System.out.println("   Decks shuffled.");
            if (Constants.VERBOSE_LEVEL > 1) System.out.println("   Game phase");
            state = new GameState(draft);

            gameManager.setTurnMaxTime(1); // weird try but works ^^
            sdkplayer.execute();

            render.run();
            return false;
        }

        if (!actionsToHandle.isEmpty()) // there is a legal action on top of the list
        {
            gameManager.setTurnMaxTime(1); // weird try but works ^^
            sdkplayer.execute();

            Action a = actionsToHandle.remove(0);
            gameManager.addToGameSummary("Player " + sdkplayer.getNicknameToken() + " performed action: " + a.toStringNoText());
            
            state.AdvanceState(a);
            if (a.type == Action.Type.SUMMON) {
                gameManager.setFrameDuration(Constants.FRAME_DURATION_SUMMON);
            }
        } else // it's time to actually call a player
        {
            if (Constants.VERBOSE_LEVEL > 2) System.out.print("      Game turn " + (gameTurn - Constants.CARDS_IN_DECK) + ", player " + gamePlayer);

            gameManager.setTurnMaxTime(gameTurn <= Constants.CARDS_IN_DECK + 1 ? Constants.TIMELIMIT_FIRSTGAMETURN : Constants.TIMELIMIT_GAMETURN);

            state.AdvanceState();

            for (String line : state.getPlayersInput())
                sdkplayer.sendInputLine(line);
            for (String line : state.getCardsInput())
                sdkplayer.sendInputLine(line);
            sdkplayer.execute();

            try {
                String output = sdkplayer.getOutputs().get(0);
                actionsToHandle = Action.parseSequence(output);
                if (Constants.VERBOSE_LEVEL > 2) System.out.println(" (returned " + actionsToHandle.size() + " actions)");
            } catch (InvalidActionHard e) {
                HandleError(gameManager, sdkplayer, sdkplayer.getNicknameToken() + ": " + e.getMessage());
            } catch (TimeoutException e) {
                HandleError(gameManager, sdkplayer, sdkplayer.getNicknameToken() + " timeout!");
            }
        }

        // now we roll-out actions until next legal is found
        List<Action> legals = state.computeLegalActions(); //System.out.println(gameTurn + " "+ state.players[state.currentPlayer].currentMana +"/"+state.players[state.currentPlayer].maxMana + "->"+legals);
        int illegalActions = 0;
        
        while (!actionsToHandle.isEmpty()) {
            Action a = actionsToHandle.get(0);
            if (a.type == Type.PASS) {
                actionsToHandle.remove(0); // pop
                continue;
            }
            if (legals.contains(a))
                break;
            actionsToHandle.remove(0); // pop
            illegalActions++; 
            if (illegalActions <= ILLEGAL_ACTION_SUMMARY_LIMIT) {
                gameManager.addToGameSummary("[Warning] " + sdkplayer.getNicknameToken() + " Action is not legal: " + a.toString());    
            }
        }
        if (illegalActions > ILLEGAL_ACTION_SUMMARY_LIMIT) {
            gameManager.addToGameSummary("[Warning] " + sdkplayer.getNicknameToken() + " Performed another " + (illegalActions - ILLEGAL_ACTION_SUMMARY_LIMIT) + " illegalActions");
        }

        render.run();

        if (CheckAndHandleEndgame(gameManager, state))
            return true;

        if (actionsToHandle.isEmpty()) // player change
        {
            gameTurn++;
            gamePlayer = (gamePlayer + 1) % 2;
        }

        return false;
    }

    private void HandleError(MultiplayerGameManager<Player> gameManager, Player sdkplayer, String errmsg) {
        gameManager.addToGameSummary(MultiplayerGameManager.formatErrorMessage(errmsg));
        sdkplayer.deactivate(errmsg);
        sdkplayer.setScore(-1);
        gameManager.endGame();
    }

    // returns true if the game ends
    private boolean CheckAndHandleEndgame(MultiplayerGameManager<Player> gameManager, GameState state) {
        if (state.winner == -1)
            return false;

        //gameManager.addToGameSummary("!\n" + state.toString());

        if (Constants.VERBOSE_LEVEL > 1) System.out.println("   Game finished in turn " + (gameTurn - Constants.CARDS_IN_DECK) + ".");
        if (Constants.VERBOSE_LEVEL > 1) System.out.print("   Scores: ");
        if (Constants.VERBOSE_LEVEL > 0) System.out.println((state.winner == 0 ? "1" : "0") + " " + (state.winner == 1 ? "1" : "0"));

        gameManager.addToGameSummary(MultiplayerGameManager.formatSuccessMessage(gameManager.getPlayer(state.winner).getNicknameToken() + " won!"));
        gameManager.getPlayer(state.winner).setScore(1);
        gameManager.endGame();
        return true;
    }

}
