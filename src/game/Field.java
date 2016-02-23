package game;

import java.awt.*;

/**
 * Created by adrabik on 23.02.16.
 */
public class Field {
    Point position;
    Sign sign;

    public Field(int x, int y, Sign sign) {
        this.position = new Point(x, y);
        this.sign = sign;

    }

    @Override
    public String toString() {
        return sign.toString();
    }

    public void markShip() {
        sign = Sign.SHIP;
    }
}
