package game;

import java.awt.*;
import java.util.List;

public class Player {
    Board board;
    Fleet fleet;
    public Player(BoardSize boardSize) {
        board = new Board(boardSize);
        List<ShipType> ships = ShipPlacement.startingShipList();
        fleet = new Fleet(ships);
    }
    public boolean hasPlacedAllShipsOnBoard(){
        return fleet.getFirstFree()==null;
    }

    public boolean placeShip(Ship ship, Field... fields) {
        if (fleet.canBePlacedOnBoard(ship,fields)) {
            Field[] fromFieldsList = new Field[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fromFieldsList[i] = board.fieldsList.get(fields[i]);
            }
            return fleet.placeShip(ship, fromFieldsList);
        }
        return false;
    }

    public boolean shoot(Point point) {
        return Shoot.shoot(point, board, fleet);
    }

    public Ship getShipToPlaceOnBoard() {
        return fleet.getFirstFree();
    }

    public BoardSize getBoardSize() {
        return board.boardSize;
    }

    public FieldsList fieldsList() {
        return board.fieldsList;
    }
}
