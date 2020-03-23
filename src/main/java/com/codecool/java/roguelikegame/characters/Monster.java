package main.java.com.codecool.java.roguelikegame.characters;

public class Monster extends Character {

    private boolean alive;

    public Monster(int strength, int defence, int hp, int agility, int[] coordinates) {
        super.strength = strength;
        super.defence = defence;
        super.hp = hp;
        super.agility = agility;
        super.coordinates = coordinates;
        Icon monsterIcon = new Icon();
        super.icon = monsterIcon;
        this.alive = true;
    }

    public boolean isAlive() {
        return alive;
    }

}