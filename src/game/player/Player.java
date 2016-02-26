package game.player;

import game.rules.Shoot;
import game.board.Board;
import game.board.BoardSize;
import game.board.fields.Field;
import game.board.fields.FieldsList;
import game.fleet.Fleet;
import game.fleet.Ship;
import game.rules.ShipSettings;
import game.fleet.ShipType;

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
    public boolean hasPlacedAllShipsOnBoard(){
        return fleet.getFirstFree()==null;
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
