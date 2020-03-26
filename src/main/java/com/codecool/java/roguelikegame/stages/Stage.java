package com.codecool.java.roguelikegame.stages;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import com.codecool.java.roguelikegame.board.Battle;
import com.codecool.java.roguelikegame.board.Board;
import com.codecool.java.roguelikegame.board.Inventory;
import com.codecool.java.roguelikegame.CharacterInput;
import com.codecool.java.roguelikegame.UI;
import com.codecool.java.roguelikegame.beings.Being;
import com.codecool.java.roguelikegame.beings.Item;
import com.codecool.java.roguelikegame.beings.Point;

/**
 * Stage1
 */
public abstract class Stage {

    protected Board board;
    protected Being player;
    protected Inventory inventory;
    protected Set<Being> enemies = new HashSet<>();
    protected Set<Item> items = new HashSet<>();
    protected Set<String> walkingChars = new HashSet<>();
    protected boolean isRunning;
    protected Point nextStageDoor = new Point(0, 0);
    protected Point secondStageDoor = new Point(0, 0);
    protected Point previousStageDoor = new Point(0, 0);
    protected int stageToGo = 0;
    protected int playerNextStageY;
    protected int playerNextStageX;
    protected int playerSecondStageY;
    protected int playerSecondStageX;
    protected int playerPreviousStageY;
    protected int playerPreviousStageX;

    public Stage(Being player, Inventory inventory) {
        walkingChars.add(" ");
        walkingChars.add("≣");
        walkingChars.add("\u2003");
        this.inventory = inventory;
        this.player = player;
    }

    protected abstract void setBoard();

    protected abstract void addEnemies();

    protected abstract void addDoorToNextStage();

    protected abstract void addPlayerNextStagePosition();

    protected abstract void addItems();

    protected void addToInventory(Item item) {
        inventory.addItem(item);
        items.remove(item);
        board.clearBoard(item.getIcon(), item.getyPosition(), item.getxPosition());
    }

    public int gameLoop() throws FileNotFoundException {
        initializeStage();
        int c;
        isRunning = true;
        while (isRunning) {
            UI.moveCursor(board.getBoard().length, 0); // to avoid printing input on the board border
            c = Character.toLowerCase(CharacterInput.getNoEnterInput());

            switch (c) {
                case 'a':
                    movePlayerIfNotCollision(0, -1);
                    moveEnemies();
                    break;

                case 'w':
                    movePlayerIfNotCollision(-1, 0);
                    moveEnemies();
                    break;

                case 's':
                    movePlayerIfNotCollision(1, 0);
                    moveEnemies();
                    break;

                case 'd':
                    movePlayerIfNotCollision(0, 1);
                    moveEnemies();
                    break;

                case 'i':
                    displayInventory();
                    break;

                case 'q':
                    stageToGo = 4;
                    isRunning = false;
                    break;

                default:
                    break;
            }
        }
        return stageToGo;
    }

    protected void movePlayerIfNotCollision(int yChange, int xChange) {
        if (!isCollisionWithBoard(yChange, xChange, player)) {
            printAndCleanOldPosition(yChange, xChange, player);
            for (Being enemy : enemies) {
                if (isCollisionWithBeing(yChange, xChange, enemy)) {
                    new Battle(player, inventory, enemy).makeBattle();
                }
            }
            for (Being item : items) {
                if (isCollisionWithBeing(yChange, xChange, item)) {
                    addToInventory((Item) item);
                }
            }
            player.move(player.getyPosition() + yChange, player.getxPosition() + xChange);
            checkIfDoorToNextStage(yChange, xChange);
        }
    }

    protected void moveEnemies() {
        for (Being enemy : enemies) {
            int yChange = 0;
            int xChange = 0;
            int yDistance = Math.abs(enemy.getyPosition() - player.getyPosition());
            int xDistance = Math.abs(enemy.getxPosition() - player.getxPosition());
            if (yDistance < 20 && yDistance < xDistance) {
                yChange = (player.getyPosition() - enemy.getyPosition() < 0) ? -1 : 1;
            } else if (xDistance < 20) {
                xChange = (player.getxPosition() - enemy.getxPosition() - enemy.getIcon()[0].length < 0) ? -1 : 1;
            }
            if (!isCollisionWithBoard(yChange, xChange, enemy)) {
                printAndCleanOldPosition(yChange, xChange, enemy);
                enemy.move(enemy.getyPosition() + yChange, enemy.getxPosition() + xChange);
            }
        }
    }

    protected void checkIfDoorToNextStage(int yChange, int xChange) {
        for (Point point : player.getAllPoints()) {
            if (point.equals(nextStageDoor)) {
                isRunning = false;
                stageToGo = 1;
                player.setyPosition(playerNextStageY);
                player.setxPosition(playerNextStageX);
                break;
            }
            if (point.equals(secondStageDoor)) {
                isRunning = false;
                stageToGo = 2;
                player.setyPosition(playerSecondStageY);
                player.setxPosition(playerSecondStageX);
                break;
            }
            if (point.equals(previousStageDoor)) {
                isRunning = false;
                stageToGo = -1;
                player.setyPosition(playerPreviousStageY);
                player.setxPosition(playerPreviousStageX);
                break;
            }
        }
    }

    protected boolean isCollisionWithBoard(int yChange, int xChange, Being being) {
        for (int y = 0; y < being.getIcon().length; y++) {
            for (int x = 0; x < being.getIcon()[y].length; x++) {
                if (!walkingChars.contains(
                        board.getBoard()[being.getyPosition() + yChange + y][being.getxPosition() + xChange + x])) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean isCollisionWithBeing(int yChange, int xChange, Being object) {
        if (player.getAllPoints().retainAll(object.getAllPoints())) {
            return false;
        } else {
            return true;
        }
    }

    protected void printAndCleanOldPosition(int yChange, int xChange, Being being) {
        board.clearBoard(being.getIcon(), being.getyPosition(), being.getxPosition());
        board.printOnBoard(being.getIcon(), being.getyPosition() + yChange, being.getxPosition() + xChange);
    }

    protected void displayInventory() {
        inventory.inventoryScreen(player);
        printStage();
    }

    protected void initializeStage() {
        setBoard();
        addEnemies();
        addItems();
        addDoorToNextStage();
        addPlayerNextStagePosition();
        printStage();
    }

    protected void printStage() {
        board.printBoard();
        board.printOnBoard(player.getIcon(), player.getyPosition(), player.getxPosition());
        for (Being item : items) {
            board.printOnBoard(item.getIcon(), item.getyPosition(), item.getxPosition());
        }
        for (Being being : enemies) {
            board.printOnBoard(being.getIcon(), being.getyPosition(), being.getxPosition());
        }
    }
}
