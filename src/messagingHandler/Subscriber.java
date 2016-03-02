package messagingHandler;

import messagingHandler.Actions.Action;

public interface Subscriber<T> {
    <T extends Action> void notifySubscriber(T action);
    default void subscribe(){
        GameAdapter.getInstance().addSubscriber(this);
    }
}
