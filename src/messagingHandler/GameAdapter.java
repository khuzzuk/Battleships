package messagingHandler;

import messagingHandler.Messages.Message;
import messagingHandler.Messages.PriorityMessage;
import messagingHandler.Messages.StartingMessage;
import messagingHandler.Subscribers.Subscriber;
import messagingHandler.Subscribers.SubscribersList;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class GameAdapter {
    private static final GameAdapter mainAdapter = new GameAdapter();
    private SubscribersList subscribers = new SubscribersList();
    private BlockingDeque<Runnable> channel = new LinkedBlockingDeque<>(100);

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

    public void offer(Message m) {
        if (m.getClass().isAnnotationPresent(PriorityMessage.class)) {
            PriorityMessage.Priority p = m.getClass().getAnnotation(PriorityMessage.class).prt();
            if (p == PriorityMessage.Priority.VERY_HIGH) subscribers.send(m);
            if (p == PriorityMessage.Priority.HIGH) channel.offerFirst(new MessageActivator(m, subscribers));
            else channel.offer(new MessageActivator(m, subscribers));
        }
        else if (m instanceof StartingMessage)
            initialize((StartingMessage) m);
        else channel.offer(new MessageActivator(m, subscribers));
    }

    public void removeSubscriber(Subscriber<?> subscriber) {
        subscribers.remove(subscriber);
    }
}
