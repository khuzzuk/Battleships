package messagingHandler.Actions;

import messagingHandler.Subscriber;

public interface Action {
    void notify(Subscriber<?> sub);
}
