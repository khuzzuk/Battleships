package messagingHandler;

import messagingHandler.Messages.Message;

public class MessageSender {
    private MessageSender() {
    }

    public static void send(Message m) {
        m.send();
    }
}
