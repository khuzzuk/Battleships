package gameInterface;

import board.BoardSize;
import board.fields.Field;
import board.fields.FieldsList;
import game.Game;
import gameInterface.Listeners.ShootListener;
import gameInterface.buttons.FieldButton;
import gameInterface.buttons.VisibleItem;
import player.Player;

import javax.swing.*;
import java.awt.*;

public class BoardWindow extends JFrame implements TerminationWindow {
    public static BoardWindow boardWindow;
    private final Game game;
    private JPanel panel1, panel2;
    private Player player1, player2;
    private FieldButton[][] buttons;
    private BoardSize boardSize;

    public BoardWindow(Game game, Player player1, Player player2) throws HeadlessException {
        closingDefinition(this);
        this.player1=player1;
        this.player2=player2;
        this.game=game;
        boardSize = player1.getBoardSize();
        boardWindow = this;
        preparePanel();
        int windowSize = boardSize.size* VisibleItem.itemSize + VisibleItem.itemSize;
        setSize(new Dimension(windowSize, windowSize+ VisibleItem.itemSize /2));
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
                if (buttons[x][y].isFieldUsable())
                    buttons[x][y].addActionListener(new ShootListener(new Point(x,y), game, player));
                panel.add(buttons[x][y]);
            }
        }
        add(panel);
        repaint();revalidate();
    }
    public void playerOneIsShot(){
        setTitle("Gracz 2 strzela");
        remove(panel2);
        addFields(player1.fieldsList(),panel1, player1);
    }

    public void playerTwoIsShot() {
        setTitle("Gracz 1 strzela");
        remove(panel1);
        addFields(player2.fieldsList(),panel2, player2);
    }
}
