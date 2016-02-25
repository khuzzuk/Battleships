import Interface.MainWindow;
import game.BoardSize;
import game.Game;

/**
 * Created by adrabik on 25.02.16.
 */
public class SkeletonSwing {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game game = new Game(new BoardSize(10));
            }
        });
    }
}
