package messagingHandler.Messages;

import messagingHandler.GameAdapter;

public class BoardSizeDecided implements Message {
    private int boardSize;

    public BoardSizeDecided(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getBoardSize() {
        return boardSize;
    }

    @Override
    public void send() {
        GameAdapter.getInstance().receive(this);
    }
}
