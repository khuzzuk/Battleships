package game;

import Interface.*;
import Interface.Dialogs.PlayerShootDialog;

import java.awt.*;

public class Game {

    BoardSize boardSize;
    Player playerOne;
    Player playerTwo;
    MainWindow mainWindow;
    BoardWindow boardWindow;
    private Player currentPlayer;

    public Game(BoardSize boardSize) {
        this.boardSize = boardSize;
        playerOne = new Player(boardSize, new PlayerNumber(1));
        playerTwo = new Player(boardSize, new PlayerNumber(2));
        WinningConditions logic = new WinningConditions();
        mainWindow = new ShipPlacementWindowPlayerOne(this);
    }

    public Game start() {
        playerOne = new Player(boardSize, new PlayerNumber(1));
        playerTwo = new Player(boardSize, new PlayerNumber(2));
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
        if (player.shoot(point)){
            shootSequence();
        }
        else {
            if (player.playerNumber.number==2) currentPlayer=playerTwo;
            else currentPlayer=playerOne;
            shootSequence();
        }
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
        currentPlayer=playerOne;
        shootSequence();
    }

    private void shootSequence() {
        if (currentPlayer.playerNumber.number==1){
            PlayerShootDialog dialog = new PlayerShootDialog(1);
            dialog.setVisible(true);
            boardWindow.playerTwoIsShot();
        }
        else{
            PlayerShootDialog dialog = new PlayerShootDialog(2);
            dialog.setVisible(true);
            boardWindow.playerOneIsShot();
        }
    }
}
