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

    public void addSubscriber(Class<?> typeOfMessages, Subscriber subscriber) {
        subscribers.add(typeOfMessages, subscriber);
    }

    public GameAdapter offer(StartingMessage m) {
        new MessageWorker(channel).activate();
        ActionActivator activator = new ActionActivator(m);
        channel.offer(activator);
        return this;
    }
    public void offer(Message m){
        subscribers.send(m);
    }

    /*public GameAdapter offer(BoardSizeDecided boardSizeDecided) {
        notifySubscribers(boardSizeDecided);
        return this;
    }*/

    /*public GameAdapter offer(PlayerStartsPlacingShips m) {
        notifySubscribers(new StartPlacingShipsAction());
        return this;
    }*/

    public void removeSubscriber(Subscriber<?> subscriber) {
        subscribers.remove(subscriber);
    }

    /*public GameAdapter offer(ShipPlaced m) {
        notifySubscribers(new PlaceShipOnBoardAction(m));
        return this;
    }*/

}
