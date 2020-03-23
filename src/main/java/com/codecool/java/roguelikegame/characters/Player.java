package main.java.com.codecool.java.roguelikegame.characters;

public class Player extends Character {

    private int level;

    public Player(int strength, int defence, int hp, int agility, int[] coordinates) {
        super.strength = strength;
        super.defence = defence;
        super.hp = hp;
        super.agility = agility;
        super.coordinates = coordinates;
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