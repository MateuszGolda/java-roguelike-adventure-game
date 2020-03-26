package com.codecool.java.roguelikegame.board;

import com.codecool.java.roguelikegame.beings.Being;
import java.util.Scanner;

import java.util.Random;

public class Battle {

    Random rand = new Random();
    Scanner userInput = new Scanner(System.in);
    String actions;
    Being player;
    Inventory inventory;
    Being monster;
    Board board;
    int playerActionPoints;
    int monsterActionPoints;
    final int PLAYER_INDEX = 0;
    final int MONSTER_INDEX = 1;
    final int ICONS_Y = 4;
    final int PLAYER_X = 4;
    final int MONSTER_X = 125;
    final int ACTION_Y = 5;
    final int PLAYER_ACTION_X = 45;
    final int MONSTER_ACTION_X = 85;
    final int STATISTICS_Y = 30;
    final int PLAYER_STATS_X = 80;

    public Battle(Being player, Inventory inventory, Being monster) {
        this.player = player;
        this.inventory = inventory;
        this.monster = monster;
        this.board = new Board("src/main/java/com/codecool/java/roguelikegame/board/boards/battle.txt");
    }

    public Being makeBattle() {
        printBattleBoard();
        while (player.getHp() > 0 && monster.getHp() > 0) {
            printStatsOnBoard();
            choseActions();
            checkIfSleep();
            addDefence();
            makeAttack();
            removeDefence();
            player.addExp(playerActionPoints);
            printActionsOnBoard();
            clearStatsFromBoard();
        }
        printBattleResult();
        return player;
    }

    private void printBattleResult() {
        if (player.getHp() > 0) {
            printResult("src/main/java/com/codecool/java/roguelikegame/board/boards/win.txt");
        } else {
            printResult("src/main/java/com/codecool/java/roguelikegame/board/boards/lose.txt");
        }
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
    }

    private void printBattleBoard() {
        board.printBoard();
        board.printOnBoard(player.getIcon(), ICONS_Y, PLAYER_X);
        board.printOnBoard(monster.getIcon(), ICONS_Y, MONSTER_X);
    }

    private void choseActions() {
        actions = "";
        do {
            switch (userInput.next()) {
                case "i":
                    player = inventory.inventoryScreen(player);
                    break;
                case "a":
                    actions += "A";
                    break;
                case "d":
                    actions += "D";
                    break;
                case "s":
                    actions += "S";
                    break;
        
                default:
                    break;
            }
        } while (actions == "");
        actions += getMonsterRandomAction();
    }

    private String getMonsterRandomAction() {
        String[] actions = {"A", "D", "S"};
        int number = rand.nextInt(3);
        return actions[number];
    }

    private void checkIfSleep() {
        if (actions.charAt(PLAYER_INDEX) == 'S') {
            playerActionPoints = rand.nextInt(11);
            player.addHp(playerActionPoints);
        }
        if (actions.charAt(MONSTER_INDEX) == 'S') {
            monsterActionPoints = rand.nextInt(11);
            monster.addHp(monsterActionPoints);
        }
    }

    private void addDefence() {
        if (actions.charAt(PLAYER_INDEX) == 'D') {
            playerActionPoints = player.getDefence();
            player.addDefence(playerActionPoints);
        }
        if (actions.charAt(MONSTER_INDEX) == 'D') {
            monsterActionPoints = monster.getDefence();
            monster.addDefence(monsterActionPoints);
        }
    }

    private void removeDefence() {
        if (actions.charAt(PLAYER_INDEX) == 'D') {
            player.subtractDefence(playerActionPoints);
        }
        if (actions.charAt(MONSTER_INDEX) == 'D') {
            monster.subtractDefence(monsterActionPoints);
        }
    }

    private void makeAttack() {
        if (actions.charAt(PLAYER_INDEX) == 'A') {
            playerActionPoints = getAttackPoints("player");
            if (playerActionPoints < 0) {
                playerActionPoints = 0;
            }
            player.subtractHp(playerActionPoints);
        }
        if (actions.charAt(MONSTER_INDEX) == 'A') {
            monsterActionPoints = getAttackPoints("monster");
            if (monsterActionPoints < 0) {
                monsterActionPoints = 0;
            }
            monster.subtractHp(monsterActionPoints);
        }
    }

    private int getAttackPoints(String name) {
        if (name.equals("player")) {
            int number = player.getStrength() * 4 - monster.getDefence();
            number += monster.getAgility();
            int attack = rand.nextInt(number);
            return attack - monster.getAgility();
        } else {
            int number = monster.getStrength() * 4 - player.getDefence();
            number += player.getAgility();
            int attack = rand.nextInt(number);
            return attack - player.getAgility();
        }
    }

    private void printActionsOnBoard() {
        printByAction(actions.charAt(PLAYER_INDEX), "player");
        printByAction(actions.charAt(MONSTER_INDEX), "monster");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
        String cleaner = "                   ";
        printByName("player", cleaner, cleaner);
        printByName("monster", cleaner, cleaner);
    }

    private void printByAction(char action, String name) {
        switch (action) {
            case 'A':
                printByName(name, "ATTACK");
                break;
            case 'D':
                printByName(name, "DEFENCE");
                break;
            case 'S':
                printByName(name, "SLEEP");
                break;
        }
    }

    private void printByName(String name, String actionName) {
        printByName(name, actionName, "# %d HP");
    }

    private void printByName(String name, String actionName, String info) {
        if (name.equals("player")){
            String[][] message = {{actionName}, {" "}, {String.format(info, playerActionPoints)}};
            board.printOnBoard(message, ACTION_Y, PLAYER_ACTION_X);
        } else {
            String[][] message = {{actionName}, {" "}, {String.format(info, monsterActionPoints)}};
            board.printOnBoard(message, ACTION_Y, MONSTER_ACTION_X);
        }
    }

    private void printStatsOnBoard() {
        board.printOnBoard(getPlayerStats(), STATISTICS_Y, PLAYER_STATS_X);
        board.printOnBoard(getMonsterStats(), STATISTICS_Y, PLAYER_STATS_X + 35);
    }

    private String[][] getPlayerStats() {
        String[][] playerStatistics = {{"PLAYER:"}, {" "}, {String.format("HP: %d", player.getHp())}, {" "}, 
                {String.format("STRENGTH: %d", player.getStrength())}, {" "},
                {String.format("DEFENCE: %d", player.getDefence())}, {" "}, 
                {String.format("AGILITY: %d", player.getAgility())}};
        return playerStatistics;
    }

    private String[][] getMonsterStats() {
        String[][] monsterStatistics = {{"MONSTER:"}, {" "}, {String.format("HP: %d", monster.getHp())}, {" "}, 
                {String.format("STRENGTH: %d", monster.getStrength())}, {" "},
                {String.format("DEFENCE: %d", monster.getDefence())}, {" "}, 
                {String.format("AGILITY: %d", monster.getAgility())}};
        return monsterStatistics;
    }

    private void clearStatsFromBoard() {
        String cleaner = "                              ";
        String[][] cleanerItem = new String[5][1];
        for (int i = 0; i < cleanerItem.length; i++) {
            cleanerItem[i][0] = cleaner;
        }
        board.printOnBoard(cleanerItem, STATISTICS_Y, PLAYER_STATS_X);
        board.printOnBoard(cleanerItem, STATISTICS_Y, PLAYER_STATS_X + 35);

    }

    private void printResult(String name) {
        Board resultBoard = new Board(name);
        resultBoard.printBoard();
    }

    

}