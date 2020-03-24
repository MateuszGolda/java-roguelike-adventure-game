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

    public void changeLevelUp() {
        level++;
        Icon newIcon = new Icon(level);
        super.icon = newIcon;
    }
}
