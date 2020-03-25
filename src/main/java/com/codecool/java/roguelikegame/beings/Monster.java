package com.codecool.java.roguelikegame.beings;

public class Monster extends Being {

    public Monster(int strength, int defence, int hp, int agility, int yPosition, int xPosition) {
        super.strength = strength;
        super.defence = defence;
        super.hp = hp;
        super.agility = agility;
        super.yPosition = yPosition;
        super.xPosition = xPosition;
        Icon monsterIcon = new Icon();
        super.icon = monsterIcon;
    }

    public boolean isAlive() {
        if (super.hp > 0) {
            return true;
        } else {
            return false;
        }
    }

}
