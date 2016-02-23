import game.BoardSize;
import game.Game;

/**
 * Created by adrabik on 23.02.16.
 */
public class Skeleton {
    public static void main(String[] args) {
        Game game = new Game(new BoardSize(10));
        game.start();
    }
}
