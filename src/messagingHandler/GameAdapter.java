package messagingHandler;

import messagingHandler.Actions.*;
import messagingHandler.Messages.*;
import messagingHandler.Subscribers.ActionActivator;
import messagingHandler.Subscribers.Subscriber;
import messagingHandler.Subscribers.SubscribersList;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class GameAdapter {
    private static final GameAdapter mainAdapter = new GameAdapter();
    private SubscribersList subscribers;
    private BlockingQueue<Runnable> channel = new ArrayBlockingQueue<>(100);

    public static GameAdapter getInstance() {
        return mainAdapter;
    }

    private GameAdapter() {
        subscribers = new SubscribersList();
    }

    public boolean addSubscriber(Subscriber subscriber) {
        return subscribers.add(subscriber);
    }

    private synchronized void notifySubscribers(Action action) {
        for (Subscriber<?> sub : subscribers) {
            channel.add(new ActionActivator(action, sub));
        }
    }

    public GameAdapter receive(StartingMessage m) {
        new MessageWorker(channel).activate();
        ActionActivator activator = new ActionActivator(new StartingAction(), null);
        channel.offer(activator);
        return this;
    }

    public GameAdapter receive(BoardSizeDecided boardSizeDecided) {
        notifySubscribers(new NotifyWithBoardSize(boardSizeDecided));
        return this;
    }

    public GameAdapter receive(PlayerStartsPlacingShips m) {
        notifySubscribers(new StartPlacingShipsAction());
        return this;
    }

    public void removeSubscriber(Subscriber<?> subscriber) {
        subscribers.remove(subscriber);
    }

    public GameAdapter receive(ShipPlaced m) {
        notifySubscribers(new PlaceShipOnBoardAction(m));
        return this;
    }

    public GameAdapter receive(NextShipPlaceMessage nextShipPlaceMessage) {
        notifySubscribers(new NextShipToPlaceAction(nextShipPlaceMessage));
        return this;
    }
}
