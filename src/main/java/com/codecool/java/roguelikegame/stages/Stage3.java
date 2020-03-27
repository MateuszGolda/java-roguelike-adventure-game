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
public class Stage3 extends Stage {

    public Stage3(Being player, Inventory inventory) {
        super(player, inventory);
    }

    @Override
    protected void setBoard() {
        super.board = new Board("src/main/java/com/codecool/java/roguelikegame/board/boards/map3.txt");
    }

    @Override
    protected void addEnemies() {
        enemies.add(new Monster(4, 4, 30, 4, 32, 93, 2));
    }

    @Override
    protected void addItems() {
        items.add(new Item(ItemTypes.HP, 10, 50));
    }

    @Override
    protected void addDoorToNextStage() {
        super.nextStageDoor = new Point(31, 95);
    }

    @Override
    protected void addPlayerNextStagePosition() {
        super.playerNextStageY = 22;
        super.playerNextStageX = 3;
    }
}
