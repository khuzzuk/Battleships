package messagingHandler.Subscribers;

import messagingHandler.Actions.Action;
import messagingHandler.GameAdapter;

public interface Subscriber<T extends Action> {
    void notifySubscriber(T action);
    default void subscribe(){
        GameAdapter.getInstance().addSubscriber(this);
    }
    default void unSubscribe(){
        GameAdapter.getInstance().removeSubscriber(this);
    }
}
