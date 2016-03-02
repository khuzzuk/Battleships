package rules;

import board.Board;
import board.fields.Field;
import fleet.Fleet;

import java.awt.*;

public class Shoot {

        public static boolean shoot(Point point, Board board, Fleet fleet) {
            Field shotField = new Field((int) point.getX(), (int) point.getY());
            if (board.hasShip(shotField)){
                fleet.shootShip(shotField);
                return board.hit(shotField);
            }
            board.miss(shotField);
            return false;
        }

}
