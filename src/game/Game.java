package game;

import java.awt.*;

/**
 * Created by adrabik on 23.02.16.
 */
public class Game {

    BoardSize boardSize;
    Player playerOne;
    Player playerTwo;

    public Game(BoardSize boardSize) {
        this.boardSize = boardSize;
        playerOne = new Player(boardSize);
        playerTwo = new Player(boardSize);
        WinningConditions logic = new WinningConditions();
    }

    public Game start() {
        playerOne = new Player(boardSize);
        playerTwo = new Player(boardSize);
        WinningConditions logic = new WinningConditions();
        return this;
    }


    public boolean placeShipPlayerOne(Ship ship, Field... fields) {
        return playerOne.placeShip(ship,fields);
    }

    public Game print() {
        System.out.println(playerOne.board);
        System.out.println(playerTwo.board);
        return this;
    }

    public Game placeShipPlayerTwo(Ship ship, Field... fields) {
        playerTwo.placeShip(ship,fields);
        return this;
    }

    public Game shootOnPlayerOneBoard(Point point) {
        playerOne.shoot(point);
        return this;
    }

    public Ship nextShipToPlace() {
        Ship ship = playerOne.getShipToPlaceOnBoard();
        if (ship==null) ship=playerTwo.getShipToPlaceOnBoard();
        return ship;
    }
}
