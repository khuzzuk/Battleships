package game;

/**
 * Created by Adrian on 18.02.2016.
 */
public class Player {
    Board board;
    Fleet fleet;
    public Player(BoardSize boardSize) {
        board = new Board(boardSize);
        fleet = new Fleet();
    }
}
