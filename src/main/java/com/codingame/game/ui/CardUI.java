package com.codingame.game.ui;

import java.util.HashMap;

import com.codingame.game.engine.ActionResult;
import com.codingame.game.engine.Card;
import com.codingame.game.engine.CreatureOnBoard;
import com.codingame.gameengine.module.entities.Curve;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.codingame.gameengine.module.entities.Group;
import com.codingame.gameengine.module.entities.Sprite;
import com.codingame.gameengine.module.entities.Text;
import com.codingame.view.tooltip.TooltipModule;

public class CardUI {
    private TooltipModule tooltipModule;
    private GraphicEntityModule graphicEntityModule;

    private Group group;
    private Sprite background;
    private Sprite overlay;
    private Sprite ward;
    private Sprite lethal;
    private Sprite shadow;
    private Sprite impact, heal;
    private Sprite[] keywords = new Sprite[6];
    private Text attack;
    private Text cost;
    private Text defense;
    private Text[] extras = new Text[3];
    private Text damageFloat, healFloat;
    private Sprite[] extraIcons = new Sprite[3];

    public CardUI(GraphicEntityModule graphicEntityModule, TooltipModule tooltipModule) {
        this.graphicEntityModule = graphicEntityModule;
        this.tooltipModule = tooltipModule;

        attack = graphicEntityModule.createText("0")
            .setAnchor(0.5)
            .setFillColor(0xffffff)
            .setFontSize(35)
            .setStrokeColor(0x000000)
            .setStrokeThickness(4.0)
            .setX(45 * 2 * 260 / 740)
            .setY(210 * 2 * 260 / 740)
            .setZIndex(2);

        background = graphicEntityModule.createSprite()
            .setBaseWidth(ConstantsUI.CARD_DIM.x)
            .setBaseHeight(ConstantsUI.CARD_DIM.y)
            .setZIndex(1);

        cost = graphicEntityModule.createText("0")
            .setAnchor(0.5)
            .setFillColor(0xffffff)
            .setFontSize(35)
            .setStrokeColor(0x000000)
            .setStrokeThickness(4.0)
            .setX(150 * 2 * 260 / 740)
            .setY(220 * 2 * 260 / 740)
            .setZIndex(2);

        defense = graphicEntityModule.createText("0")
            .setAnchor(0.5)
            .setFillColor(0xffffff)
            .setFontSize(35)
            .setStrokeColor(0x000000)
            .setStrokeThickness(4.0)
            .setX(255 * 2 * 260 / 740)
            .setY(210 * 2 * 260 / 740)
            .setZIndex(2);

        overlay = graphicEntityModule.createSprite()
            .setAlpha(1)
            .setBaseHeight(ConstantsUI.CARD_DIM.y)
            .setBaseWidth(ConstantsUI.CARD_DIM.x)
            .setImage("basic_overlay.png")
            .setZIndex(1);

        ward = graphicEntityModule.createSprite()
            .setAlpha(0)
            .setBaseHeight(ConstantsUI.CARD_DIM.y)
            .setBaseWidth(ConstantsUI.CARD_DIM.x)
            .setImage("ward.png")
            .setZIndex(3);

        lethal = graphicEntityModule.createSprite()
            .setAlpha(0)
            .setBaseWidth((int) (ConstantsUI.CARD_DIM.x * (28f/300)))
            .setBaseHeight((int) (ConstantsUI.CARD_DIM.y * (66f/370)))
            .setImage("lethal.png")
            .setZIndex(4)
            .setX((int) (ConstantsUI.CARD_DIM.x * (3f/300)))
            .setY((int) (ConstantsUI.CARD_DIM.y * (170f/370)));
        

        shadow = graphicEntityModule.createSprite()
            .setAlpha(0)
            .setAnchor(-0.045) // (-0.035)
            .setBaseHeight(ConstantsUI.CARD_DIM.y)
            .setBaseWidth(ConstantsUI.CARD_DIM.x)
            .setImage("shadow.png")
            //.setTint(0x808080)
            .setZIndex(0);
        
        impact = graphicEntityModule.createSprite()
            .setAlpha(0)
            .setAnchor(.5)
            .setImage("impact.png")
            .setZIndex(1);
        
        heal = graphicEntityModule.createSprite()
            .setAlpha(0)
            .setAnchor(.5)
            .setImage("heal.png")
            .setZIndex(1);

        damageFloat = graphicEntityModule.createText("")
            .setAnchor(0.5)
            .setFillColor(0xffffff)
            .setFontSize(36)
            .setStrokeColor(0x000000)
            .setStrokeThickness(2.0)
            .setZIndex(2);
        
        healFloat = graphicEntityModule.createText("")
            .setAnchor(0.5)
            .setFillColor(0xffffff)
            .setFontSize(36)
            .setStrokeColor(0x000000)
            .setStrokeThickness(2.0)
            .setZIndex(2);

        Group damageGroup = graphicEntityModule.createGroup(impact, damageFloat)
            .setZIndex(6)
            .setX(ConstantsUI.CARD_DIM.x / 2)
            .setY(80);
        Group healGroup = graphicEntityModule.createGroup(heal, healFloat)
            .setZIndex(5)
            .setX(ConstantsUI.CARD_DIM.x / 2)
            .setY(80);

        group = graphicEntityModule.createGroup(shadow, background, overlay, ward, lethal, attack, cost, defense, healGroup, damageGroup)
            .setScale(1.0);

        extraIcons = new Sprite[3];
        for (int index = 0; index < 3; ++index) {
            group.add(extraIcons[index] = graphicEntityModule.createSprite()
                    .setAnchorY(0.5)
                    .setScale(0.63)
                    .setX(ConstantsUI.CARD_EXTRAS[index].x + 15)
                    .setY(ConstantsUI.CARD_EXTRAS[index].y)
            .setZIndex(1));
        }
        extraIcons[0].setImage("heart_icon.png");
        extraIcons[1].setImage("heart_icon_rev.png");
        extraIcons[2].setImage("card_stat.png");

        for (int index = 0; index < 3; ++index)
        {
            group.add(extras[index] = graphicEntityModule.createText("-")
                    .setAnchor(0.5)
                    .setFillColor(0xffffff)
                    .setFontSize(25)
                    .setStrokeColor(0x000000)
                    .setStrokeThickness(2.0)
                    .setX(ConstantsUI.CARD_EXTRAS[index].x)
                    .setY(ConstantsUI.CARD_EXTRAS[index].y)
                    .setZIndex(2)
            );
        }

        
        
        
        

        for (int index = 0; index < 6; ++index)
        {
            group.add(keywords[index] = graphicEntityModule.createSprite()
                    .setAnchor(0.5)
                    .setImage(ConstantsUI.CARD_KEYWORDS_IMAGES[index])
                    .setScale(0.6)
                    .setX(ConstantsUI.CARD_KEYWORDS[index].x)
                    .setY(ConstantsUI.CARD_KEYWORDS[index].y)
                    .setZIndex(2)
            );
        }
    }

