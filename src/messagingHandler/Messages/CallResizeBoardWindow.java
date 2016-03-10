package messagingHandler.Messages;

import gameInterface.BoardWindow;

public class CallResizeBoardWindow implements Message {
    private BoardWindow window;

    public CallResizeBoardWindow(BoardWindow window) {
        this.window = window;
    }

    public BoardWindow getWindow() {
        return window;
    }
}
