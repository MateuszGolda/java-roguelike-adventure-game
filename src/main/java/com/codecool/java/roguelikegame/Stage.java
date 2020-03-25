package com.codecool.java.roguelikegame;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import com.codecool.java.roguelikegame.board.Board;
import com.codecool.java.roguelikegame.beings.Being;
import com.codecool.java.roguelikegame.beings.Player;

/**
 * Stage1
 */
public abstract class Stage {

    protected Board board;
    protected Being player;
    protected Set<String> walkingChars = new HashSet<String>();
    protected boolean isRunning;

    public Stage(Being player) {
        isRunning = true;
        walkingChars.add(" ");
        walkingChars.add("≣");
        this.player = player;
    }

    protected abstract void setBoard();

    public void gameLoop() throws FileNotFoundException {
        setBoard();
        board.printBoard();
        board.printOnBoard(player.getIcon(), player.getyPosition(), player.getxPosition());

        int c;
        while (isRunning) {
            UI.moveCursor(board.getBoard().length, 0); // to avoid printing input on the board border
            c = Character.toLowerCase(CharacterInput.getNoEnterInput());

            switch (c) {
                case 'a':
                    moveIfNotConflict(0, -1);
                    break;

                case 'w':
                    moveIfNotConflict(-1, 0);
                    break;

                case 's':
                    moveIfNotConflict(1, 0);
                    break;

                case 'd':
                    moveIfNotConflict(0, 1);
                    break;

                case 'i':
                    System.out.println("Inventory is not yet implemented");
                    break;

                case 'q':
                    isRunning = false;
                    break;

                default:
                    break;
            }
        }
    }

    protected void moveIfNotConflict(int yChange, int xChange) {
        if (notConflict(yChange, xChange)) {
            printAndCleanOldPosition(yChange, xChange);
            player.move(player.getyPosition() + yChange, player.getxPosition() + xChange);
        }
    }

    protected boolean notConflict(int yChange, int xChange) {
        if (walkingChars.contains(board.getBoard()[player.getyPosition() + yChange][player.getxPosition() + xChange])) {
            return true;
        }
        return false;
    }

    protected void printAndCleanOldPosition(int yChange, int xChange) {
        String[][] old = { { board.getBoard()[player.getyPosition()][player.getxPosition()] } };
        board.printOnBoard(old, player.getyPosition(), player.getxPosition());
        board.printOnBoard(player.getIcon(), player.getyPosition() + yChange, player.getxPosition() + xChange);
    }
}
