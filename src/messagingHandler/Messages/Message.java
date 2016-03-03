package messagingHandler.Messages;

import messagingHandler.GameAdapter;

public interface Message {
    void send(GameAdapter receiver);
}
