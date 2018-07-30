package com.codingame.game.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.codingame.game.Player;
import com.codingame.game.engine.Action;
import com.codingame.game.engine.Card;
import com.codingame.game.engine.Card.Type;
import com.codingame.game.engine.Constants;
import com.codingame.game.engine.CreatureOnBoard;
import com.codingame.game.engine.EngineReferee;
import com.codingame.game.engine.Gamer;
import com.codingame.gameengine.core.MultiplayerGameManager;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.codingame.gameengine.module.entities.Rectangle;
import com.codingame.gameengine.module.entities.Sprite;
import com.codingame.gameengine.module.entities.Text;
import com.codingame.view.FXModule;
import com.codingame.view.tooltip.TooltipModule;

public class RefereeUI {
    private static final double EPSILON = 0.001;

    public EngineReferee engine;
    public MultiplayerGameManager<Player> gameManager;
    public GraphicEntityModule graphicEntityModule;
    public FXModule fxModule;
    
    private TooltipModule tooltipModule;

    private Map<Integer, CardUI> cardsPool = new HashMap<>();
    private static PlayerUI[] players = new PlayerUI[2];

    private Text[] deck = new Text[2];

    private Text[][] manaCurveCosts = new Text[2][8];
    private Rectangle[][] manaCurve = new Rectangle[2][8];
    private Text[][] manaCurveQuantity = new Text[2][8];

    private Sprite newTurnBackground;
    private Text newTurn;
    private int lastturn = -1;


    public void init() {
        tooltipModule = new TooltipModule(gameManager);
        gameManager.setFrameDuration(Constants.FRAME_DURATION_DRAFT);

        graphicEntityModule.createSpriteSheetLoader()
            .setName("atlas-")
            .setSourceImage("atlas.png")
            .setWidth(600 / 4)
            .setHeight(740 / 4)
            .setOrigRow(0)
            .setOrigCol(0)
            .setImageCount(160)
            .setImagesPerRow(10)
            .load();

        graphicEntityModule.createSprite()
            .setAnchor(0)
            .setImage("board.jpg")
            .setX(0)
            .setY(0);

        newTurnBackground = graphicEntityModule.createSprite()
                .setAnchor(0)
                .setImage("newturn.png")
                .setX(78)
                .setY(482)
                .setAlpha(0);

        for (Player player : gameManager.getPlayers()) {
            players[player.getIndex()] = new PlayerUI(graphicEntityModule, player);
            fxModule.registerNickname(players[player.getIndex()].getNick());
        }
    }

    public void draft(int turn) {
        if (turn == 0) {
            newTurn = graphicEntityModule.createText("");
            deck[0] = graphicEntityModule.createText("");
            deck[1] = graphicEntityModule.createText("");
            initManaCurve();
        }

        for (CardUI card : cardsPool.values())
            card.setVisible(false);

        int draftX = ConstantsUI.BOARD.x - (int) (ConstantsUI.CARD_DIM.x * 1.5 * ConstantsUI.CARD_DRAFT_SCALE) - ConstantsUI.CARD_BOARD_SPACE - 140; // handmade value
        int draftY = ConstantsUI.BOARD.y;
        int draftCardSpace = (int) ((ConstantsUI.CARD_BOARD_SPACE + ConstantsUI.CARD_DIM.x) * ConstantsUI.CARD_DRAFT_SCALE);

        Card[] pick = engine.draft.draft[turn];

        for (int index = 0; index < 3; ++index) {
            int cardX = draftX + draftCardSpace * index - ConstantsUI.CARD_BOARD_SPACE;
            int cardY = draftY - (int) (ConstantsUI.CARD_DIM.y / 2 * ConstantsUI.CARD_DRAFT_SCALE);

            Card card = pick[index];
            getCardFromPool(-(index + 1))
                .setScale(ConstantsUI.CARD_DRAFT_SCALE)
                .move(cardX, cardY, card)
                .commit(0.0);

            for (Player player : gameManager.getPlayers()) {
                int playerIndex = player.getIndex();
                Vector2D offset = Vector2D.mult(ConstantsUI.PLAYER_OFFSET, 1 - playerIndex);

                deck[1 - playerIndex]
                    .setText(Integer.toString(1 + turn))
                    .setAnchor(0.5)
                    .setFillColor(0xffffff)
                    .setFontSize(40)
                    .setStrokeColor(0x000000)
                    .setStrokeThickness(4.0)
                    .setX(Vector2D.add(ConstantsUI.PLAYER_DECK_TXT, offset).x)
                    .setY(Vector2D.add(ConstantsUI.PLAYER_DECK_TXT, offset).y);

                if (engine.draft.chosenCards[playerIndex].get(turn).baseId != pick[index].baseId)
                    continue;

                if (!engine.draft.text[playerIndex].isEmpty()) {
                    players[playerIndex].talk(engine.draft.text[playerIndex], true);
                } else {
                    players[playerIndex].hideBubble();
                }

                this.getCardFromPool(-(playerIndex + 4))
                    .lift(1)
                    .setScale(ConstantsUI.CARD_DRAFT_SCALE)
                    .move(cardX, cardY, card)
                    .commit(0.0)
                    .setScale(ConstantsUI.CARD_DECK_SCALE)
                    .move(
                        ConstantsUI.PLAYER_DECK_POS.x,
                        ConstantsUI.PLAYER_DECK_POS.y + ConstantsUI.PLAYER_DECK_OFFSET*(1 - playerIndex),
                        card
                    )
                    .commit(1.0);
            }
        }

        newTurnBackground
                .setX(ConstantsUI.SCREEN_DIM.x - 314 - 20)
                .setY(482)
                .setAlpha(1);

        newTurn
            .setText("Draft: " + Integer.toString(1 + turn) + "/30")
            .setAnchor(0.5)
            .setFontSize(50)
            .setFillColor(0xffffff)
            .setStrokeColor(0x000000)
            .setStrokeThickness(4.0)
            .setX(ConstantsUI.SCREEN_DIM.x - (314/2) - 20)
            .setY(draftY);
        
        graphicEntityModule.commitEntityState(0,  newTurnBackground, newTurn);
        drawManaCurve();
    }

