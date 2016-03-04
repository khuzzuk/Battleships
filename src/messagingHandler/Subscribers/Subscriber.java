package messagingHandler.Subscribers;

import messagingHandler.Actions.Action;
import messagingHandler.GameAdapter;
import messagingHandler.Messages.Message;

public interface Subscriber<T extends Message> {
    void receiveMessage(T message);
    default void subscribe(Class<?> typeOfMessages){
        GameAdapter.getInstance().addSubscriber(typeOfMessages, this);
    }
    default void unSubscribe(){
        GameAdapter.getInstance().removeSubscriber(this);
    }
}
