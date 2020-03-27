package com.codecool.java.roguelikegame.beings;

public class Monster extends Being {

    public Monster(int strength, int defence, int hp, int agility, int yPosition, int xPosition, int monsterLevel) {
        super.strength = strength;
        super.defence = defence;
        super.hp = hp;
        super.agility = agility;
        super.yPosition = yPosition;
        super.xPosition = xPosition;
        super.icon = new Icon().getMonsterIcon(monsterLevel);
    }

    public boolean isAlive() {
        if (super.hp > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String[][] getIcon() {
        return super.icon;
    }
}
