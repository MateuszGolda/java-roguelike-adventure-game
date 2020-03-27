package helpers;

/**
 * UI
 */
public class UI {

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
    }

    public static void moveCursor(int y, int x) {
        System.out.print("\033[" + ++y + ";" + ++x + "H");
    }
}
