package Interface;

import Interface.Dialogs.DisclosureDialog;
import Interface.buttons.*;
import game.*;
import game.board.fields.Field;
import game.fleet.Ship;
import game.player.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Adrian on 19.02.2016.
 */
public class MainWindow extends JFrame implements ClosableWindow {
    public static MainWindow mainWindow;
    private final Player player;
    protected final Dimension boardSize;
    protected FieldButtonEmpty[][] buttons;
    protected Dimension windowSize;
    protected JPanel panel;
    protected Game game;
    protected Field[] fieldsFromBoard;

    public MainWindow(Game game, Player player) {
        super();
        mainWindow = this;
        this.player = player;
        this.game = game;
        closingDefinition(this);
        boardSize = new Dimension(10,10);
        windowSize = new Dimension(boardSize.width*PlaceableItem.ITEM_SIZE+200, boardSize.height*PlaceableItem.ITEM_SIZE+40);
        preparePanel();
        showNextShip();
        addFields();
        setSize(windowSize);
        DisclosureDialog dialog = new DisclosureDialog();
        dialog.setModal(true);
        dialog.setVisible(true);
        setVisible(true);
    }

    protected void showNextShip(){
        Ship ship = game.nextShipToPlace(player);
        if (ship!=null)
            addShip(ship);
    }

    protected void addShip(Ship ship) {
        Point startingPoint = new Point(boardSize.width*PlaceableItem.ITEM_SIZE+PlaceableItem.ITEM_SIZE,PlaceableItem.ITEM_SIZE);
        ShipButton button = ShipButton.getShipButton(ship, startingPoint);
        int componentCount = panel.getComponentCount();
        if (componentCount==0) panel.add(button);
        else panel.add(button, 0);
    }

    private void addFields() {
        buttons = new FieldButtonEmpty[boardSize.width][boardSize.height];
        for (int x=0; x<boardSize.width; x++){
            for (int y = 0; y < boardSize.height; y++) {
                buttons[x][y] = new FieldButtonEmpty(new Point(x*20,y*20));
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
        fieldsFromBoard = new Field[points.length];
        for (int i = 0; i < points.length; i++) {
            fieldsFromBoard[i] = new Field(points[i].x, points[i].y);
        }
        if (game.placeShip(ship, player, fieldsFromBoard)){
            showNextShip();
        }
        else {
            returnLastShip();
        }
        repaint();revalidate();
    }

    protected void returnLastShip() {
        ShipButton b = (ShipButton) panel.getComponent(0);
        b.returnToOriginalPosition();
        b.setEnabled(true);
    }
}
