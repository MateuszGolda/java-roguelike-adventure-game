package com.codecool.java.roguelikegame.beings;

public class Player extends Being {

    public Player(int yPosition, int xPosition) {
        super.strength = 5;
        super.defence = 5;
        super.hp = 50;
        super.agility = 5;
        super.yPosition = yPosition;
        super.xPosition = xPosition;
        super.level = 0;
        super.exp = 0;
        super.icon = new Icon(level).getPlayerIcon();
    }

    @Override
    public String[][] getIcon() {
        return super.icon;
    }
}
