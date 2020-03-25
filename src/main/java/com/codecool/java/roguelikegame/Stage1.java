package com.codecool.java.roguelikegame;

import com.codecool.java.roguelikegame.beings.Player;
import com.codecool.java.roguelikegame.board.Board;

/**
 * Stage1
 */
public class Stage1 extends Stage {

    Stage1() {
        super(new Board("src/main/java/com/codecool/java/roguelikegame/board/boards/map1.txt"),
                new Player(5, 5, 5, 1, 5, 5));
    }
}