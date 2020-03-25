package com.codecool.java.roguelikegame.beings;

public class Icon {

    private IconColor color;
    private IconColor reset = IconColor.RESET;
    private int playerLevel;

    public Icon() { // constructor for monster
        this.color = IconColor.RED;
    }

    public Icon(int level) { // for player
        this.color = IconColor.GREEN;
        this.playerLevel = level;
    }

    public Icon(ItemTypes type) {
        switch (type) {
            case HP:
                color = IconColor.GREEN;
                break;
            case STRENGTH:
                color = IconColor.RED;
                break;
            case AGILITY:
                color = IconColor.BLUE;
                break;
            case DEFENCE:
                color = IconColor.YELLOW;
                break;
        }
    }

    public String[][] getPlayerIcon() {
        if (playerLevel == 0) {
            String[][] icon = {{String.format("%s@%s", color.iconColor(), reset.iconColor())}};
            return icon;
        } else if (playerLevel == 1) {
            String[][] icon = {{color.iconColor(), "@", " "}, {"/", "|", "\\"}, {"/", " ", "\\", reset.iconColor()}};
            return icon;
        } else {
            String[][] icon = {{color.iconColor(), " ", " ", " ", "_", "_", " ", " ", " ", " ", },    //     __
                    {" ", " ", "_", "(", "-", "-", ")", "_", " ", " ", },                             //   _(--)_
                    {" ", "/", "/", "[", "]", "[", "]", "\\", "\\", " ", },                           //  //[][]\\
                    {"/", "/", " ", "[", "]", "[", "]", " ", "\\", "\\", },                           // // [][] \\
                    {" ", " ", " ", "/", "/", "\\", "\\", " ", " ", " ", },                           //    //\\  
                    {" ", "=", "=", "=", " ", " ", "=", "=", "=", " ", reset.iconColor()},};          //  ===  ===
            return icon;
        }
    }

    public String[][] getMonsterIcon() {
        String[][] monsterIcon = {{color.iconColor(), "_", "\\", "(", "|", ")", "/", "_"},
                {" ", " ", "/", "(", "0", ")", "\\", " ", reset.iconColor()}};
        return monsterIcon;
    }



    public String[][] getItemIcon() {

        String[][] potionFormat = {{" ", "_", "{", "}", "_", " "},          //  _{}_
                {"|", String.format("%s≋", color.iconColor()),              // |≋≋≋≋|
                "≋", "≋", String.format("≋%s", reset.iconColor()), "|"},    // |≋≋≋≋|
                {"|", String.format("%s≋", color.iconColor()),              // ``````
                "≋", "≋", String.format("≋%s", reset.iconColor()), "|"},
                {"`", "`", "`", "`", "`", "`"}};
        return potionFormat;
    }
}
