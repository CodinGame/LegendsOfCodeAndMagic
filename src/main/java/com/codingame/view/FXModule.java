package com.codingame.view;

import java.util.ArrayList;
import java.util.List;

import com.codingame.game.Player;
import com.codingame.gameengine.core.Module;
import com.codingame.gameengine.core.MultiplayerGameManager;
import com.codingame.gameengine.module.entities.Text;
import com.google.inject.Inject;

public class FXModule implements Module {

    MultiplayerGameManager<Player> gameManager;
    List<Text> nicknames = new ArrayList<>(2);

    @Inject
    public FXModule(MultiplayerGameManager<Player> gameManager) {
        this.gameManager = gameManager;
        gameManager.registerModule(this);

    }

    @Override
    public void onGameInit() {
        gameManager.setViewGlobalData("fx", nicknames.stream().mapToInt(n -> n.getId()).toArray());
    }

    public void registerNickname(Text nick) {
        nicknames.add(nick);
    }

    @Override
    public void onAfterGameTurn() {

    }

    @Override
    public void onAfterOnEnd() {

    }

}
