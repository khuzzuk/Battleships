package messagingHandler.Actions;

import board.BoardSize;
import game.Game;
import messagingHandler.GameAdapter;
import messagingHandler.Messages.BoardSizeDecided;
import messagingHandler.Subscriber;

public class NotifyWithBoardSize implements Action{

    private BoardSize boardSize;

    public NotifyWithBoardSize(BoardSizeDecided message) {
        boardSize = new BoardSize(message.getBoardSize());
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }

    @Override
    public void notify(Subscriber<?> sub) {
        sub.notifySubscriber(this);
    }
}
