package game;

import board.BoardSize;
import board.fields.Field;
import fleet.Ship;
import gameInterface.BoardWindow;
import gameInterface.Dialogs.PlayerWinDialog;
import gameInterface.ShipPlacementWindow;
import messagingHandler.Actions.GeneralAction;
import messagingHandler.Actions.NotifyWithBoardSize;
import messagingHandler.Actions.PlaceShipOnBoardAction;
import messagingHandler.Messages.NextShipPlaceMessage;
import messagingHandler.Messages.PlayerStartsPlacingShips;
import messagingHandler.Messages.StartingMessage;
import messagingHandler.Subscribers.Subscriber;
import player.Player;
import player.PlayerNumber;

import java.awt.*;

import static messagingHandler.MessageSender.send;

public class Game implements Subscriber<GeneralAction> {
    BoardSize boardSize;
    Player playerOne;
    Player playerTwo;
    ShipPlacementWindow shipPlacementWindow;
    BoardWindow boardWindow;
    Player currentPlayer;

    public Game() {
        subscribe();
        send(new StartingMessage());
    }

    void setupGame(BoardSize boardSize) {
        this.boardSize = boardSize;
        playerOne = new Player(boardSize, new PlayerNumber(1));
        playerTwo = new Player(boardSize, new PlayerNumber(2));
        currentPlayer = playerOne;
    }

    public Game start() {
        playerOne = new Player(boardSize, new PlayerNumber(1));
        playerTwo = new Player(boardSize, new PlayerNumber(2));
        return this;
    }


    public boolean placeShip(Ship ship, Player player, Field... fields) {
        boolean p =player.placeShip(ship,fields);
        return p;
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

    @Override
    public void notifySubscriber(GeneralAction action) {
    }
    public void notifySubscriber(NotifyWithBoardSize action){
        setupGame(action.getBoardSize());
        send(new PlayerStartsPlacingShips(this, currentPlayer));
        send(new NextShipPlaceMessage(nextShipToPlace(currentPlayer)));
    }
    public void notifySubscriber(PlaceShipOnBoardAction action){
        Ship ship = action.getShip();
        Player player = action.getPlayer();
        Field[] fields = action.getFields();
        if (placeShip(ship,player,fields)){
            Ship nextShip = nextShipToPlace(currentPlayer);
            send(new NextShipPlaceMessage(nextShip));
        }
        else{
            System.out.println("wrong ship");
        }
    }
}
