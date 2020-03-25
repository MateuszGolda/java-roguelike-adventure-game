package com.codecool.java.roguelikegame.inventory;

import com.codecool.java.roguelikegame.beings.Being;
import com.codecool.java.roguelikegame.beings.Icon;

public class Item extends Being {

    private ItemTypes type;
    private Icon icon;

    public Item(ItemTypes type) {
        this.type = type;
        Icon newIcon = new Icon(type);
        this.icon = newIcon;
        switch (type) {
            case HP:
                super.hp = 5;
                break;
            case SWORD:
                super.strength = 5;
                break;
            case AGILITY:
                super.agility = 5;
                break;
            case DEFENCE:
                super.defence = 5;
                break;
        }
    }

    public Being usePotion(Being player) {
        switch (type) {
            case HP:
                player.addHp(super.hp);
                break;
            case SWORD:
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
        return icon.getItemIcon(type);
    }


}