package com.codecool.java.roguelikegame;

import java.io.IOException;

import com.codecool.java.roguelikegame.beings.Being;
import com.codecool.java.roguelikegame.beings.Item;
import com.codecool.java.roguelikegame.beings.ItemTypes;
import com.codecool.java.roguelikegame.beings.Player;
import com.codecool.java.roguelikegame.board.Inventory;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Being player = new Player(5, 5); // set player starting position
        Stage stage1 = new Stage1(player);
        Inventory inventory = new Inventory();
        inventory.addItem(new Item(ItemTypes.AGILITY, 0, 0));
        inventory.addItem(new Item(ItemTypes.HP, 0, 0));
        inventory.addItem(new Item(ItemTypes.STRENGTH, 0, 0));
        inventory.addItem(new Item(ItemTypes.AGILITY, 0, 0));
        inventory.addItem(new Item(ItemTypes.AGILITY, 0, 0));
        inventory.addItem(new Item(ItemTypes.DEFENCE, 0, 0));
        inventory.addItem(new Item(ItemTypes.STRENGTH, 0, 0));
        inventory.inventoryScreen(player);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*
        stage1.gameLoop();
        */
    }
}
