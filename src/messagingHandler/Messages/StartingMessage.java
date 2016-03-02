package messagingHandler.Messages;

import messagingHandler.GameAdapter;

public class StartingMessage implements Message {
    public StartingMessage() {}
    @Override
    public void send() {
        GameAdapter.getInstance().receive(new StartingMessage());
    }
}
