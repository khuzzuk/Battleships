package gameInterface;

import board.fields.Field;
import fleet.Ship;
import game.Game;
import gameInterface.Dialogs.DisclosureDialog;
import gameInterface.buttons.EmptyFieldButton;
import gameInterface.buttons.PlaceableItem;
import gameInterface.buttons.ShipButton;
import messagingHandler.GameAdapter;
import messagingHandler.MessageSender;
import messagingHandler.Messages.*;
import messagingHandler.Subscribers.Subscriber;
import player.Player;

import javax.swing.*;
import java.awt.*;

import static messagingHandler.MessageSender.send;

public class ShipPlacementWindow <T extends Message> extends JFrame implements ClosableWindow, Subscriber<T> {
    public static ShipPlacementWindow shipPlacementWindow;
    private final Player player;
    protected final Dimension boardSize;
    protected EmptyFieldButton[][] buttons;
    protected Dimension windowSize;
    protected JPanel panel;
    protected Game game;
    protected Field[] fieldsFromBoard;

    public ShipPlacementWindow(Game game, Player player) {
        super();
        subscribe(PlayerStartsPlacingShips.class);
        subscribe(NextShipPlaceMessage.class);
        subscribe(FinishPlacingShipForPlayer.class);
        subscribe(WrongShipPositionMessage.class);
        shipPlacementWindow = this;
        this.player = player;
        this.game = game;
        closingDefinition(this);
        boardSize = new Dimension(10,10);
        windowSize = new Dimension(boardSize.width*PlaceableItem.ITEM_SIZE+PlaceableItem.ITEM_SIZE*6, boardSize.height*PlaceableItem.ITEM_SIZE+PlaceableItem.ITEM_SIZE);
        preparePanel();
        addFields();
        setSize(windowSize);
        setLocationRelativeTo(null);
    }

    protected void showNextShip(Ship ship){
        if (ship!=null)
            addShip(ship);
        repaint();revalidate();
    }

    protected void addShip(Ship ship) {
        Point startingPoint = new Point(boardSize.width*PlaceableItem.ITEM_SIZE+PlaceableItem.ITEM_SIZE,PlaceableItem.ITEM_SIZE);
        ShipButton button = ShipButton.getShipButton(ship, startingPoint);
        int componentCount = panel.getComponentCount();
        if (componentCount==0) panel.add(button);
        else panel.add(button, 0);
    }

    private void addFields() {
        buttons = new EmptyFieldButton[boardSize.width][boardSize.height];
        for (int x=0; x<boardSize.width; x++){
            for (int y = 0; y < boardSize.height; y++) {
                buttons[x][y] = new EmptyFieldButton(new Point(x*PlaceableItem.ITEM_SIZE,y*PlaceableItem.ITEM_SIZE));
                buttons[x][y].setFocusable(false);
                panel.add(buttons[x][y]);
            }
        }
        add(panel);
    }

    private void preparePanel() {
        panel = new JPanel(null);
        panel.setOpaque(true);
        panel.setSize(windowSize);
    }

    public Point getNearestFieldLocation(Point point, Dimension size){
        int buttonX;
        int buttonY;
        for (int x = 0; x < buttons.length; x++) {
            buttonX=buttons[x][0].getBounds().x;
            if (Math.abs(buttonX-point.x)<PlaceableItem.ITEM_SIZE && isInBoardRange(size.width,x)){
                for (int y = 0; y < buttons[x].length; y++) {
                    buttonY=buttons[x][y].getBounds().y;
                    if (Math.abs(point.y-buttonY)<PlaceableItem.ITEM_SIZE && isInBoardRange(size.height,y)){
                        return buttons[x][y].getLocation();
                    }
                }
            }
        }
        return null;
    }

    private boolean isInBoardRange(int counterWidth, int fieldPosition) {
        int counterMaxPosition = counterWidth/PlaceableItem.ITEM_SIZE;
        return counterMaxPosition+fieldPosition<=boardSize.width;
    }

    public void placeShipOnBoard(Ship ship, Point... points) {
        MessageSender.send(new ShipPlaced(player, ship, points));
    }

    protected void returnLastShip() {
        ShipButton b = (ShipButton) panel.getComponent(0);
        b.returnToOriginalPosition();
        b.setEnabled(true);
    }

    @Override
    public void receiveMessage(T message) {
        if (message.getClass()==NextShipPlaceMessage.class)
            receiveMessage((NextShipPlaceMessage) message);
        else if (message.getClass()==PlayerStartsPlacingShips.class)
            receiveMessage((PlayerStartsPlacingShips) message);
        else if (message.getClass()==FinishPlacingShipForPlayer.class)
            receiveMessage((FinishPlacingShipForPlayer) message);
        else if (message.getClass()==WrongShipPositionMessage.class)
            receiveMessage((WrongShipPositionMessage) message);
    }

    public void receiveMessage(PlayerStartsPlacingShips message) {
        DisclosureDialog dialog = new DisclosureDialog();
        dialog.setModal(true);
        dialog.setVisible(true);
        setVisible(true);
    }

    public void receiveMessage(FinishPlacingShipForPlayer message){
        setVisible(false);
        send(new RemoveSubscriberMessage(this));
        dispose();
    }

    public void receiveMessage(NextShipPlaceMessage nextShipToPlaceAction) {
        showNextShip(nextShipToPlaceAction.getShip());
    }
    public void receiveMessage(WrongShipPositionMessage message){
        returnLastShip();
    }
}
