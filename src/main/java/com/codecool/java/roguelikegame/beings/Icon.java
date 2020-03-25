package com.codecool.java.roguelikegame.beings;

public class Icon {

    private IconColor color;
    private IconColor reset = IconColor.RESET;
    private int playerLevel;
    private String itemCharacter;

    public Icon() { // constructor for monster
        this.color = IconColor.RED;
    }

    public Icon(int level) { // for player
        this.color = IconColor.GREEN;
        this.playerLevel = level;
    }

    public Icon(String itemCharacter) {
        this.color = IconColor.BLUE;
        this.itemCharacter = itemCharacter;
    }

    public String[][] getPlayerIcon() {
        String[][] icon = {{color.iconColor() + "@" + reset.iconColor()},
                {color.iconColor() + "@" + reset.iconColor(),
                color.iconColor() + "@" + reset.iconColor(),},
                {color.iconColor() + "@" + reset.iconColor(),
                color.iconColor() + "@" + reset.iconColor(),
                color.iconColor() + "@" + reset.iconColor()}};
        return icon;
    }

    public String[][] getMonsterIcon() {
        String[][] playerIcon = {{color.iconColor() + "M" + reset.iconColor()}};
        return playerIcon;
    }

    public String getItemIcon() {
        return color.iconColor() + itemCharacter + reset.iconColor();
    }

}
