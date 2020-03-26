package com.codecool.java.roguelikegame;

import java.io.IOException;

import com.codecool.java.roguelikegame.beings.Being;
import com.codecool.java.roguelikegame.beings.Player;
import com.codecool.java.roguelikegame.board.StartScreen;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) throws IOException {
        boolean isRunning = true;
        while (isRunning) {
            StartScreen start = new StartScreen();
            switch (start.displayStartScreen()) {
                case 0: // start game
                    // code of game
                    break;
                case 3: // quit
                    isRunning = false;
                    break;
            }
        }

        /*
        Being player = new Player(5, 5); // set player starting position
        Stage stage1 = new Stage1(player);
        stage1.gameLoop();
        */
    }
}
