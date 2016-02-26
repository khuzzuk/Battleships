package Interface;


import Interface.Dialogs.StartingDialog;
import game.board.Board;
import game.board.BoardSize;
import game.Game;

/**
 * Created by Adrian on 19.02.2016.
 */
public class Main {
    public static void main2(String[] args) {
        StartingDialog dialog = new StartingDialog();
        dialog.setVisible(true);
    }
    public static void main(String[] args){
        new Board(new BoardSize(10));
        MainWindow frame = new ShipPlacementWindowPlayerOne(new Game(new BoardSize(10)));
        frame.setVisible(true);
    }
}