    private void initManaCurve()
    {
        for (int p=0; p<2; p++)
        {
            for (int m = 0; m < 8; m++)
            {
                manaCurveCosts[p][m] = graphicEntityModule.createText(m < 7 ? Integer.toString(m) : "7+")
                        .setAnchor(0.5)
                        .setFontSize(ConstantsUI.MC_COST_FONTSIZE)
                        .setFillColor(0xffffff)
                        .setStrokeColor(0x000000)
                        .setStrokeThickness(4.0)
                        .setX(ConstantsUI.MC_COST_X + (m * ConstantsUI.MC_COST_WIDTH) + ConstantsUI.MC_COST_WIDTH / 2)
                        .setY(ConstantsUI.MC_PLAYERS_OFFSET[p] + ConstantsUI.MC_GRAPH_LOWY + (ConstantsUI.SCREEN_DIM.y - ConstantsUI.MC_GRAPH_LOWY) / 2);

            manaCurve[p][m] = graphicEntityModule.createRectangle()
                    .setFillColor(0x4d79d0)
                    .setHeight(0)
                    .setWidth(ConstantsUI.MC_GRAPH_WIDTH)
                    .setX(ConstantsUI.MC_COST_X + m * ConstantsUI.MC_COST_WIDTH + (ConstantsUI.MC_COST_WIDTH-ConstantsUI.MC_GRAPH_WIDTH)/2)
                    .setY(ConstantsUI.MC_PLAYERS_OFFSET[p] + ConstantsUI.MC_GRAPH_LOWY);

            manaCurveQuantity[p][m] = graphicEntityModule.createText("")
                    .setAnchor(0.5)
                    .setFontSize(ConstantsUI.MC_QUANTITY_FONTSIZE)
                    .setFillColor(0xffffff)
                    .setStrokeColor(0x000000)
                    .setStrokeThickness(4.0)
                    .setX(ConstantsUI.MC_COST_X + (m * ConstantsUI.MC_COST_WIDTH) + ConstantsUI.MC_COST_WIDTH/2)
                    .setY(ConstantsUI.MC_PLAYERS_OFFSET[p] + ConstantsUI.MC_GRAPH_LOWY + (ConstantsUI.SCREEN_DIM.y - ConstantsUI.MC_GRAPH_LOWY)/2);
            }
        }
    }

