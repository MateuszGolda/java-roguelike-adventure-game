package com.codecool.java.roguelikegame;

import java.io.IOException;

import com.codecool.java.roguelikegame.beings.Being;
import com.codecool.java.roguelikegame.beings.Player;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Being player = new Player(5, 5); // set player starting position
        Stage stage1 = new Stage1(player);
        stage1.gameLoop();
    }
}
