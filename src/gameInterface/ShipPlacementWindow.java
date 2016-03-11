package gameInterface;

import fleet.Ship;
import game.Game;
import gameInterface.Dialogs.DisclosureDialog;
import gameInterface.Listeners.WindowResized;
import gameInterface.buttons.EmptyFieldButton;
import gameInterface.buttons.VisibleItem;
import gameInterface.buttons.ShipButton;
import messagingHandler.MessageSender;
import messagingHandler.Messages.*;
import messagingHandler.Subscribers.Subscriber;
import player.Player;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static messagingHandler.MessageSender.send;

public class ShipPlacementWindow<T extends Message> extends JFrame implements TerminationWindow, Subscriber<T>, ScallableWindow {
    public static ShipPlacementWindow shipPlacementWindow;
    private final Player player;
    protected final Dimension boardSize;
    protected EmptyFieldButton[][] buttons;
    protected List<ShipButton> ships;
    protected Dimension windowSize;
    protected JPanel panel;
    protected Game game;

    public ShipPlacementWindow(Game game, Player player) {
        super();
        defineSubscription();
        closingDefinition(this);
        shipPlacementWindow = this;
        this.player = player;
        this.game = game;
        boardSize = new Dimension(player.getBoardSize().size, player.getBoardSize().size);
        ships = new ArrayList<>();
        defineWindowSize();
        preparePanel();
        addFields();
        setLocationRelativeTo(null);
        setComponentListener();
        startWindow();
    }

    private void defineSubscription() {
        /*subscribe(PlayerStartsPlacingShips.class);*/
        subscribe(NextShipPlaceMessage.class);
        subscribe(FinishPlacingShipForPlayer.class);
        subscribe(WrongShipPositionMessage.class);
    }

    private void defineWindowSize() {
        windowSize = new Dimension(boardSize.width * VisibleItem.itemSize + VisibleItem.itemSize * 6, boardSize.height * VisibleItem.itemSize + VisibleItem.itemSize);
        setSize(windowSize);
    }

    private void preparePanel() {
        panel = new JPanel(null);
        panel.setOpaque(true);
        panel.setSize(windowSize);
    }

    protected void showNextShip(Ship ship) {
        if (ship != null)
            addShip(ship);
        repaint();
        revalidate();
    }

    protected void addShip(Ship ship) {
        Point startingPoint = new Point(boardSize.width * VisibleItem.itemSize + VisibleItem.itemSize, VisibleItem.itemSize);
        ShipButton button = ShipButton.getShipButton(ship, startingPoint);
        int componentCount = panel.getComponentCount();
        if (componentCount == 0) panel.add(button);
        else panel.add(button, 0);
        ships.add(button);
    }

    private void addFields() {
        buttons = new EmptyFieldButton[boardSize.width][boardSize.height];
        for (int x = 0; x < boardSize.width; x++) {
            for (int y = 0; y < boardSize.height; y++) {
                buttons[x][y] = new EmptyFieldButton(new Point(x * VisibleItem.itemSize, y * VisibleItem.itemSize));
                buttons[x][y].setFocusable(false);
                panel.add(buttons[x][y]);
            }
        }
        add(panel);
    }

    public Point getNearestFieldLocation(Point point, Dimension size) {
        int buttonX;
        int buttonY;
        for (int x = 0; x < buttons.length; x++) {
            buttonX = buttons[x][0].getBounds().x;
            if (Math.abs(buttonX - point.x) < VisibleItem.itemSize && isInBoardRange(size.width, x)) {
                for (int y = 0; y < buttons[x].length; y++) {
                    buttonY = buttons[x][y].getBounds().y;
                    if (Math.abs(point.y - buttonY) < VisibleItem.itemSize && isInBoardRange(size.height, y)) {
                        return buttons[x][y].getLocation();
                    }
                }
            }
        }
        return null;
    }

    private boolean isInBoardRange(int counterWidth, int fieldPosition) {
        int counterMaxPosition = counterWidth / VisibleItem.itemSize;
        return counterMaxPosition + fieldPosition <= boardSize.width;
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
        if (message.getClass() == NextShipPlaceMessage.class)
            receiveMessage((NextShipPlaceMessage) message);
        else if (message.getClass() == PlayerStartsPlacingShips.class)
            startWindow();
        else if (message.getClass() == FinishPlacingShipForPlayer.class)
            receiveMessage((FinishPlacingShipForPlayer) message);
        else if (message.getClass() == WrongShipPositionMessage.class)
            receiveMessage((WrongShipPositionMessage) message);
    }

    public void startWindow() {
        DisclosureDialog dialog = new DisclosureDialog();
        dialog.setModal(true);
        dialog.setVisible(true);
        setVisible(true);
        send(new ShipPlacementWindowReady());
    }

    public void receiveMessage(FinishPlacingShipForPlayer message) {
        setVisible(false);
        send(new RemoveSubscriberMessage(this));
        dispose();
    }

    public void receiveMessage(NextShipPlaceMessage nextShipToPlaceAction) {
        showNextShip(nextShipToPlaceAction.getShip());
        remake();
    }

    public void receiveMessage(WrongShipPositionMessage message) {
        returnLastShip();
    }

    public void remake() {
        //defineWindowSize();
        for (int x = 0; x < boardSize.width; x++) {
            for (int y = 0; y < boardSize.height; y++) {
                buttons[x][y].relocate(new Point(x * VisibleItem.itemSize, y * VisibleItem.itemSize));
            }
        }
        for (ShipButton b : ships) {
            b.resize(boardSize.width);
        }
        remove(panel);
        add(panel, getComponentCount() - 1);
        repaint();
        revalidate();
    }

    private void setComponentListener() {
        addComponentListener(new WindowResized(boardSize.width, 7));
    }
}
