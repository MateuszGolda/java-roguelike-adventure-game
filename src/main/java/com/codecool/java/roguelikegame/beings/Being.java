package com.codecool.java.roguelikegame.beings;

public abstract class Being {

    protected int strength;
    protected int defence;
    protected int hp;
    protected int agility;
    protected int yPosition;
    protected int xPosition;
    protected Icon icon;

    public void addStrength(int strength) {
        this.strength += strength;
    }

    public void addDefence(int defence) {
        this.defence += defence;
    }

    public void addHp(int hp) {
        this.hp += hp;
    }

    public void addAgility(int agility) {
        this.agility += agility;
    }

    public void substraktStrength(int strength) {
        this.strength -= strength;
    }

    public void substraktDefence(int defence) {
        this.defence -= defence;
    }

    public void substraktHp(int hp) {
        this.hp -= hp;
    }

    public void substraktAgility(int agility) {
        this.agility -= agility;
    }

    public void changeIcon(Icon icon) {
        this.icon = icon;
    }

    public void move(int y, int x) {
        yPosition = y;
        xPosition = x;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefence() {
        return defence;
    }

    public int getHp() {
        return hp;
    }

    public int getAgility() {
        return agility;
    }

    /**
     * @return the yPosition
     */
    public int getyPosition() {
        return yPosition;
    }

    /**
     * @return the xPosition
     */
    public int getxPosition() {
        return xPosition;
    }

    public Icon getIcon() {
        return icon;
    }
}
