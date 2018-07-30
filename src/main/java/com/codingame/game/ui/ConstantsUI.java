package com.codingame.game.ui;

public class ConstantsUI {
    public static final Vector2D SCREEN_DIM = new Vector2D(1920, 1080);
    public static final Vector2D CARD_DIM   = new Vector2D(210,  260);

    public static final int CARD_BOARD_SPACE = 50;
    public static final int CARD_HAND_SPACE  = 20;

    public static final Vector2D[] CARD_KEYWORDS = {
        new Vector2D(30,  205),
        new Vector2D(60,  205),
        new Vector2D(90,  205),
        new Vector2D(120, 205),
        new Vector2D(150, 205),
        new Vector2D(180, 205)
    };

    public static final String[] CARD_KEYWORDS_IMAGES = {
        "B.png",
        "C.png",
        "D.png",
        "G.png",
        "L.png",
        "W.png"
    };

    public static final Vector2D[] CARD_EXTRAS = {
        new Vector2D(30,  237),
        new Vector2D(95,  237),
        new Vector2D(155, 237)
    };

    public static final Vector2D BOARD     = new Vector2D(1210, SCREEN_DIM.y / 2);
    public static final Vector2D BOARD_DIM = new Vector2D(1435, 644);

    public static final Vector2D PLAYER_OFFSET = new Vector2D(0, 610);

    public static final Vector2D PLAYER_AVATAR     = new Vector2D(229, 112);
    public static final Vector2D PLAYER_AVATAR_DIM = new Vector2D(137, 137);
    public static final Vector2D PLAYER_DRAW_TXT = new Vector2D(378, 343); // new Vector2D(378, 369);
    public static final Vector2D PLAYER_DECK_POS = new Vector2D(326, 307);
    public static final int PLAYER_DECK_OFFSET = 606;
    public static final Vector2D PLAYER_DECK_TXT = new Vector2D(378, 395);
    public static final Vector2D PLAYER_DECK_DIM   = new Vector2D(100, 131);
    public static final Vector2D PLAYER_FRAME_DIM  = new Vector2D(183, 183);
    public static final Vector2D PLAYER_HEALTH_TXT = new Vector2D(95,  370);
    public static final Vector2D PLAYER_MANA_TXT = new Vector2D(245, 370);
    public static final Vector2D PLAYER_NICK_TXT = new Vector2D(229, 250);

    public static final Vector2D[] PLAYER_RUNES = {
        new Vector2D(95,  434),
        new Vector2D(144, 415),
        new Vector2D(157, 367),
        new Vector2D(144, 322),
        new Vector2D(95,  304)
    };
    
    public static final Vector2D[] PLAYER_BUBBLES_POSITION = {
        new Vector2D(229, 539),
        new Vector2D(229, 531)
    };
    
    public static final Vector2D PLAYER_LONG_BUBBLES_TEXT_POSITION = new Vector2D(0, 27);
    public static final Vector2D PLAYER_BUBBLES_TEXT_POSITION = new Vector2D(0, 58);

    public static final double CARD_BOARD_SCALE = 1.0;
    public static final double LIFTED_CARD_BOARD_SCALE = 1.2;
    public static final double CARD_DRAFT_SCALE = 1.3;
    public static final double CARD_DECK_SCALE  = (double) PLAYER_DECK_DIM.y / (double) CARD_DIM.y;
    public static final double CARD_HAND_SCALE  = 0.77;

    public static final Vector2D CARD_BOARD_DIM = Vector2D.mult(CARD_DIM, CARD_BOARD_SCALE);
    public static final Vector2D CARD_DRAFT_DIM = Vector2D.mult(CARD_DIM, CARD_DRAFT_SCALE);
    public static final Vector2D CARD_DECK_DIM  = Vector2D.mult(CARD_DIM, CARD_BOARD_SCALE);
    public static final Vector2D CARD_HAND_DIM  = Vector2D.mult(CARD_DIM, CARD_HAND_SCALE);
    public static final Vector2D ZOOM_OFFSET = new Vector2D(
        -(int) (ConstantsUI.CARD_BOARD_DIM.x * (ConstantsUI.LIFTED_CARD_BOARD_SCALE - ConstantsUI.CARD_BOARD_SCALE) / 2),
        -(int) (ConstantsUI.CARD_BOARD_DIM.y * (ConstantsUI.LIFTED_CARD_BOARD_SCALE - ConstantsUI.CARD_BOARD_SCALE) / 2)
        );

    public static final int MC_GRAPH_LOWY = SCREEN_DIM.y - 40;
    public static final int MC_COST_FONTSIZE = 30;
    public static final int MC_QUANTITY_FONTSIZE = 24;
    public static final int MC_COST_WIDTH = 100;
    public static final int MC_GRAPH_WIDTH = 80;
    public static final int MC_COST_X = 470 + (SCREEN_DIM.x-470 - 7*MC_COST_WIDTH)/2;
    public static final int MC_GRAPH_STEP = 25;
    public static final int MC_GRAPH_MAXSIZE = 175;
    public static final int MC_GRAPH_ZEROSIZE = 2;
    public static final int[] MC_PLAYERS_OFFSET = {0, -860};
}
