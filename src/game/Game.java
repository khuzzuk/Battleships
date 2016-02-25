package game;

import Interface.*;

import java.awt.*;

public class Game {

    BoardSize boardSize;
    Player playerOne;
    Player playerTwo;
    MainWindow mainWindow;
    BoardWindow boardWindow;

    public Game(BoardSize boardSize) {
        this.boardSize = boardSize;
        playerOne = new Player(boardSize);
        playerTwo = new Player(boardSize);
        WinningConditions logic = new WinningConditions();
        mainWindow = new ShipPlacementWindowPlayerOne(this);
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

    public boolean placeShipPlayerTwo(Ship ship, Field... fields) {
        return playerTwo.placeShip(ship,fields);
    }

    public Game shootOnPlayerBoard(Point point, Player player) {
        player.shoot(point);
        shootSequence();
        return this;
    }

    public Ship nextShipToPlacePlayerOne() {
        Ship ship = playerOne.getShipToPlaceOnBoard();
        if (ship==null) {
            mainWindow.dispose();
            mainWindow = new ShipPlacementWindowPlayerTwo(this);
            return ship;
        }
        else return ship;
    }

    public Ship nextShipToPlacePlayerTwo() {
        Ship ship = playerTwo.getShipToPlaceOnBoard();
        if (ship==null) {
            startShootingSequence();
            return ship;
        }
        else return ship;
    }

    private void startShootingSequence() {
        mainWindow.dispose();
        boardWindow = new BoardWindow(this, playerOne, playerTwo);
        boardWindow.setVisible(true);
        shootSequence();
    }

    private void shootSequence() {
        boardWindow.playerOneIsShot();
    }
}
