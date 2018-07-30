package com.codingame.game;

import com.codingame.game.engine.EngineReferee;
import com.codingame.game.ui.RefereeUI;
import com.codingame.gameengine.core.AbstractReferee;
import com.codingame.gameengine.core.MultiplayerGameManager;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.codingame.view.FXModule;
import com.codingame.view.endscreen.EndScreenModule;
import com.google.inject.Inject;

public class Referee extends AbstractReferee {
    @Inject private MultiplayerGameManager<Player> gameManager;
    @Inject private GraphicEntityModule graphicEntityModule;
    @Inject private EndScreenModule endScreenModule;
    @Inject private FXModule fxModule;

    private EngineReferee engine = new EngineReferee();
    private RefereeUI ui = new RefereeUI();

    public static int turn = 0;
    
    @Override
    public void init()
    {
        // Engine
        engine.refereeInit(gameManager);

        // GUI.
        ui.engine = engine;
        ui.gameManager = gameManager;
        ui.graphicEntityModule = graphicEntityModule;
        ui.fxModule = fxModule;
        ui.init();
    }

    @Override
    public void gameTurn(int turn)
    {
        this.turn = turn;
        // Engine
        boolean end = engine.refereeGameTurn(gameManager, turn, ui);
    }

    @Override
    public void onEnd() {
        endScreenModule.setScores(gameManager.getPlayers().stream().mapToInt(p -> p.getScore()).toArray());
    }
}