    public int getX() {
        return group.getX();
    }

    public int getY() {
        return group.getY();
    }

    public double getScaleX() {
        return group.getScaleX();
    }

    public double getScaleY() {
        return group.getScaleY();
    }

    public CardUI setScaleX(double scale) {
        group.setScaleX(scale);
        return this;
    }

    public CardUI setScaleY(double scale) {
        group.setScaleY(scale);
        return this;
    }

    public CardUI setScale(double scale) {
        return setScaleX(scale).setScaleY(scale);
    }

    public int getWidth() {
        return (int) (getScaleX() * ConstantsUI.CARD_DIM.x);
    }

    public int getHeight() {
        return (int) (getScaleY() * ConstantsUI.CARD_DIM.y);
    }

    public CardUI move(int x, int y, Card card) {
        return move(x, y, card, new CreatureOnBoard(card), false);
    }

    public CardUI move(int x, int y, Card base, CreatureOnBoard card, boolean isOnBoard) {
        setVisible(true);

        group.setX(x).setY(y);

        attack
            .setText(formatAttDef(card.attack, base.type))
            .setFillColor(colorAttDef(base.attack, card.attack), Curve.NONE);
        cost
            .setText(Integer.toString(base.cost));
        defense
            .setText(formatAttDef(card.defense, base.type))
            .setFillColor(colorAttDef(base.defense, card.defense), Curve.NONE);

        attack.setVisible(base.type == Card.Type.CREATURE || card.attack != 0);
        defense.setVisible(base.type == Card.Type.CREATURE || card.defense != 0);

        background.setImage("atlas-" + (card.baseId - 1));
        overlay.setImage(isOnBoard && card.keywords.hasGuard ? "guard_overlay.png" : "basic_overlay.png");
        ward.setAlpha(isOnBoard && card.keywords.hasWard ? 1 : 0);
        lethal.setAlpha(isOnBoard && card.keywords.hasLethal ? 1 : 0);
        shadow.setAlpha(isOnBoard && card.canAttack ? 1 : 0);

        int[] extraValues = new int[] {
            base.myHealthChange,
            base.oppHealthChange,
            base.cardDraw
        };

        for (int index = 0; index < 3; ++index) {
            int value = extraValues[index];
            extras[index].setText(formatExtra(value));
            if (value == 0) {
                extras[index].setAlpha(0.1);
                extraIcons[index].setAlpha(0.1);
            } else {
                extras[index].setAlpha(1);
                extraIcons[index].setAlpha(1);
            }
        }

        keywords[0].setAlpha(card.keywords.hasBreakthrough ? 1 : 0.1);
        keywords[1].setAlpha(card.keywords.hasCharge ? 1 : 0.1);
        keywords[2].setAlpha(card.keywords.hasDrain ? 1 : 0.1);
        keywords[3].setAlpha(card.keywords.hasGuard ? 1 : 0.1);
        keywords[4].setAlpha(card.keywords.hasLethal ? 1 : 0.1);
        keywords[5].setAlpha(card.keywords.hasWard ? 1 : 0.1);

        this.tooltipModule.registerEntity(group, new HashMap<>());
        this.tooltipModule.updateExtraTooltipText(group, card.toTooltipText());

        return this;
    }

