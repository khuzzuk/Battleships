package messagingHandler.Messages;

import messagingHandler.GameAdapter;
import messagingHandler.Subscribers.Subscriber;

@PriorityMessage(prt = PriorityMessage.Priority.HIGH)
public class RemoveSubscriberMessage implements Message {
    private Subscriber<?> subscriber;

    public RemoveSubscriberMessage(Subscriber<?> subscriber) {
        this.subscriber = subscriber;
    }

    public Subscriber<?> getSubscriber() {
        return subscriber;
    }

}
