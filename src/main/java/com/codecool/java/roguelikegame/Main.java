package com.codecool.java.roguelikegame;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.codecool.java.roguelikegame.beings.Being;
import com.codecool.java.roguelikegame.beings.Player;
import com.codecool.java.roguelikegame.board.Inventory;
import com.codecool.java.roguelikegame.board.StartScreen;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        boolean isRunning = true;
        while (isRunning) {
            StartScreen start = new StartScreen();
            switch (start.displayStartScreen()) {
                case 0: // start game
                    startGame();
                    break;
                case 3: // quit
                    isRunning = false;
                    break;
            }
        }

    }

    public static void startGame() throws FileNotFoundException {
        Inventory inventory = new Inventory();
        Being player = new Player(5, 5); // set player starting position
        Stage stage1 = new Stage1(player, inventory);
        stage1.gameLoop();
    }
}
