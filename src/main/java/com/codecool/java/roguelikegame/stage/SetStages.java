package com.codecool.java.roguelikegame.stage;

import com.codecool.java.roguelikegame.beings.Being;
import com.codecool.java.roguelikegame.beings.ItemTypes;
import com.codecool.java.roguelikegame.beings.Monster;
import com.codecool.java.roguelikegame.beings.Player;
import com.codecool.java.roguelikegame.beings.Point;
import com.codecool.java.roguelikegame.board.Inventory;
import com.codecool.java.roguelikegame.beings.Item;

/**
 * SetStages
 */
public class SetStages {

    public static Stage[] getStages() {
        Inventory inventory = new Inventory();
        Being player = new Player(5, 5); // set player starting position

        Stage[] stages = new Stage[4];
        stages[0] = new Stage(player, inventory);
        stages[0].setBoard("src/main/resources/map1.txt");
        stages[0].addEnemy(new Monster(2, 2, 20, 2, 7, 50, 1));
        stages[0].addItem(new Item(ItemTypes.HP, 8, 30));
        stages[0].setNextStageDoor(new Point(13, 120));
        stages[0].setSecondStageDoor(new Point(29, 31));
        stages[0].setPlayerNextStageY(32);
        stages[0].setPlayerNextStageX(140);
        stages[0].setPlayerSecondStageY(30);
        stages[0].setPlayerSecondStageX(3);

        stages[1] = new Stage(player, inventory);
        stages[1].setBoard("src/main/resources/map2.txt");
        stages[1].addEnemy(new Monster(2, 2, 20, 2, 36, 8, 1));
        stages[1].addItem(new Item(ItemTypes.HP, 35, 1));
        stages[1].setPreviousStageDoor(new Point(32, 144));
        stages[1].setPlayerPreviousStageY(14);
        stages[1].setPlayerPreviousStageX(120);

        stages[2] = new Stage(player, inventory);
        stages[2].setBoard("src/main/resources/map3.txt");
        stages[2].addEnemy(new Monster(5, 5, 30, 5, 32, 93, 2));
        stages[2].addItem(new Item(ItemTypes.HP, 10, 50));
        stages[2].setNextStageDoor(new Point(31, 95));
        stages[2].setPlayerNextStageY(22);
        stages[2].setPlayerNextStageX(3);

        stages[3] = new Stage(player, inventory);
        stages[3].setBoard("src/main/resources/map4.txt");
        stages[3].addEnemy(new Monster(10, 10, 70, 10, 10, 70, 3));
        stages[3].addItem(new Item(ItemTypes.HP, 22, 12));

        return stages;
    }
}