    private void drawManaCurve()
    {
        for (int p=0; p<2; p++)
        {
            int[] mc = new int[8];

            for (Card c : engine.draft.chosenCards[p])
            {
                if (c.cost < 7) mc[c.cost]++;
                else            mc[7]++;
            }
            int maxmc = Arrays.stream(mc).max().getAsInt();
            boolean overflow = maxmc  * ConstantsUI.MC_GRAPH_STEP > ConstantsUI.MC_GRAPH_MAXSIZE;

            for (int m = 0; m < 8; m++)
            {
                int h = mc[m] * ConstantsUI.MC_GRAPH_STEP;
                if (overflow)
                    h = ConstantsUI.MC_GRAPH_MAXSIZE * mc[m] / maxmc;
                if (h == 0)
                    h = ConstantsUI.MC_GRAPH_ZEROSIZE;

                manaCurve[p][m]
                        .setHeight(h)
                        .setY(ConstantsUI.MC_PLAYERS_OFFSET[p] + ConstantsUI.MC_GRAPH_LOWY - h);

                int texty = ConstantsUI.MC_GRAPH_LOWY - h + ConstantsUI.MC_QUANTITY_FONTSIZE / 2; // text below
                if (h < ConstantsUI.MC_QUANTITY_FONTSIZE)
                    texty = ConstantsUI.MC_GRAPH_LOWY - h - ConstantsUI.MC_QUANTITY_FONTSIZE / 2; // text above

                manaCurveQuantity[p][m]
                        .setText(mc[m] > 0 ? Integer.toString(mc[m]) : "")
                        .setY(ConstantsUI.MC_PLAYERS_OFFSET[p] + texty);
            }
        }
    }

