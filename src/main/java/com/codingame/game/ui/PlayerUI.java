package com.codingame.game.ui;

import com.codingame.game.Player;
import com.codingame.game.engine.ActionResult;
import com.codingame.game.engine.Constants;
import com.codingame.game.engine.Gamer;
import com.codingame.gameengine.module.entities.Curve;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.codingame.gameengine.module.entities.Group;
import com.codingame.gameengine.module.entities.Sprite;
import com.codingame.gameengine.module.entities.Text;

public class PlayerUI {
    private static final int[] CHARS_TO_CROP = new int[] {22, 45};
    private GraphicEntityModule graphicEntityModule;
    private Player player;

    private Sprite avatar;
    private Sprite[] frame = new Sprite[2];
    private Sprite[] runes = new Sprite[5];
    private Text draw;
    private Text deck;
    private Text health;
    private Text mana;
    private Text nick;

    private Text damageFloat, healFloat;
    private Sprite impact, heal;
    private Group bubble;
    private Text bubbleText;
    private Sprite bubbleSprite;

    private Vector2D bubblePosition;

    public PlayerUI(GraphicEntityModule graphicEntityModule, Player player)
    {
        this.graphicEntityModule = graphicEntityModule;
        this.player = player;

        int playerIndex = player.getIndex();

        Vector2D offset = Vector2D.mult(ConstantsUI.PLAYER_OFFSET, 1 - playerIndex);
        bubblePosition = ConstantsUI.PLAYER_BUBBLES_POSITION[1 - playerIndex];

        avatar = graphicEntityModule.createSprite()
            .setAnchor(0.5)
            .setBaseHeight(ConstantsUI.PLAYER_AVATAR_DIM.y)
            .setBaseWidth(ConstantsUI.PLAYER_AVATAR_DIM.x)
            .setImage(player.getAvatarToken())
            .setX(Vector2D.add(ConstantsUI.PLAYER_AVATAR, offset).x)
            .setY(Vector2D.add(ConstantsUI.PLAYER_AVATAR, offset).y);

        draw = graphicEntityModule.createText("")
            .setAnchor(0.5)
            .setFillColor(0xffffff)
            .setFontSize(40)
            .setStrokeColor(0x000000)
            .setStrokeThickness(4.0)
            .setX(Vector2D.add(ConstantsUI.PLAYER_DRAW_TXT, offset).x)
            .setY(Vector2D.add(ConstantsUI.PLAYER_DRAW_TXT, offset).y);

        deck = graphicEntityModule.createText("")
                .setAnchor(0.5)
                .setFillColor(0xffffff)
                .setFontSize(40)
                .setStrokeColor(0x000000)
                .setStrokeThickness(4.0)
                .setX(Vector2D.add(ConstantsUI.PLAYER_DECK_TXT, offset).x)
                .setY(Vector2D.add(ConstantsUI.PLAYER_DECK_TXT, offset).y);

        frame[0] = graphicEntityModule.createSprite()
            .setAlpha(0)
            .setAnchor(0.5)
            .setBaseHeight(ConstantsUI.PLAYER_FRAME_DIM.y)
            .setBaseWidth(ConstantsUI.PLAYER_FRAME_DIM.x)
            .setImage("playerframe-active.png")
            .setX(Vector2D.add(ConstantsUI.PLAYER_AVATAR, offset).x)
            .setY(Vector2D.add(ConstantsUI.PLAYER_AVATAR, offset).y);

        frame[1] = graphicEntityModule.createSprite()
            .setAnchor(0.5)
            .setBaseHeight(ConstantsUI.PLAYER_FRAME_DIM.y)
            .setBaseWidth(ConstantsUI.PLAYER_FRAME_DIM.x)
            .setImage("playerframe-inactive.png")
            .setX(Vector2D.add(ConstantsUI.PLAYER_AVATAR, offset).x)
            .setY(Vector2D.add(ConstantsUI.PLAYER_AVATAR, offset).y);

        health = graphicEntityModule.createText("")
            .setAnchor(0.5)
            .setFillColor(0xffffff)
            .setFontSize(40)
            .setStrokeColor(0x000000)
            .setStrokeThickness(4.0)
            .setX(Vector2D.add(ConstantsUI.PLAYER_HEALTH_TXT, offset).x)
            .setY(Vector2D.add(ConstantsUI.PLAYER_HEALTH_TXT, offset).y);

        damageFloat = graphicEntityModule.createText("")
            .setAnchor(0.5)
            .setFillColor(0xffffff)
            .setFontSize(36)
            .setStrokeColor(0x000000)
            .setStrokeThickness(4.0)
            .setZIndex(2);

        impact = graphicEntityModule.createSprite()
            .setAlpha(0)
            .setAnchor(.5)
            .setImage("impact.png")
            .setZIndex(1);

        healFloat = graphicEntityModule.createText("")
            .setAnchor(0.5)
            .setFillColor(0xffffff)
            .setFontSize(36)
            .setStrokeColor(0x000000)
            .setStrokeThickness(4.0)
            .setZIndex(2);

        heal = graphicEntityModule.createSprite()
            .setAlpha(0)
            .setAnchor(.5)
            .setImage("heal.png")
            .setZIndex(1);

        bubbleText = graphicEntityModule.createText("")
            .setFillColor(player.getColorToken())
            .setFontSize(30)
            .setZIndex(2)
            .setAnchor(.5)
            .setX(ConstantsUI.PLAYER_LONG_BUBBLES_TEXT_POSITION.x)
            .setY((playerIndex == 0 ? 1 : -1) * ConstantsUI.PLAYER_LONG_BUBBLES_TEXT_POSITION.y)
            .setFontFamily("Monospace");

        bubbleSprite = graphicEntityModule.createSprite()
            .setAlpha(0)
            .setZIndex(1)
            .setScale(playerIndex == 0 ? 1 : -1)
            .setAnchorX(0.5);

        bubble = graphicEntityModule.createGroup(
            bubbleSprite,
            bubbleText
        );

        graphicEntityModule.createGroup(impact, damageFloat)
            .setZIndex(1)
            .setX(avatar.getX())
            .setY(avatar.getY());

        graphicEntityModule.createGroup(heal, healFloat)
            .setZIndex(1)
            .setX(avatar.getX())
            .setY(avatar.getY());

        mana = graphicEntityModule.createText("")
            .setAnchor(0.5)
            .setFillColor(0xffffff)
            .setFontSize(40)
            .setStrokeColor(0x000000)
            .setStrokeThickness(4.0)
            .setX(Vector2D.add(ConstantsUI.PLAYER_MANA_TXT, offset).x)
            .setY(Vector2D.add(ConstantsUI.PLAYER_MANA_TXT, offset).y);

        nick = graphicEntityModule.createText(player.getNicknameToken())
            .setAnchor(0.5)
            .setFillColor(player.getColorToken())
            .setFontSize(60)
            .setStrokeColor(0x000000)
            .setStrokeThickness(4.0)
            .setX(Vector2D.add(ConstantsUI.PLAYER_NICK_TXT, offset).x)
            .setY(Vector2D.add(ConstantsUI.PLAYER_NICK_TXT, offset).y);
        

        for (int index = 0; index < 5; ++index)
            runes[index] = graphicEntityModule
                .createSprite()
                .setAlpha(0)
                .setAnchor(0.5)
                .setImage("rune.png")
                .setX(Vector2D.add(ConstantsUI.PLAYER_RUNES[index], offset).x)
                .setY(Vector2D.add(ConstantsUI.PLAYER_RUNES[index], offset).y);
    }

