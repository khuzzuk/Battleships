import board.BoardSize;
import game.Game;

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
