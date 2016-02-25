import game.*;

import java.awt.*;

public class Skeleton {
    public static void main(String[] args) {
        Game game = new Game(new BoardSize(10));
        game.start()
            .placeShipPlayerOne(new Ship(ShipType.SS), new Field(1,1))
            .placeShipPlayerOne(new Ship(ShipType.DD), new Field(2, 2), new Field(1,2))
            .placeShipPlayerOne(new Ship(ShipType.CA), new Field(4, 1), new Field(4,2), new Field(4,3))
            .placeShipPlayerOne(new Ship(ShipType.BB), new Field(6, 1), new Field(6,2), new Field(6,3), new Field(6,4))
            .placeShipPlayerTwo(new Ship(ShipType.SS), new Field(1,1))
            .placeShipPlayerTwo(new Ship(ShipType.DD), new Field(2, 2), new Field(1,2))
            .placeShipPlayerTwo(new Ship(ShipType.CA), new Field(4, 1), new Field(4,2), new Field(4,3))
            .placeShipPlayerTwo(new Ship(ShipType.BB), new Field(6, 1), new Field(6,2), new Field(6,3), new Field(6,4))
            .print()
            .shootOnPlayerOneBoard(new Point(1,1))
            .shootOnPlayerOneBoard(new Point(1,5))
            .shootOnPlayerOneBoard(new Point(2,2))
            .shootOnPlayerOneBoard(new Point(1,2))
            .print();
    }
}