    public void attacker(ActionResult result)
    {
        handleHealthChange(result.attackerHealthChange);
    }

    public void defender(ActionResult result)
    {
        handleHealthChange(result.defenderHealthChange);
    }

    public PlayerUI updateStats(Gamer gamer)
    {
        draw.setText("+" + Integer.toString(gamer.nextTurnDraw));
        if (gamer.hand.size()+gamer.nextTurnDraw > Constants.MAX_CARDS_IN_HAND) // show overdraw
            draw.setFillColor(0xff5500);
        else
            draw.setFillColor(0xffffff);

        deck.setText(Integer.toString(gamer.deck.size())); // show empty deck approaching
        if (gamer.deck.size() == 0)
            deck.setFillColor(0xff0000);
        else if (gamer.deck.size() <= 5)
            deck.setFillColor(0xff5500);

        damageFloat.setText("");
        healFloat.setText("");
        hideImpact();
        hideHeal();
        health.setText(Integer.toString(gamer.health));
        mana.setText(Integer.toString(gamer.currentMana) + "/" + Integer.toString(gamer.maxMana));

        for (int index = 0; index < 5; ++index)
            runes[index].setAlpha(gamer.runes.size() > index ? 1 : 0);

        return this;
    }

    public PlayerUI setActive(boolean active)
    {
        frame[0].setAlpha(active ? 1 : 0, Curve.IMMEDIATE);
        frame[1].setAlpha(active ? 0 : 1, Curve.IMMEDIATE);
        return this;
    }

