package com.codecool.java.roguelikegame.board;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import helpers.UI;

/**
 * Board
 */
public class Board {
    private String[][] board;

    public Board(String fileName) {
        try {
            getBoardFromFile(fileName);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public void getBoardFromFile(String fileName) throws FileNotFoundException {
        final int boardHeight = 40;
        final int boardWidth = 149;
        String[][] board = new String[boardHeight][boardWidth];
        Scanner scan = new Scanner(new File(fileName));
        for (int i = 0; i < board.length; i++) {
            board[i] = scan.nextLine().split("");
        }
        this.board = board;
    }

    public void printBoard() {
        UI.clearScreen();
        for (String[] row : board) {
            for (String square : row) {
                System.out.print(square);
            }
            System.out.println();
        }
    }

    public void printOnBoard(String[][] item, int y, int x) {
        for (int i = 0; i < item.length; i++) {
            for (int j = 0; j < item[i].length; j++) {
                UI.moveCursor(y + i, x + j);
                if (!item[i][j].equals(" ")) {
                    System.out.print(item[i][j]);
                }
            }
            System.out.println();
        }
    }

    public void clearBoard(String[][] item, int y, int x) {
        for (int i = 0; i < item.length; i++) {
            for (int j = 0; j < item[i].length; j++) {
                UI.moveCursor(y + i, x + j);
                if (!item[i][j].equals(" ")) {
                    System.out.print(board[y + i][x + j]);
                }
            }
            System.out.println();
        }
    }

    /**
     * @return the board
     */
    public String[][] getBoard() {
        return board;
    }
}
