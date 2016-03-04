package messagingHandler.Subscribers;

import messagingHandler.Actions.Action;

public class ActionActivator implements Runnable{
    private Action action;
    private Subscriber<?> sub;

    public ActionActivator(Action action, Subscriber<?> sub) {
        this.action = action;
        this.sub = sub;
    }

    @Override
    public void run() {
        action.sendToSubscriber(sub);
    }
}
