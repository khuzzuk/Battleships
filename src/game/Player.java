package game;

import java.util.List;

/**
 * Created by Adrian on 18.02.2016.
 */
public class Player {
    Board board;
    Fleet fleet;
    public Player(BoardSize boardSize) {
        board = new Board(boardSize);
        List<ShipType> ships = ShipPlacement.startingShipList();
        fleet = new Fleet(ships);
    }
    public boolean hasPlacedAllShipsOnBoard(){
        if (fleet.getFirstFree()==null) return true;
        return false;
    }

    public boolean placeShip(Ship ship, Field... fields) {
        Field[] fromFieldsList = new Field[fields.length];
        if (fleet.canBePlacedOnBoard(ship,fields)) {
            for (int i = 0; i < fields.length; i++) {
                fromFieldsList[i] = board.fieldsList.get(fields[i]);
            }
            return fleet.placeShip(ship, fromFieldsList);
        }
        return false;
    }
}
