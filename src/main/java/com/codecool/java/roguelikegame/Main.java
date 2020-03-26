package com.codecool.java.roguelikegame;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.codecool.java.roguelikegame.beings.Being;
import com.codecool.java.roguelikegame.beings.Player;
import com.codecool.java.roguelikegame.board.Inventory;
import com.codecool.java.roguelikegame.board.StartScreen;
import com.codecool.java.roguelikegame.stages.Stage;
import com.codecool.java.roguelikegame.stages.Stage1;
import com.codecool.java.roguelikegame.stages.Stage2;
import com.codecool.java.roguelikegame.stages.Stage3;
import com.codecool.java.roguelikegame.stages.Stage4;

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

        Stage[] stages = new Stage[4];
        stages[0] = new Stage1(player, inventory);
        stages[1] = new Stage2(player, inventory);
        stages[2] = new Stage3(player, inventory);
        stages[3] = new Stage4(player, inventory);
        int currentStage = 0;
        while (currentStage < 4) {
            currentStage += stages[currentStage].gameLoop();
        }
    }
}
