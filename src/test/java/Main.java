import java.util.Properties;

import com.codingame.game.engine.Constants;
import com.codingame.gameengine.runner.MultiplayerGameRunner;

public class Main {
    public static void main(String[] args) {
        MultiplayerGameRunner gameRunner = new MultiplayerGameRunner();
        
        Properties gameParameters = new Properties();
        // set game parameters here
        //        gameRunner.setSeed(1279960l);
        //        gameParameters.setProperty("draftChoicesSeed", "-5113144502819146988");
        //        gameParameters.setProperty("shufflePlayer0Seed", "127");
        //        gameParameters.setProperty("shufflePlayer1Seed", "333");
        //        gameParameters.setProperty("predefinedDraftIds", "91 92 93,94 95 96,97 98 99,100 101 102,103 104 105,106 107 108,109 110 111,112 113 114,115 116 117,118 119 120,121 122 123,124 125 126,127 128 129,130 131 132,133 134 135,136 137 138,139 140 141,142 143 144,145 146 147,148 149 150,151 152 153,154 155 156,157 158 159,160 160 160,160 160 160,160 160 160,160 160 160,160 160 160,160 160 160,160 160 160");
        gameRunner.setGameParameters(gameParameters);

        gameRunner.addAgent(PlayerEmpty.class);
        gameRunner.addAgent(PlayerEmpty.class);

        Constants.VERBOSE_LEVEL = 2;
        
        //set ruleset here
        System.setProperty("league.level", "4");
        
        gameRunner.start();
    }

}
