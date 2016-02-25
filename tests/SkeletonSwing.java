import Interface.MainWindow;
import game.BoardSize;
import game.Game;

/**
 * Created by adrabik on 25.02.16.
 */
public class SkeletonSwing {
    public static void main(String[] args) {
        Game game = new Game(new BoardSize(10));
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow mainWindow = new MainWindow(game);
            }
        });
    }
}
