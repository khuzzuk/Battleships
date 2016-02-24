package Interface;

import Interface.Listeners.MovingButtonAdapter;
import Interface.buttons.*;
import game.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Adrian on 19.02.2016.
 */
public class MainWindow extends JFrame {
    public static MainWindow mainWindow;
    private Dimension boardSize;
    private FieldButtonEmpty[][] buttons;
    private Dimension windowSize;
    private JPanel panel;
    public MainWindow() {
        super();
        mainWindow = this;
        closingDefinition();
        boardSize = new Dimension(10,10);
        windowSize = new Dimension(boardSize.width*PlaceableItem.ITEM_SIZE+200, boardSize.height*PlaceableItem.ITEM_SIZE+40);
        preparePanel();
        addShip();
        addFields();
        setSize(windowSize);
    }

    private void addShip() {
        BattleshipButton button = new BattleshipButton(new Point(boardSize.width*PlaceableItem.ITEM_SIZE+PlaceableItem.ITEM_SIZE,PlaceableItem.ITEM_SIZE));
        button.addMouseListener(new MovingButtonAdapter(button));
        button.addMouseMotionListener(new MovingButtonAdapter(button));
        button.addMouseWheelListener(new MovingButtonAdapter(button));
        panel.add(button);
        CruiserButton cruiserButton = new CruiserButton(new Point(boardSize.width*PlaceableItem.ITEM_SIZE+PlaceableItem.ITEM_SIZE,PlaceableItem.ITEM_SIZE*3));
        cruiserButton.addMouseListener(new MovingButtonAdapter(cruiserButton));
        cruiserButton.addMouseMotionListener(new MovingButtonAdapter(cruiserButton));
        cruiserButton.addMouseWheelListener(new MovingButtonAdapter(cruiserButton));
        panel.add(cruiserButton);
        DestroyerButton destroyerButton = new DestroyerButton(new Point(boardSize.width*PlaceableItem.ITEM_SIZE+PlaceableItem.ITEM_SIZE,PlaceableItem.ITEM_SIZE*5));
        destroyerButton.addMouseListener(new MovingButtonAdapter(destroyerButton));
        destroyerButton.addMouseMotionListener(new MovingButtonAdapter(destroyerButton));
        destroyerButton.addMouseWheelListener(new MovingButtonAdapter(destroyerButton));
        panel.add(destroyerButton);
        SubmarineButton submarineButton = new SubmarineButton(new Point(boardSize.width*PlaceableItem.ITEM_SIZE+PlaceableItem.ITEM_SIZE,PlaceableItem.ITEM_SIZE*7));
        submarineButton.addMouseListener(new MovingButtonAdapter(submarineButton));
        submarineButton.addMouseMotionListener(new MovingButtonAdapter(submarineButton));
        submarineButton.addMouseWheelListener(new MovingButtonAdapter(submarineButton));
        panel.add(submarineButton);
    }

    private void addFields() {
        buttons = new FieldButtonEmpty[boardSize.width][boardSize.height];
        for (int x=0; x<boardSize.width; x++){
            for (int y = 0; y < boardSize.height; y++) {
                buttons[x][y] = new FieldButtonEmpty(new Point(x*20,y*20));
                panel.add(buttons[x][y]);
            }
        }
        add(panel);
    }

    private void preparePanel() {
        panel = new JPanel(null);
        panel.setSize(windowSize);
    }

    private void closingDefinition() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
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
        int counterMaxPosition = (counterWidth/2+1)/PlaceableItem.ITEM_SIZE;
        if (counterMaxPosition<=fieldPosition) return true;
        return false;
    }
}
