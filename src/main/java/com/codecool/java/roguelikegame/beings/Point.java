package com.codecool.java.roguelikegame.beings;

import java.util.Objects;

/**
 * Point
 */
public class Point {
    public final int y;
    public final int x;

    Point(int y, int x) {
        this.y = y;
        this.x = x;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Point)) {
            return false;
        }
        Point point = (Point) obj;
        return point.y == y &&
               point.x == x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }
}
