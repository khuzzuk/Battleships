import game.*;

/**
 * Created by adrabik on 23.02.16.
 */
public class Skeleton {
    public static void main(String[] args) {
        Game game = new Game(new BoardSize(10));
        game.start()
            .placeShipPlayerOne(new Ship(ShipType.SS), new Field(1,1))
            .print()
            .placeShipPlayerOne(new Ship(ShipType.DD), new Field(1, 1), new Field(1,2))
            .print();
    }
}
