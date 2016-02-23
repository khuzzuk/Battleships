package game;

/**
 * Created by adrabik on 23.02.16.
 */
public class Game {

    BoardSize boardSize;
    Player playerOne;
    Player playerTwo;

    public Game(BoardSize boardSize) {
        this.boardSize = boardSize;
    }

    public Game start() {
        playerOne = new Player(boardSize);
        playerTwo = new Player(boardSize);
        Logic logic = new Logic();
        return this;
    }

}
