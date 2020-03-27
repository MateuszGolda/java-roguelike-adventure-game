package com.codecool.java.roguelikegame.stages;

import com.codecool.java.roguelikegame.beings.Being;
import com.codecool.java.roguelikegame.beings.Item;
import com.codecool.java.roguelikegame.beings.ItemTypes;
import com.codecool.java.roguelikegame.beings.Monster;
import com.codecool.java.roguelikegame.beings.Point;
import com.codecool.java.roguelikegame.board.Board;
import com.codecool.java.roguelikegame.board.Inventory;

/**
 * Stage1
 */
public class Stage1 extends Stage {

    public Stage1(Being player, Inventory inventory) {
        super(player, inventory);
    }

    @Override
    protected void setBoard() {
        super.board = new Board("src/main/resources/map1.txt");
    }

    @Override
    protected void addEnemies() {
        enemies.add(new Monster(2, 2, 20, 2, 7, 50, 1));
    }

    @Override
    protected void addItems() {
        items.add(new Item(ItemTypes.HP, 8, 30));
    }

    @Override
    protected void addDoorToNextStage() {
        super.nextStageDoor = new Point(13, 120);
        super.secondStageDoor = new Point(28, 31);
    }

    @Override
    protected void addPlayerNextStagePosition() {
        super.playerNextStageY = 32;
        super.playerNextStageX = 143;
        super.playerSecondStageY = 30;
        super.playerSecondStageX = 3;
    }
}
