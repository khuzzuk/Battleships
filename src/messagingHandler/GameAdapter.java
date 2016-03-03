package messagingHandler;

import messagingHandler.Actions.*;
import messagingHandler.Messages.*;
import messagingHandler.Subscribers.Subscriber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameAdapter {
    private static final GameAdapter mainAdapter = new GameAdapter();
    private final ExecutorService threads;
    private SubscribersList subscribers;

    public static GameAdapter getInstance() {
        return mainAdapter;
    }

    private GameAdapter() {
        subscribers = new SubscribersList();
        threads = Executors.newFixedThreadPool(10);
    }

    public boolean addSubscriber(Subscriber subscriber){
        return subscribers.add(subscriber);
    }

    private synchronized void notifySubscribers(Action action){
        if (subscribers.size()==0) action.sendToSubscriber(null);
        for (Subscriber sub:subscribers) {
            threads.submit(() -> action.sendToSubscriber(sub));
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

    public GameAdapter receive(PlayerStartsPlacingShips m){
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
