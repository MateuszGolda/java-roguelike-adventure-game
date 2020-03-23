package main.java.com.codecool.java.roguelikegame.characters;

public abstract class Character {

    protected int strength;
    protected int defence;
    protected int hp;
    protected int agility;
    protected int[] coordinates;
    private final int yIndex = 0;
    private final int xIndex = 1;
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

    public void subtrackStrength(int strength) {
        this.strength -= strength;
    }

    public void subtrackDefence(int defence) {
        this.defence -= defence;
    }

    public void subtrackHp(int hp) {
        this.hp -= hp;
    }

    public void subtrackAgility(int agility) {
        this.agility -= agility;
    }

    public void changeIcon(Icon icon) {
        this.icon = icon;
    }

    public void moveUp() {
        if (notConflict()) {    
            coordinates[yIndex] += 1;
        }
    }

    public void moveDown() {
        if (notConflict()) {
            coordinates[yIndex] -= 1;
        }        
    }

    public void moveLeft() {
        if (notConflict()) {
            coordinates[xIndex] -= 1;
        }
    }

    public void moveRight() {
        if (notConflict()) {
            coordinates[xIndex] += 1;
        }
    }

    private boolean notConflict() {
        return true;
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

    public int[] getCoordinates() {
        return coordinates;
    }

    public Icon getIcon() {
        return icon;
    }
}