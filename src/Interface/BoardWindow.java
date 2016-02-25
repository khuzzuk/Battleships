package Interface;

import Interface.Listeners.ShootListener;
import Interface.buttons.FieldButton;
import Interface.buttons.PlaceableItem;
import game.*;

import javax.swing.*;
import java.awt.*;

public class BoardWindow extends JFrame {
    public static BoardWindow boardWindow;
    private final Game game;
    private JPanel panel1, panel2;
    private Player player1, player2;
    private FieldButton[][] buttons;
    private BoardSize boardSize;

    public BoardWindow(Game game, Player player1, Player player2) throws HeadlessException {
        this.player1=player1;
        this.player2=player2;
        this.game=game;
        boardSize = player1.getBoardSize();
        boardWindow = this;
        preparePanel();
        int windowSize = boardSize.size* PlaceableItem.ITEM_SIZE;
        setSize(new Dimension(windowSize*5/4-10, windowSize*5/4+10));
    }
    private void preparePanel(){
        panel1 = new JPanel(null);
        panel2 = new JPanel(null);
    }
    private void addFields(FieldsList fieldsList, JPanel panel, Player player) {
        panel.removeAll();
        buttons = new FieldButton[boardSize.size][boardSize.size];
        for (int x=0; x<buttons.length; x++){
            for (int y = 0; y < buttons[x].length; y++) {
                buttons[x][y] = new FieldButton(fieldsList.get(new Field(x,y)), x,y);
                buttons[x][y].addActionListener(new ShootListener(new Point(x,y), game, player));
                panel.add(buttons[x][y]);
            }
        }
        add(panel);
        repaint();revalidate();
    }
    public void playerOneIsShot(){
        addFields(player1.fieldsList(),panel1, player1);
    }
}
