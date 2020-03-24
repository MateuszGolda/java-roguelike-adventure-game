package com.codecool.java.roguelikegame;

import java.io.FileNotFoundException;

import com.codecool.java.roguelikegame.board.Board;
import com.codecool.java.roguelikegame.beings.Being;
import com.codecool.java.roguelikegame.beings.Player;

/**
 * Stage1
 */
public class Stage1 {

    Board board = new Board("src/main/java/com/codecool/java/roguelikegame/board/boards/map1.txt");
    Being player = new Player(5, 5, 5, 1, 5, 5);

    public void name() throws FileNotFoundException {

        board.printBoard();
        String[][] item = { { "@" } };
        board.printOnBoard(item, player.getyPosition(), player.getxPosition());

        int c;
        while (true) {
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

    private boolean notConflict(int yChange, int xChange) {
        if (board.getBoard()[player.getyPosition() + yChange][player.getxPosition() + xChange].equals(" ")) {
            return true;
        }
        return false;
    }

    private void printAndCleanOldPosition(String[][] item, int yChange, int xChange) {
        String[][] old = { { " " } };
        board.printOnBoard(old, player.getyPosition(), player.getxPosition());
        board.printOnBoard(item, player.getyPosition() + yChange, player.getxPosition() + xChange);
    }
}
