package messagingHandler.Messages;

import messagingHandler.GameAdapter;

public class StartingMessage implements Message {
    public StartingMessage() {}
    @Override
    public void send(GameAdapter receiver) {
        receiver.receive(this);
    }
}
