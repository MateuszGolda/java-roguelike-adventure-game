package main.java.com.codecool.java.roguelikegame.characters;

public enum IconColor {
    GREEN("greencolor"),
    YELLOW("yellowcolor"),
    RED("redcolor"),
    BLUE("bluecolor"),
    WHITE("withoutcolor"); // as default


    private String colorChanger;

    IconColor(String colorChanger) {
        this.colorChanger = colorChanger;
    }

    public String iconColor() {
        return colorChanger;
    }
}