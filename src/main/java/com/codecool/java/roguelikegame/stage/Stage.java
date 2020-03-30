package com.codecool.java.roguelikegame.stage;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.codecool.java.roguelikegame.beings.Being;
import com.codecool.java.roguelikegame.beings.Item;
import com.codecool.java.roguelikegame.beings.Monster;
import com.codecool.java.roguelikegame.beings.Point;
import com.codecool.java.roguelikegame.board.Battle;
import com.codecool.java.roguelikegame.board.Board;
import com.codecool.java.roguelikegame.board.Inventory;

import helpers.CharacterInput;
import helpers.UI;

/**
 * Stage1
 */
public class Stage {

    private Board board;
    private Being player;
    private Inventory inventory;
    private Set<Being> enemies = new HashSet<>();
    private Set<Item> items = new HashSet<>();
    private Set<String> walkingChars = new HashSet<>();
    private boolean isRunning;
    private Point nextStageDoor = new Point(0, 0);
    private Point secondStageDoor = new Point(0, 0);
    private Point previousStageDoor = new Point(0, 0);
    private int stageToGo = 0;
    private int playerNextStageY;
    private int playerNextStageX;
    private int playerSecondStageY;
    private int playerSecondStageX;
    private int playerPreviousStageY;
    private int playerPreviousStageX;

    public Stage(Being player, Inventory inventory) {
        walkingChars.add(" ");
        walkingChars.add("≣");
        walkingChars.add("\u2003");
        this.inventory = inventory;
        this.player = player;
    }

    public int gameLoop() throws FileNotFoundException {
        printStage();
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

    private void movePlayerIfNotCollision(int yChange, int xChange) {
        if (!isCollisionWithBoard(yChange, xChange, player)) {
            printAndCleanOldPosition(yChange, xChange, player);
            for (Being enemy : enemies) {
                if (isCollisionWithBeing(yChange, xChange, enemy)) {
                    new Battle(player, inventory, enemy).makeBattle();
                    enemies.remove(enemy);
                    printStage();
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

    private void moveEnemies() {
        for (Being enemy : enemies) {
            int yChange = 0;
            int xChange = 0;
            int yPlayer = player.getyPosition();
            int yPlayerLatter = player.getyPosition() + player.getIcon().length;
            int xPlayer = player.getxPosition();
            int xPlayerLatter = player.getxPosition() + player.getIcon()[0].length;
            int yMonster = enemy.getyPosition();
            int yMonsterLatter = enemy.getyPosition() + enemy.getIcon().length;
            int xMonster = enemy.getxPosition();
            int xMonsterLatter = enemy.getxPosition() + enemy.getIcon()[0].length;

            int yDistance = Math.min(Math.abs(yMonster - yPlayer), Math.abs(yMonsterLatter - yPlayer));
            yDistance = Math.min(Math.min(Math.abs(yMonster - yPlayerLatter), Math.abs(yMonsterLatter - yPlayerLatter)),
                    yDistance);

            int xDistance = Math.min(Math.abs(xMonster - xPlayer), Math.abs(xMonsterLatter - xPlayer));
            xDistance = Math.min(Math.min(Math.abs(xMonster - xPlayerLatter), Math.abs(xMonsterLatter - xPlayerLatter)),
                    xDistance);

            if (yDistance < 20 && xDistance < 20 && yDistance + 1 > xDistance) {
                yChange = (player.getyPosition() - enemy.getyPosition() - enemy.getIcon().length / 2 < 0) ? -1 : 1;
            } else if (yDistance < 20 && xDistance < 20) {
                xChange = (player.getxPosition() - enemy.getxPosition() - enemy.getIcon()[0].length / 2 < 0) ? -1 : 1;
            }
            if (!isCollisionWithBoard(yChange, xChange, enemy)) {
                printAndCleanOldPosition(yChange, xChange, enemy);
                enemy.move(enemy.getyPosition() + yChange, enemy.getxPosition() + xChange);
            }
        }
    }

    private void checkIfDoorToNextStage(int yChange, int xChange) {
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

    private boolean isCollisionWithBoard(int yChange, int xChange, Being being) {
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

    private boolean isCollisionWithBeing(int yChange, int xChange, Being object) {
        if (Collections.disjoint(player.getAllPoints(), object.getAllPoints())) {
            return false;
        } else {
            return true;
        }
    }

    private void printAndCleanOldPosition(int yChange, int xChange, Being being) {
        board.clearBoard(being.getIcon(), being.getyPosition(), being.getxPosition());
        board.printOnBoard(being.getIcon(), being.getyPosition() + yChange, being.getxPosition() + xChange);
    }

    private void displayInventory() {
        inventory.inventoryScreen(player);
        printStage();
    }

    private void printStage() {
        board.printBoard();
        board.printOnBoard(player.getIcon(), player.getyPosition(), player.getxPosition());
        for (Being item : items) {
            board.printOnBoard(item.getIcon(), item.getyPosition(), item.getxPosition());
        }
        for (Being being : enemies) {
            board.printOnBoard(being.getIcon(), being.getyPosition(), being.getxPosition());
        }
    }

    private void addToInventory(Item item) {
        inventory.addItem(item);
        items.remove(item);
        board.clearBoard(item.getIcon(), item.getyPosition(), item.getxPosition());
    }

    public void setBoard(String mapTxtFile) {
        this.board = new Board(mapTxtFile);
    }

    public void addEnemy(Monster enemy) {
        this.enemies.add(enemy);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void setPlayerNextStageY(int playerNextStageY) {
        this.playerNextStageY = playerNextStageY;
    }

    public void setPlayerNextStageX(int playerNextStageX) {
        this.playerNextStageX = playerNextStageX;
    }

    public void setPlayerSecondStageY(int playerSecondStageY) {
        this.playerSecondStageY = playerSecondStageY;
    }

    public void setPlayerSecondStageX(int playerSecondStageX) {
        this.playerSecondStageX = playerSecondStageX;
    }

    public void setPlayerPreviousStageY(int playerPreviousStageY) {
        this.playerPreviousStageY = playerPreviousStageY;
    }

    public void setPlayerPreviousStageX(int playerPreviousStageX) {
        this.playerPreviousStageX = playerPreviousStageX;
    }

    public void setNextStageDoor(Point nextStageDoor) {
        this.nextStageDoor = nextStageDoor;
    }

    public void setSecondStageDoor(Point secondStageDoor) {
        this.secondStageDoor = secondStageDoor;
    }

    public void setPreviousStageDoor(Point previousStageDoor) {
        this.previousStageDoor = previousStageDoor;
    }
}
