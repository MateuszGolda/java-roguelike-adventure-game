package com.codecool.java.roguelikegame.beings;

public class Item extends Being {

    private ItemTypes type;
    private String[][] icon;

    public Item(ItemTypes type, int yPosition, int xPosition) {
        this.type = type;
        Icon newIcon = new Icon(type);
        super.icon = newIcon.getItemIcon();
        this.icon = newIcon.getItemIcon();
        switch (type) {
            case HP:
                super.hp = 5;
                break;
            case STRENGTH:
                super.strength = 5;
                break;
            case AGILITY:
                super.agility = 5;
                break;
            case DEFENCE:
                super.defence = 5;
                break;
        }
        super.yPosition = yPosition;
        super.xPosition = xPosition;
    }

    public Being usePotion(Being player) {
        switch (type) {
            case HP:
                player.addHp(super.hp);
                break;
            case STRENGTH:
                player.addStrength(super.strength);
                break;
            case AGILITY:
                player.addAgility(super.agility);
                break;
            case DEFENCE:
                player.addDefence(super.defence);
                break;
        }
        return player;
    }

    public String[][] getIcon() {
        return icon;
    }

    public ItemTypes getType() {
        return type;
    }
}
