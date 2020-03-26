package com.codecool.java.roguelikegame.board;

import java.util.Scanner;

import com.codecool.java.roguelikegame.CharacterInput;
import com.codecool.java.roguelikegame.UI;
import com.codecool.java.roguelikegame.beings.IconColor;
import com.codecool.java.roguelikegame.beings.Item;
import com.codecool.java.roguelikegame.beings.ItemTypes;

public class StartScreen {

    Board board;
    int makerIndex;
    final int OPTIONS_HIGHT = 15;
    final int OPTIONS_SHIFT = 67;
    final int ACTIONS_HIGHT = 29;
    final int ACTIONS_SHIFT = 4;

    public StartScreen() {
        makerIndex = 0;
    }

    public int displayStartScreen() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            board = new Board("src/main/java/com/codecool/java/roguelikegame/board/boards/start.txt");
            board.printBoard();
            printPotions();
            printOptions();
            printActions();
            boolean printedMenu = true;
            while (printedMenu) {
                printArrow();
                UI.moveCursor(40, 0);
                Thread.sleep(500);
                switch (CharacterInput.getNoEnterInput()) {
                    case 'w':
                        if (makerIndex > 0) {
                            clearArrow();
                            makerIndex--;
                        }
                        break;
                    case 's':
                        if (makerIndex < 3) {
                            clearArrow();
                            makerIndex++;
                        }
                        break;
                    case 'c':
                        if (makerIndex == 1) {
                            printBoardFrom(
                                    "src/main/java/com/codecool/java/roguelikegame/board/boards/instruction.txt");
                            scanner.nextLine();
                            printedMenu = false;
                        } else if (makerIndex == 2) {
                            printBoardFrom("src/main/java/com/codecool/java/roguelikegame/board/boards/credits.txt");
                            scanner.nextLine();
                            printedMenu = false;
                        } else {
                            return makerIndex;
                        }
                        break;
                }
            }
        }
    }

    private void printPotions() {
        Item first = new Item(ItemTypes.AGILITY, 2, 4);
        Item second = new Item(ItemTypes.AGILITY, 2, 139);
        printPotionsOnBoard(first, second);
        first = new Item(ItemTypes.STRENGTH, 2, 19);
        second = new Item(ItemTypes.STRENGTH, 2, 124);
        printPotionsOnBoard(first, second);
        first = new Item(ItemTypes.DEFENCE, 2, 34);
        second = new Item(ItemTypes.DEFENCE, 2, 109);
        printPotionsOnBoard(first, second);
        first = new Item(ItemTypes.HP, 2, 49);
        second = new Item(ItemTypes.HP, 2, 94);
        printPotionsOnBoard(first, second);
        first = new Item(ItemTypes.AGILITY, 2, 64);
        second = new Item(ItemTypes.AGILITY, 2, 79);
        printPotionsOnBoard(first, second);

    }

    private void printPotionsOnBoard(Item first, Item second) {
        board.printOnBoard(first.getIcon(), first.getyPosition(), first.getxPosition());
        board.printOnBoard(second.getIcon(), second.getyPosition(), second.getxPosition());
    }

    private void printOptions() {
        String[][] options = new String[10][1];
        for (int i = 0; i < options.length; i++) {
            options[i][0] = "|";
        }
        options[0][0] = "|START GAME";
        options[3][0] = "|INSTRUCTION";
        options[6][0] = "|CREDITS";
        options[9][0] = "|QUIT";
        board.printOnBoard(options, OPTIONS_HIGHT, OPTIONS_SHIFT);
    }

    private void printActions() {
        String[][] actions = new String[11][1];
        for (int i = 0; i < actions.length; i++) {
            actions[i][0] = " ";
        }
        actions[0][0] = "Actions:";
        actions[2][0] = "c - chose potion";
        actions[4][0] = "w - move up";
        actions[6][0] = "s - move down";
        actions[8][0] = "Enter what you want to do and press \"ENTER\"";
        board.printOnBoard(actions, ACTIONS_HIGHT, ACTIONS_SHIFT);
    }

    private void printArrow() {
        String[][] arrow = { { IconColor.RED.iconColor(), ">>>>>", IconColor.RESET.iconColor() } };
        board.printOnBoard(arrow, OPTIONS_HIGHT + 3 * makerIndex, OPTIONS_SHIFT - 7);
    }

    private void clearArrow() {
        String[][] arro = { { "", "", "", "", "", "" } };
        board.clearBoard(arro, OPTIONS_HIGHT + 3 * (makerIndex), OPTIONS_SHIFT - 6);
    }

    private void printBoardFrom(String fileName) {
        Board boardToPrint = new Board(fileName);
        boardToPrint.printBoard();
    }

}