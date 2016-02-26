package game;

import Interface.*;
import Interface.Dialogs.PlayerWinDialog;
import game.board.BoardSize;
import game.board.fields.Field;
import game.fleet.Ship;
import game.player.Player;
import game.player.PlayerNumber;

import java.awt.*;

public class Game {

    BoardSize boardSize;
    Player playerOne;
    Player playerTwo;
    ShipPlacementWindow shipPlacementWindow;
    BoardWindow boardWindow;
    Player currentPlayer;

    public Game(BoardSize boardSize) {
        this.boardSize = boardSize;
        playerOne = new Player(boardSize, new PlayerNumber(1));
        playerTwo = new Player(boardSize, new PlayerNumber(2));
        currentPlayer = playerOne;
        shipPlacementWindow = new ShipPlacementWindow(this, playerOne);
    }

    public Game start() {
        playerOne = new Player(boardSize, new PlayerNumber(1));
        playerTwo = new Player(boardSize, new PlayerNumber(2));
        return this;
    }


    public boolean placeShip(Ship ship, Player player, Field... fields) {
        return player.placeShip(ship,fields);
    }

    public Game shootOnPlayerBoard(Point point, Player player) {
        if (!player.shoot(point)){
            if (player.equals(playerTwo)) currentPlayer=playerTwo;
            else currentPlayer=playerOne;
        }
        if (playerOne.hasNoShips()){
            boardWindow.dispose();
            PlayerWinDialog dialog = new PlayerWinDialog(2);
            dialog.setVisible(true);
        }
        else if (playerTwo.hasNoShips()){
            boardWindow.dispose();
            PlayerWinDialog dialog = new PlayerWinDialog(1);
            dialog.setVisible(true);
        }
        else{
            shootSequence();
        }
        return this;
    }

    public Ship nextShipToPlace(Player player) {
        Ship ship = player.getShipToPlaceOnBoard();
        if (ship==null) {
            if (currentPlayer.equals(playerOne)){
                shipPlacementWindow.dispose();
                shipPlacementWindow = new ShipPlacementWindow(this,playerTwo);
                currentPlayer = playerTwo;
            }
            else startShootingSequence();
        }
        return ship;
    }

    private void startShootingSequence() {
        shipPlacementWindow.dispose();
        boardWindow = new BoardWindow(this, playerOne, playerTwo);
        boardWindow.setVisible(true);
        currentPlayer=playerOne;
        shootSequence();
    }


    private void shootSequence() {

        if (currentPlayer.equals(playerOne)){
            boardWindow.playerTwoIsShot();
        }
        else{
            boardWindow.playerOneIsShot();
        }
    }

    public Game print() {
        System.out.println(playerOne);
        System.out.println(playerTwo);
        return this;
    }
}
