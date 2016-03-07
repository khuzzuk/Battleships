package messagingHandler.Messages;

import board.BoardSize;
import messagingHandler.GameAdapter;

public class BoardSizeDecided implements Message {
    private int boardSize;

    public BoardSizeDecided(int boardSize) {
        this.boardSize = boardSize;
    }

    public BoardSize getBoardSize() {
        return new BoardSize(boardSize);
    }

}
