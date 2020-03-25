package com.codecool.java.roguelikegame.beings;

public enum IconColor {
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    RED("\u001B[31m"),
    BLUE("\u001B[34m"),
    RESET("\u001B[0m"); // as default


    private String colorChanger;

    IconColor(String colorChanger) {
        this.colorChanger = colorChanger;
    }

    public String iconColor() {
        return colorChanger;
    }
}
