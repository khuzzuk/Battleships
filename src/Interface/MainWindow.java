package Interface;

import GameLogic.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Adrian on 19.02.2016.
 */
public class MainWindow extends JFrame {
    private Dimension boardSize;
    private FieldButtonEmpty[][] buttons;
    private Dimension windowSize;
    public MainWindow() {
        super();
        closingDefinition();
        boardSize = Board.boardDimension;
        windowSize = new Dimension(boardSize.width*20+20, boardSize.height*20+40);
        addFields();
        setSize(windowSize);
    }

    private void addFields() {
        JPanel panel = new JPanel(null);
        panel.setSize(windowSize);
        buttons = new FieldButtonEmpty[boardSize.width][boardSize.height];
        for (int x=0; x<boardSize.width; x++){
            for (int y = 0; y < boardSize.height; y++) {
                buttons[x][y] = new FieldButtonEmpty(new Point(x*20,y*20));
                panel.add(buttons[x][y]);
            }
        }
        add(panel);
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
