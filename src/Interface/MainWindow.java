package Interface;

import Interface.Listeners.MovingButtonAdapter;
import Interface.buttons.BattleshipButton;
import Interface.buttons.FieldButtonEmpty;
import Interface.buttons.PlaceableItem;
import Interface.buttons.ShipButton;
import gameLogic.Board;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
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
        boardSize = Board.boardDimension;
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

    public Point getNearestFieldLocation(Point point){
        int buttonX;
        int buttonY;
        for (int x = 0; x < buttons.length; x++) {
            buttonX=buttons[x][0].getBounds().x;
            if (Math.abs(point.x-buttonX)<PlaceableItem.ITEM_SIZE){
                for (int y = 0; y < buttons[x].length; y++) {
                    buttonY=buttons[x][0].getBounds().y;
                    if (Math.abs(point.y-buttons[x][y].getY())<PlaceableItem.ITEM_SIZE){
                        return buttons[x][y].getLocation();
                    }
                }
            }
        }
        return null;
    }
}
