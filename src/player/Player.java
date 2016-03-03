package player;

import board.Board;
import board.BoardSize;
import board.fields.Field;
import board.fields.FieldsList;
import fleet.Fleet;
import fleet.Ship;
import fleet.ShipType;
import rules.ShipSettings;
import rules.Shoot;

import java.awt.*;
import java.util.List;

public class Player {
    Board board;
    Fleet fleet;
    PlayerNumber id;
    public Player(BoardSize boardSize, PlayerNumber id) {
        board = new Board(boardSize);
        this.id=id;
        List<ShipType> ships = ShipSettings.startingShipList();
        fleet = new Fleet(ships);
    }

    public boolean placeShip(Ship ship, Field... fields) {
        if (fleet.canBePlacedOnBoard(ship,fields)) {
            Field[] fromFieldsList = new Field[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fromFieldsList[i] = board.get(fields[i]);
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
        return board.getFieldsList();
    }

    public boolean hasNoShips(){
        return fleet.numberOfShips()==0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return id != null ? id.equals(player.id) : player.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
