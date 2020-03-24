package com.codecool.java.roguelikegame;

/**
 * Game
 */
public class Game {

    public static void game() {
        int c;
        while (true) {
            try {
                Thread.sleep(45); // sleep 45 milliseconds, to not overload CPU, more would cause lag
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            c = Character.toLowerCase(CharacterInput.getNoEnterInput());

            if (c == 'a') {
                System.out.println("a pressed");
            }
            if (c == 'w') {
                System.out.println("w pressed");
            }
            if (c == 's') {
                System.out.println("s pressed");
            }
            if (c == 'd') {
                System.out.println("d pressed");
            }
            if (c == 'q') {
                break;
            }
        }
    }
}