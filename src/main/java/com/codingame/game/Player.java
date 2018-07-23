package com.codingame.game;

import com.codingame.gameengine.core.AbstractMultiplayerPlayer;

public class Player extends AbstractMultiplayerPlayer {
    @Override
    public int getExpectedOutputLines() {
        return 1;
    }
}
