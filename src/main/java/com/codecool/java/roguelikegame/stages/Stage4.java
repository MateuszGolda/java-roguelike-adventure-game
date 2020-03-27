package com.codecool.java.roguelikegame.stages;

import com.codecool.java.roguelikegame.beings.Being;
import com.codecool.java.roguelikegame.beings.Item;
import com.codecool.java.roguelikegame.beings.ItemTypes;
import com.codecool.java.roguelikegame.beings.Monster;
import com.codecool.java.roguelikegame.board.Board;
import com.codecool.java.roguelikegame.board.Inventory;

/**
 * Stage1
 */
public class Stage4 extends Stage {

    public Stage4(Being player, Inventory inventory) {
        super(player, inventory);
    }

    @Override
    protected void setBoard() {
        super.board = new Board("src/main/java/com/codecool/java/roguelikegame/board/boards/map4.txt");
    }

    @Override
    protected void addEnemies() {
        enemies.add(new Monster(7, 7, 50, 7, 10, 70, 3));
    }

    @Override
    protected void addItems() {
        items.add(new Item(ItemTypes.HP, 22, 12));
    }

    @Override
    protected void addDoorToNextStage() {
        ;
    }

    @Override
    protected void addPlayerNextStagePosition() {
        ;
    }
}
