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
public class Stage2 extends Stage {

    public Stage2(Being player, Inventory inventory) {
        super(player, inventory);
    }

    @Override
    protected void setBoard() {
        super.board = new Board("src/main/java/com/codecool/java/roguelikegame/board/boards/map2.txt");
    }

    @Override
    protected void addEnemies() {
        enemies.add(new Monster(2, 2, 20, 2, 30, 5));
    }

    @Override
    protected void addItems() {
        items.add(new Item(ItemTypes.HP, 22, 120));
    }

    @Override
    protected void addDoorToNextStage() {
        super.previousStageDoor = new Point(32, 144);
    }

    @Override
    protected void addPlayerNextStagePosition() {
        super.playerPreviousStageY = 14;
        super.playerPreviousStageX = 120;
    }
}
