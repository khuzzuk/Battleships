package game;

import gameInterface.*;
import gameInterface.Dialogs.PlayerWinDialog;
import board.BoardSize;
import board.fields.Field;
import fleet.Ship;
import static messagingHandler.MessageSender.*;

import messagingHandler.Actions.Action;
import messagingHandler.Actions.NotifyWithBoardSize;
import messagingHandler.Messages.StartingMessage;
import messagingHandler.Subscriber;
import player.Player;
import player.PlayerNumber;

import java.awt.*;

public class Game implements Subscriber {
    BoardSize boardSize;
    Player playerOne;
    Player playerTwo;
    ShipPlacementWindow shipPlacementWindow;
    BoardWindow boardWindow;
    Player currentPlayer;

    public Game(BoardSize boardSize) {
        subscribe();
        send(new StartingMessage());
    }

    private void setupGame(BoardSize boardSize) {
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

    public void notifySubscriber(NotifyWithBoardSize action) {
        setupGame(action.getBoardSize());
    }

    @Override
    public void notifySubscriber(Action action) {
        if (action.getClass() == NotifyWithBoardSize.class){
            notifySubscriber((NotifyWithBoardSize) action);
        }
    }
}
