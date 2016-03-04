package messagingHandler.Subscribers;

import messagingHandler.Actions.Action;

public class ActionActivator implements Runnable{
    private Action action;

    public ActionActivator(Action action) {
        this.action = action;
    }

    @Override
    public void run() {
        action.activateMessage();
    }
}
