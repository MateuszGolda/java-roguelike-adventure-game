package com.codecool.java.roguelikegame;

import com.codecool.java.roguelikegame.beings.Being;
import com.codecool.java.roguelikegame.beings.Item;
import com.codecool.java.roguelikegame.beings.ItemTypes;
import com.codecool.java.roguelikegame.beings.Monster;
import com.codecool.java.roguelikegame.board.Board;

/**
 * Stage1
 */
public class Stage1 extends Stage {

    Stage1(Being player) {
        super(player);
    }

    @Override
    protected void setBoard() {
        super.board = new Board("src/main/java/com/codecool/java/roguelikegame/board/boards/map1.txt");
    }

    @Override
    protected void addEnemies() {
        enemies.add(new Monster(2, 2, 20, 2, 7, 50));
    }

    @Override
    protected void addItems() {
        items.add(new Item(ItemTypes.HP, 8, 30));
    }
}
