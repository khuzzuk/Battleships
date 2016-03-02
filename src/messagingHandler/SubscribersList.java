package messagingHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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
}
