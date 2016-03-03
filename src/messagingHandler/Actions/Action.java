package messagingHandler.Actions;

import messagingHandler.Subscribers.Subscriber;

public interface Action {
    void sendToSubscriber(Subscriber<?> sub);
}
