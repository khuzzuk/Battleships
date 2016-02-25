package game;

import java.awt.*;

public class Shoot {

        public static boolean shoot(Point point, Board board) {
            Field shootedField = new Field((int) point.getX(), (int) point.getY());
            if (board.hasShip(shootedField)){
                return board.hit(shootedField);
            }
            board.miss(shootedField);
            return false;
        }

}
