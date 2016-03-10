package game;

import board.BoardSize;
import board.fields.Field;
import fleet.Ship;
import gameInterface.BoardWindow;
import gameInterface.Dialogs.PlayerWinDialog;
import messagingHandler.Messages.*;
import messagingHandler.Subscribers.Subscriber;
import player.Player;
import player.PlayerNumber;

import java.awt.*;

import static messagingHandler.MessageSender.send;

public class Game <T extends Message> implements Subscriber<T> {
    BoardSize boardSize;
    Player playerOne;
    Player playerTwo;
    BoardWindow boardWindow;
    Player currentPlayer;

    public Game() {
        makeSubscriptions();
        send(new StartingMessage());
    }

    private void makeSubscriptions(){
        subscribe(BoardSizeDecided.class);
        subscribe(ShipPlaced.class);
        subscribe(SwapPlayerPlacingShipMessage.class);
        subscribe(CallResizeBoardWindow.class);
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
        }
        return ship;
    }
    private void swapPlacingShipPlayer(){
        if (currentPlayer.equals(playerOne)){
            currentPlayer = playerTwo;
            send(new FinishPlacingShipForPlayer());
            send(new PlayerStartsPlacingShips(this,currentPlayer));
            send(new NextShipPlaceMessage(nextShipToPlace(currentPlayer)));
        }
        else startShootingSequence();
    }

    private void startShootingSequence() {
        send(new FinishPlacingShipForPlayer());
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
    public void receiveMessage(T message) {
        if (message.getClass()==BoardSizeDecided.class)
            receiveMessage((BoardSizeDecided) message);
        else if (message.getClass()==ShipPlaced.class)
            receiveMessage((ShipPlaced) message);
        else if (message.getClass()==SwapPlayerPlacingShipMessage.class)
            receiveMessage((SwapPlayerPlacingShipMessage) message);
        else if (message.getClass()==CallResizeBoardWindow.class)
            receiveMessage((CallResizeBoardWindow) message);
    }
    public void receiveMessage(BoardSizeDecided message){
        setupGame(message.getBoardSize());
        send(new PlayerStartsPlacingShips(this, currentPlayer));
        send(new NextShipPlaceMessage(nextShipToPlace(currentPlayer)));
    }
    public void receiveMessage(ShipPlaced message){
        Ship ship = message.getShip();
        Player player = message.getPlayer();
        Field[] fields = message.getFields();
        if (placeShip(ship,player,fields)){
            Ship nextShip = nextShipToPlace(currentPlayer);
            if (nextShip!=null)
                send(new NextShipPlaceMessage(nextShip));
            else send(new SwapPlayerPlacingShipMessage());
        }
        else{
            send(new WrongShipPositionMessage());
        }
    }
    public void receiveMessage(SwapPlayerPlacingShipMessage m){
        swapPlacingShipPlayer();
    }
    public void receiveMessage(CallResizeBoardWindow m){
        shootSequence();
    }
}
