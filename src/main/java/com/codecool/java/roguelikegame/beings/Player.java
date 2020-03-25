package com.codecool.java.roguelikegame.beings;

public class Player extends Being {

    private int level;

    public Player(int strength, int defence, int hp, int agility, int yPosition, int xPosition) {
        super.strength = strength;
        super.defence = defence;
        super.hp = hp;
        super.agility = agility;
        super.yPosition = yPosition;
        super.xPosition = xPosition;
        this.level = 0;
        Icon icon = new Icon(level);
        super.icon = icon;
    }

    public void levelUp() {
        super.strength += super.strength;
        super.defence += super.defence;
        super.hp += super.hp;
        super.agility += (int) super.agility/4;
        super.yPosition = 3;
        super.xPosition = 3;
        Icon newIcon = new Icon(this.level++);
        super.icon = newIcon;
    }

    @Override
    public String[][] getIcon() {
        return super.icon.getPlayerIcon();
    }
}