    private int colorAttDef(int baseValue, int cardValue) {
        if (baseValue < cardValue) {
            return 0x00B16A; // Green
        } else if (baseValue > cardValue) {
            return 0xF22613; // Red
        } else {
            return 0xFFFFFF; // White
        }
    }

    private String formatAttDef(int val, Card.Type type) {
        String str = Integer.toString(val);
        if (val < 0 || type == Card.Type.CREATURE)
            return str;
        return "+" + str;
    }

    private String formatExtra(int val) {
        if (val > 0) return "+" + val;
        if (val < 0) return "" + val;
        return "-";
    }

    public CardUI action() {
        impact.setAlpha(0, Curve.IMMEDIATE);
        heal.setAlpha(0, Curve.IMMEDIATE);

        damageFloat.setText("");
        healFloat.setText("");
        graphicEntityModule.commitEntityState(0, damageFloat, impact, healFloat, heal);

        return this;
    }

    public CardUI action(ActionResult result, int id) {
        int attack = result.attacker != null && result.attacker.id == id
            ? result.attackerAttackChange
            : result.defender != null && result.defender.id == id
                ? result.defenderAttackChange
                : 0;
        int defense = result.attacker != null && result.attacker.id == id
            ? result.attackerDefenseChange
            : result.defender != null && result.defender.id == id
                ? result.defenderDefenseChange
                : 0;


        if (defense < 0) {
            damageFloat.setText(Integer.toString(defense));
            impact.setAlpha(1, Curve.NONE);
            graphicEntityModule.commitEntityState(0.5, damageFloat, impact);
        } else if (defense > 0) {
            healFloat.setText(Integer.toString(defense));
            heal.setAlpha(1, Curve.NONE);
            graphicEntityModule.commitEntityState(0.5, healFloat, heal);
        }

        return this;
    }

    public CardUI lift(int elevation) {
        group.setZIndex(elevation);
        return this;
    }

    public CardUI lift() {
        return lift(5);
    }

    public CardUI ground() {
        return lift(0);
    }

    public CardUI commitGroup(double state) {
        graphicEntityModule.commitEntityState(
            state,
            group
        );

        return this;
    }

    public CardUI commit(double state) {
        graphicEntityModule.commitEntityState(
            state,
            attack,
            background,
            cost,
            defense,
            extras[0],
            extras[1],
            extras[2],
            extraIcons[0],
            extraIcons[1],
            extraIcons[2],
            group,
            overlay,
            ward,
            lethal,
            keywords[0],
            keywords[1],
            keywords[2],
            keywords[3],
            keywords[4],
            keywords[5],
            shadow
        );

        return this;
    }

    public CardUI setVisible(boolean visible) {
        group.setAlpha(visible ? 1 : 0);
        if (!visible)
            action();
        return this;
    }

    public CardUI setExtrasVisibility(boolean visible) {
        for (Text text : extras)
            text.setAlpha(visible ? 1 : 0);
        for (Sprite icon : extraIcons)
            icon.setAlpha(visible ? 1 : 0);
        return this;
    }

    public CardUI zoom(int x, int y, double scale) {
        group.setX(x).setY(y).setScale(scale);
        return this;
    }

    public CardUI setVisibility(double alpha) {
        group.setAlpha(alpha);
        return this;
    }
}
