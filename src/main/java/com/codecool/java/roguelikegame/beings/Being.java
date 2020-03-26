package com.codecool.java.roguelikegame.beings;

import java.util.HashSet;
import java.util.Set;

public abstract class Being {

    protected int strength;
    protected int defence;
    protected int hp;
    protected int agility;
    protected int yPosition;
    protected int xPosition;
    protected String[][] icon;
    protected int exp;
    protected int level;

    public Set<Point> getAllPoints() {
        Set<Point> points = new HashSet<>();
        for (int y = 0; y < icon.length; y++) {
            for (int x = 0; x < icon[y].length; x++)
                if (!icon[y][x].equals(" ")) {
                    Point point = new Point(yPosition + y, xPosition + x);
                    points.add(point);
                }
        }
        return points;
    }

    public void addExp(int exp) {
        this.exp += exp;
        if (this.exp > 100) {
            this.exp -= 100;
            levelUp();
        }
    }

    private void levelUp() {
        this.strength += this.strength;
        this.defence += this.defence;
        this.hp += this.hp;
        this.agility += (int) this.agility / 4;
        if (level < 2) {
            level++;
        }
        this.icon = new Icon(level).getPlayerIcon();
    }

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

    public void subtractStrength(int strength) {
        this.strength -= strength;
    }

    public void subtractDefence(int defence) {
        this.defence -= defence;
    }

    public void subtractHp(int hp) {
        this.hp -= hp;
    }

    public void subtractAgility(int agility) {
        this.agility -= agility;
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

    public int getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
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

    public abstract String[][] getIcon();


    /**
     * @param yPosition the yPosition to set
     */
    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    /**
     * @param xPosition the xPosition to set
     */
    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }
}
