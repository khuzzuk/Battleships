package messagingHandler;

import messagingHandler.Actions.Action;
import messagingHandler.Actions.NotifyWithBoardSize;
import messagingHandler.Actions.StartingAction;
import messagingHandler.Messages.BoardSizeDecided;
import messagingHandler.Messages.StartingMessage;

public class GameAdapter {
    private static GameAdapter mainAdapter = new GameAdapter();
    private SubscribersList subscribers;

    public static GameAdapter getInstance() {
        return mainAdapter;
    }

    private GameAdapter() {
        subscribers = new SubscribersList();
    }

    public boolean addSubscriber(Subscriber subscriber){
        return subscribers.add(subscriber);
    }

    private void notifySubscribers(Action action){
        if (subscribers.size()==0) action.notify(null);
        for (Subscriber sub : subscribers) {
            action.notify(sub);
        }
    }

    public GameAdapter receive(StartingMessage m){
        notifySubscribers(new StartingAction());
        return this;
    }

    public GameAdapter receive(BoardSizeDecided boardSizeDecided) {
        notifySubscribers(new NotifyWithBoardSize(boardSizeDecided));
        return this;
    }
}
