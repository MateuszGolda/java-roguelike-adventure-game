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

    public Stage(Board board, Player player) {
        walkingChars.add(" ");
        walkingChars.add("≣");
        this.board = board;
        this.player = player;
    }

    public void gameLoop() throws FileNotFoundException {

        board.printBoard();
        String[][] item = player.getIcon();
        board.printOnBoard(item, player.getyPosition(), player.getxPosition());

        int c;
        while (true) {
            UI.moveCursor(board.getBoard().length, 0); // move under the board to avoid printing input on the board border
            c = Character.toLowerCase(CharacterInput.getNoEnterInput());

            if (c == 'a' && notConflict(0, -1)) {
                printAndCleanOldPosition(item, 0, -1);
                player.move(player.getyPosition(), player.getxPosition() - 1);
            }
            if (c == 'w' && notConflict(-1, 0)) {
                printAndCleanOldPosition(item, -1, 0);
                player.move(player.getyPosition() - 1, player.getxPosition());
            }
            if (c == 's' && notConflict(1, 0)) {
                printAndCleanOldPosition(item, 1, 0);
                player.move(player.getyPosition() + 1, player.getxPosition());
            }
            if (c == 'd' && notConflict(0, 1)) {
                printAndCleanOldPosition(item, 0, 1);
                player.move(player.getyPosition(), player.getxPosition() + 1);
            }
            if (c == 'q') {
                break;
            }
        }
    }

    protected boolean notConflict(int yChange, int xChange) {
        if (walkingChars.contains(board.getBoard()[player.getyPosition() + yChange][player.getxPosition() + xChange])) {
            return true;
        }
        return false;
    }

    protected void printAndCleanOldPosition(String[][] item, int yChange, int xChange) {
        String[][] old = { { board.getBoard()[player.getyPosition()][player.getxPosition()] } };
        board.printOnBoard(old, player.getyPosition(), player.getxPosition());
        board.printOnBoard(item, player.getyPosition() + yChange, player.getxPosition() + xChange);
    }
}
