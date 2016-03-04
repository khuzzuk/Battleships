package messagingHandler.Actions;

import board.BoardSize;
import game.Game;
import messagingHandler.Messages.BoardSizeDecided;
import messagingHandler.Subscribers.Subscriber;

public class NotifyWithBoardSize extends GeneralAction implements Action {

    private BoardSize boardSize;

    public NotifyWithBoardSize(BoardSizeDecided message) {
        boardSize = message.getBoardSize();
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }

    @Override
    public void activateMessage() {

    }

    private void sendHelper(Game sub){

    }
}
