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
    }

    public Game start() {
        playerOne = new Player(boardSize);
        playerTwo = new Player(boardSize);
        Logic logic = new Logic();
        return this;
    }


    public Game placeShipPlayerOne(Ship ship, Field... fields) {
        playerOne.placeShip(ship,fields);
        return this;
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
}
