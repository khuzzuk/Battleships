package messagingHandler;

import messagingHandler.Messages.Message;
import messagingHandler.Subscribers.SubscribersList;

public class MessageActivator implements Runnable{
    private Message message;
    private SubscribersList subscribersList;

    public MessageActivator(Message message, SubscribersList subscribersList) {
        this.message = message;
        this.subscribersList = subscribersList;
    }

    @Override
    public void run() {
        subscribersList.send(message);
    }
}
