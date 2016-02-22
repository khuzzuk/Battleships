package Interface;

import Interface.buttons.BattleshipButton;
import Interface.buttons.FieldButtonEmpty;
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
        windowSize = new Dimension(boardSize.width*20+200, boardSize.height*20+40);
        preparePanel();
        addShip();
        addFields();
        setSize(windowSize);
    }

    private void addShip() {
        BattleshipButton button = new BattleshipButton(new Point(220,10));

        button.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button.relocate(e);
            }
        });
        button.addMouseMotionListener(new MouseInputAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                button.relocate(e);
            }
        });

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
}