    public void battle(int turn) {
        for (int playerIndex = 0; playerIndex < 2; ++playerIndex) {
            players[playerIndex].hideBubble();
        }

        newTurn.setAlpha(0);
        deck[0].setAlpha(0);
        deck[1].setAlpha(0);

        for (int p=0; p<2; p++)
        {
            for (int m = 0; m < 8; m++)
            {
                manaCurveCosts[p][m].setAlpha(0);
                manaCurve[p][m].setAlpha(0);
                manaCurveQuantity[p][m].setAlpha(0);
            }
        }

        if (turn != lastturn)
        {
            lastturn = turn;

            newTurnBackground
                    .setX(78)
                    .setAlpha(1);

            newTurn
                    .setText("Turn " + Integer.toString(turn/2 - 14))
                    .setAnchor(0.5)
                    .setFontSize(50)
                    .setFillColor(gameManager.getPlayers().get(turn%2).getColorToken())
                    .setStrokeColor(0x000000)
                    .setStrokeThickness(4.0)
                    .setX(78+314/2)
                    .setY(ConstantsUI.BOARD.y)
                    .setAlpha(1);
        }
        else
        {
            newTurnBackground.setAlpha(0);
            newTurn.setText("");
        }

        graphicEntityModule.commitEntityState(0,  newTurn, newTurnBackground);
        
        for (CardUI card : cardsPool.values())
            card.setVisible(false);


        for (Player player : gameManager.getPlayers()) {
            int playerIndex = player.getIndex();
            Vector2D offset = Vector2D.mult(ConstantsUI.PLAYER_OFFSET, 1 - playerIndex);

            // Expose an API in the engine?
            Gamer gamer = engine.state.players[playerIndex];

            // Update player.
            players[playerIndex]
                .setActive(turn % 2 == playerIndex)
                .updateStats(gamer);

            // Update board.
            ArrayList<CreatureOnBoard> board = gamer.board;

            int boardX = ConstantsUI.BOARD.x;
            int boardY = ConstantsUI.BOARD.y + (int) (ConstantsUI.BOARD_DIM.y * (0.5 - playerIndex) / 2.0);
            int bcw = (int) (ConstantsUI.CARD_BOARD_SCALE * ConstantsUI.CARD_DIM.x * (board.size() > 5 ? 0.8 : 1));
            int bch = (int) (ConstantsUI.CARD_BOARD_SCALE * ConstantsUI.CARD_DIM.y);
            int bcs = ConstantsUI.CARD_BOARD_SPACE;
            int boardCenterX = ((bcw + bcs) * board.size() + (board.size() % 2) * bcs / 2) / 2;

            for (int index = 0; index < board.size(); ++index) {
                int cardXOffset = (bcw + bcs) * index;
                int cardX = boardX - boardCenterX + cardXOffset;
                int cardY = boardY - bch / 2;

                CreatureOnBoard card = board.get(index);
                Card base = Constants.CARDSET.values().stream()
                    .filter(x -> x.baseId == card.baseId)
                    .findAny()
                    .get();

                getCardFromPool(card.id)
                    .setScale(ConstantsUI.CARD_BOARD_SCALE)
                    .move(cardX, cardY, base, card, true);
            }

            // Update hand.
            ArrayList<Card> hand = gamer.hand;

            int handX = ConstantsUI.BOARD.x;
            int handY = ConstantsUI.BOARD.y + (ConstantsUI.BOARD_DIM.y + ConstantsUI.CARD_HAND_DIM.y + 20) * ((1 - playerIndex) * 2 - 1) / 2;
            int hcw = (int) (ConstantsUI.CARD_HAND_SCALE * ConstantsUI.CARD_DIM.x);
            int hch = (int) (ConstantsUI.CARD_HAND_SCALE * ConstantsUI.CARD_DIM.y);
            int hcs = ConstantsUI.CARD_HAND_SPACE;
            int handCenterX = ((hcw + hcs) * hand.size() + (hand.size() % 2) * hcs / 2) / 2;

            for (int index = 0; index < hand.size(); ++index) {
                int cardXOffset = (hcw + hcs) * index;
                int cardX = handX - handCenterX + cardXOffset;
                int cardY = handY - hch / 2;

                Card card = hand.get(index);
                CardUI cardUI = getCardFromPool(card.id);

                if (cardUI.getX() == 0) {
                    cardUI
                        .lift()
                        .setScale(ConstantsUI.CARD_DECK_SCALE)
                        .move(
                            Vector2D.add(Vector2D.sub(ConstantsUI.PLAYER_DRAW_TXT, Vector2D.div(ConstantsUI.PLAYER_DECK_DIM, 2)), offset).x,
                            Vector2D.add(Vector2D.sub(ConstantsUI.PLAYER_DRAW_TXT, Vector2D.div(ConstantsUI.PLAYER_DECK_DIM, 2)), offset).y,
                            card
                        )
                        .commit(0.0);
                }

                cardUI
                    .setScale(ConstantsUI.CARD_HAND_SCALE)
                    .move(cardX, cardY, card)
                    .ground();
            }
        }

        int playerIndex = turn % 2;
        Vector2D offset = Vector2D.mult(ConstantsUI.PLAYER_OFFSET, playerIndex);

        ArrayList<Action> actions = engine.state.players[playerIndex].performedActions;
        Action action = actions.size() == 0 ? null : actions.get(actions.size() - 1);

        if (action != null && action.text != null && !action.text.isEmpty()) {
            players[playerIndex].talk(action.text, false);
        } else {
            players[playerIndex].hideBubble();
        }

        if (action != null && (action.type == Action.Type.ATTACK || action.type == Action.Type.USE)) {
            if (actionPlayedOnSelf(action)) {
                offset = Vector2D.mult(ConstantsUI.PLAYER_OFFSET, 1 - playerIndex);
            }
            CardUI card1 = cardsPool.get(action.arg1);
            CardUI card2 = action.arg2 == -1 ? null : cardsPool.get(action.arg2);

            if (actionDoesntAffectTargetCreature(action)) {
                card2 = null;
            }

            int x1 = card1.getX();
            int y1 = card1.getY();

            Card card = engine.state.cardIdMap.get(action.arg1);
            Optional<CreatureOnBoard> creature = engine.state.players[playerIndex].board.stream()
                .filter(x -> x.id == card.id)
                .findAny();

            int x2 = card2 == null
                ? ConstantsUI.PLAYER_AVATAR.x - card1.getWidth() / 2 + offset.x
                : card2.getX() + (card2.getWidth() - card1.getWidth()) / 2;
            int y2 = card2 == null
                ? ConstantsUI.PLAYER_AVATAR.y - card1.getHeight() / 2 + offset.y
                : card2.getY() + (card2.getHeight() - card1.getHeight()) / 2;


            if (creature.isPresent()) {
                animateAttackAndLive(card1, card, creature.get(), action, card.id, x1, y1, x2, y2);
            } else {
                Optional<CreatureOnBoard> graveyardCreature = engine.state.players[playerIndex].graveyard.stream()
                    .filter(x -> x.id == card.id)
                    .findAny();
                CreatureOnBoard realCreature = graveyardCreature.isPresent() ? graveyardCreature.get() : new CreatureOnBoard(card);
                boolean isOnBoard = graveyardCreature.isPresent();
                if (!isOnBoard) {
                    animateUse(card1, card, realCreature, action, card.id, x1, y1, x2, y2);
                } else {
                    animateAttackAndDie(card1, card, realCreature, action, card.id, x1, y1, x2, y2);
                }
            }


            if (card2 != null) {
                Optional<CreatureOnBoard> graveyardCreature2 = engine.state.players[1 - playerIndex].graveyard.stream()
                    .filter(x -> x.id == action.arg2)
                    .findAny();
                if (graveyardCreature2.isPresent()) {
                    forceCommitAlpha1(card2, 0.5);
                    card2.setVisible(false);
                    card2.commit(1);
                }
                card2.action(action.result, action.arg2);
            }
        }
        if (action != null) {
            players[playerIndex].attacker(action.result);
            players[1 - playerIndex].defender(action.result);
        }
    }

