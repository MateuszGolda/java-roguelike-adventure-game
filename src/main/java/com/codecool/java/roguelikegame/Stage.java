package com.codecool.java.roguelikegame;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import com.codecool.java.roguelikegame.board.Board;
import com.codecool.java.roguelikegame.board.Inventory;
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

    public Stage(Being player, Inventory inventory) {
        isRunning = true;
        walkingChars.add(" ");
        walkingChars.add("≣");
        walkingChars.add("\u2003");
        this.inventory = inventory;
        this.player = player;
    }

    protected abstract void setBoard();

    protected abstract void addEnemies();

    protected abstract void addItems();

    protected void addToInventory(Item item) {
        inventory.addItem(item);
        items.remove(item);
        board.clearBoard(item.getIcon(), item.getyPosition(), item.getxPosition());
    }

    public void gameLoop() throws FileNotFoundException {
        initializeStage();
        int c;

        while (isRunning) {
            UI.moveCursor(board.getBoard().length, 0); // to avoid printing input on the board border
            c = Character.toLowerCase(CharacterInput.getNoEnterInput());

            switch (c) {
                case 'a':
                    moveIfNotCollision(0, -1);
                    break;

                case 'w':
                    moveIfNotCollision(-1, 0);
                    break;

                case 's':
                    moveIfNotCollision(1, 0);
                    break;

                case 'd':
                    moveIfNotCollision(0, 1);
                    break;

                case 'i':
                    displayInventory();
                    break;

                case 'q':
                    isRunning = false;
                    break;

                default:
                    break;
            }
        }
    }

    protected void moveIfNotCollision(int yChange, int xChange) {
        if (isCollisionWithBoard(yChange, xChange)) {
            printAndCleanOldPosition(yChange, xChange);
            for (Being enemy : enemies) {
                if (isCollisionWithBeing(yChange, xChange, enemy)) {
                    isRunning = false;
                }
            }
            for (Being item : items) {
                if (isCollisionWithBeing(yChange, xChange, item)) {
                    addToInventory((Item) item);
                }
            }
            player.move(player.getyPosition() + yChange, player.getxPosition() + xChange);
        }
    }

    protected boolean isCollisionWithBoard(int yChange, int xChange) {
        for (int y = 0; y < player.getIcon().length; y++) {
            for (int x = 0; x < player.getIcon()[y].length; x++) {
                if (!walkingChars.contains(
                        board.getBoard()[player.getyPosition() + yChange + y][player.getxPosition() + xChange + x])) {
                    return false;
                }
            }
        }
        return true;
    }

    protected boolean isCollisionWithBeing(int yChange, int xChange, Being object) {
        Set<Point> pl = player.getAllPoints();
        Set<Point> ob = object.getAllPoints();
        if (pl.retainAll(ob)) {
            return false;
        } else {
            return true;
        }
    }

    protected void printAndCleanOldPosition(int yChange, int xChange) {
        board.clearBoard(player.getIcon(), player.getyPosition(), player.getxPosition());
        board.printOnBoard(player.getIcon(), player.getyPosition() + yChange, player.getxPosition() + xChange);
    }

    protected void displayInventory() {
        inventory.inventoryScreen(player);
        printStage();
    }

    protected void initializeStage() {
        setBoard();
        addEnemies();
        addItems();
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
