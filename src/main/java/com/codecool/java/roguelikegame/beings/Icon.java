package com.codecool.java.roguelikegame.beings;

public class Icon {

    private IconColor color;
    private IconColor white;
    private int playerLevel;
    private String itemCharacter;

    public Icon() { // constructor for monster
        this.color = IconColor.RED;
        this.white = IconColor.WHITE;
    }

    public Icon(int level) { // for player
        this.color = IconColor.GREEN;
        this.playerLevel = level;
    }

    public Icon(String itemCharacter) {
        this.color = IconColor.BLUE;
        this.itemCharacter = itemCharacter;
    }

    public String getPlayerIcon() {
        return color.iconColor() + "@" + white.iconColor();
    }

    public String getMonsterIcon() {
        String playerIcon = "";
        if (playerLevel >= 0) {
            playerIcon = color.iconColor() + "%" + white.iconColor();
        }
        return playerIcon;
    }

    public String getItemIcon() {
        return color.iconColor() + itemCharacter + white.iconColor();
    }

}