    private boolean actionPlayedOnSelf(Action action) {
        Card card = engine.state.cardIdMap.get(action.arg1);

        return card.type == Type.ITEM_BLUE &&
            action.result.attackerHealthChange > 0 &&
            action.result.defenderHealthChange == 0;
    }

    private boolean actionDoesntAffectTargetCreature(Action action) {
        Card card = engine.state.cardIdMap.get(action.arg1);

        return card.type == Type.ITEM_BLUE &&
            action.result.defenderDefenseChange == 0
            && action.result.defenderAttackChange == 0;
    }

    private void animateUse(CardUI cardUI, Card card, CreatureOnBoard creature, Action action, int attackerId, int x1, int y1, int x2, int y2) {
        cardUI
        .lift()
        .action(action.result, card.id)
        .move(x1, y1, card, creature, false)
        .commitGroup(0.0)
        .zoom(x1, y1, (cardUI.getScaleX() + cardUI.getScaleY()) / 2)
        .commitGroup(0.1)
        .move(x2, y2, card, creature, false)
        .setVisible(true)
        .commit(0.5)
        .ground()
        .setVisible(false)
        .commit(1);
    }

    private void animateAttackAndDie(CardUI cardUI, Card card, CreatureOnBoard creature, Action action, int attackerId, int x1, int y1, int x2, int y2) {
        cardUI
            .lift()
            .action(action.result, card.id)
            .move(x1, y1, card, creature, true)
            .commitGroup(0.0)
            .zoom(x1 + ConstantsUI.ZOOM_OFFSET.x, y1 + ConstantsUI.ZOOM_OFFSET.y, ConstantsUI.LIFTED_CARD_BOARD_SCALE)
            .commitGroup(0.1)
            .move(x2 + ConstantsUI.ZOOM_OFFSET.x, y2 + ConstantsUI.ZOOM_OFFSET.y, card, creature, true)
            .commitGroup(0.5)
            .move(x1 + ConstantsUI.ZOOM_OFFSET.x, y1 + ConstantsUI.ZOOM_OFFSET.y, card, creature, true)
            .ground()
            .setVisibility(0.1)
            .commitGroup(0.9)
            .zoom(x1, y1, ConstantsUI.CARD_BOARD_SCALE)
            .setVisible(false)
            .commit(1.0);
    }

    private void animateAttackAndLive(CardUI cardUI, Card card, CreatureOnBoard creature, Action action, int attackerId, int x1, int y1, int x2, int y2) {
        cardUI
        .lift()
        .action(action.result, attackerId)
        .move(x1, y1, card, creature, true)
        .commitGroup(0.0)
        .zoom(x1 + ConstantsUI.ZOOM_OFFSET.x, y1 + ConstantsUI.ZOOM_OFFSET.y, ConstantsUI.LIFTED_CARD_BOARD_SCALE)
        .commitGroup(0.1)
        .move(x2 + ConstantsUI.ZOOM_OFFSET.x, y2 + ConstantsUI.ZOOM_OFFSET.y, card, creature, true)
        .commitGroup(0.5)
        .move(x1 + ConstantsUI.ZOOM_OFFSET.x, y1 + ConstantsUI.ZOOM_OFFSET.y, card, creature, true)
        .ground()
        .commitGroup(0.9)
        .zoom(x1, y1, ConstantsUI.CARD_BOARD_SCALE)
        .commit(1.0);
    }

    /**
     * Work-around a limitation of the SDK in its current state. <br>
     * It is not possible to start value interpolation at a given t if the value has not changed at t
     * (unless a different property is changed too).<br>
     * This method changes the value of alpha by an imperceptable amount <br>
     * It assumes alpha is currently set to 1
     */
    private void forceCommitAlpha1(CardUI card, double t) {
        card.setVisibility(1-EPSILON);
        card.commit(t);
    }

    private CardUI getCardFromPool(int cardId) {
        if (cardsPool.containsKey(cardId))
            return cardsPool.get(cardId).setVisible(true);

        CardUI card = new CardUI(graphicEntityModule, tooltipModule);
        cardsPool.put(cardId, card);
        return card;
    }
}