    private void handleHealthChange(int healthChange) {
        if (healthChange > 0) {
            healFloat.setText(Integer.toString(healthChange));
            displayHeal();
            hideImpact();
        } else if (healthChange < 0) {
            damageFloat.setText(Integer.toString(healthChange));
            displayImpact();
            hideHeal();
        } else {
            hideImpact();
            hideHeal();
        }
    }

    private void displayImpact() {
        impact.setAlpha(1, Curve.NONE);
        graphicEntityModule.commitEntityState(0.5, damageFloat, impact);
    }
    private void displayHeal() {
        heal.setAlpha(1, Curve.NONE);
        graphicEntityModule.commitEntityState(0.5, healFloat, heal);
    }

    private void hideImpact() {
        impact.setAlpha(0, Curve.IMMEDIATE);
        damageFloat.setText("");
        graphicEntityModule.commitEntityState(0, damageFloat, impact);
    }
    private void hideHeal() {
        heal.setAlpha(0, Curve.IMMEDIATE);
        healFloat.setText("");
        graphicEntityModule.commitEntityState(0, healFloat, heal);
    }

    public void talk(String text, boolean draftPhase) {
        int playerIndex = player.getIndex();

        bubbleSprite.setAlpha(1, Curve.NONE);

        if (draftPhase) {
            bubble.setX(bubblePosition.x, Curve.IMMEDIATE).setY(bubblePosition.y, Curve.IMMEDIATE);
            bubbleSprite.setImage("bubble_long.png");
            if (text.length() > 22) {
                bubbleText.setFontSize(26);
                if (text.length() > 25) {
                    text = text.substring(0, 23) + "...";
                }
            } else {
                bubbleText.setFontSize(30);
            }
        } else {
            bubbleSprite.setImage("bubble.png");
            bubble.setX(bubblePosition.x, Curve.IMMEDIATE).setY(bubblePosition.y + (playerIndex == 0 ? -1 : 1) * 60, Curve.IMMEDIATE);
            bubbleText.setFontSize(30)
                .setY((playerIndex == 0 ? 1 : -1) * ConstantsUI.PLAYER_BUBBLES_TEXT_POSITION.y, Curve.IMMEDIATE);

            for (int i : CHARS_TO_CROP) {
                if (text.length() > i) {
                    text = text.substring(0, i) + "\n" + text.substring(i, text.length());
                }
            }
            if (text.length() > 67) {
                text = text.substring(0, 65) + "...";
            }
        }

        bubbleText.setText(text);
        graphicEntityModule.commitEntityState(0, bubbleText, bubbleSprite);
    }

    public void hideBubble() {
        bubbleSprite.setAlpha(0, Curve.NONE);
        bubbleText.setText("");
        graphicEntityModule.commitEntityState(0, bubbleText, bubbleSprite);
    }

    public Text getNick() {
        return nick;
    }
}
