package com.codecool.java.roguelikegame;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.codecool.java.roguelikegame.board.StartScreen;
import com.codecool.java.roguelikegame.stage.StagesCreator;
import com.codecool.java.roguelikegame.stage.Stage;

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
        StagesCreator sc = new StagesCreator();
        Stage[] stages = sc.configureStages();
        int currentStage = 0;

        while (currentStage < stages.length) {
            currentStage += stages[currentStage].gameLoop();
        }
    }
}
