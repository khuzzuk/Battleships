package messagingHandler.Subscribers;

import messagingHandler.Subscribers.Subscriber;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SubscribersList implements Iterable<Subscriber> {
    private List<Subscriber<?>> subscribers;

    public SubscribersList() {
        subscribers = new ArrayList<>();
    }
    public boolean add(Subscriber<?> subscriber){
        return subscribers.add(subscriber);
    }

    @Override
    public Iterator iterator() {
        return subscribers.iterator();
    }

    public int size() {
        return subscribers.size();
    }

    public void remove(Subscriber<?> subscriber) {
        subscribers.remove(subscriber);
    }

    public Subscriber<?> get(int i) {
        return subscribers.get(i);
    }
}
