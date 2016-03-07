package messagingHandler;

import messagingHandler.Messages.FinishPlacingShipForPlayer;
import messagingHandler.Messages.HighPriorityMessage;
import messagingHandler.Messages.Message;
import messagingHandler.Messages.StartingMessage;
import messagingHandler.Subscribers.Subscriber;
import messagingHandler.Subscribers.SubscribersList;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class GameAdapter {
    private static final GameAdapter mainAdapter = new GameAdapter();
    private SubscribersList subscribers = new SubscribersList();
    private BlockingQueue<Runnable> channel = new ArrayBlockingQueue<>(100);

    public static GameAdapter getInstance() {
        return mainAdapter;
    }

    private GameAdapter() {
    }

    public void addSubscriber(Class<?> typeOfMessages, Subscriber subscriber) {
        subscribers.add(typeOfMessages, subscriber);
    }

    public GameAdapter initialize(StartingMessage m) {
        new MessageWorker(channel).activate();
        channel.offer(new MessageActivator(m, subscribers));
        return this;
    }
    public void offer(Message m){
        if (m instanceof StartingMessage)
            initialize((StartingMessage) m);
        else if (m instanceof HighPriorityMessage)
            subscribers.send(m);
        else channel.offer(new MessageActivator(m, subscribers));
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
