package messagingHandler.Subscribers;

import messagingHandler.Messages.Message;

import java.util.*;

public class SubscribersList {
    private Map<Class<?>, List<Subscriber<?>>> subscribers;

    public SubscribersList() {
        subscribers = new HashMap<>();
    }
    public void add(Class<?> typeOfMessages, Subscriber<?> subscriber){
        List<Subscriber<?>> subsList = subscribers.get(typeOfMessages);
        if (subsList==null) subsList = new ArrayList<>();
        subsList.add(subscriber);
        subscribers.put(typeOfMessages, subsList);
    }

    public Iterator<Subscriber<?>> iterator(Class<?> typeOfMessage) {
        List<Subscriber<?>> list = subscribers.get(typeOfMessage);
        return list.iterator();
    }

    public int size() {
        return subscribers.size();
    }

    public void remove(Subscriber<?> subscriber) {
        subscribers.remove(subscriber);
    }

    public List<Subscriber<?>> get(Class<?> typeOfMessage) {
        return subscribers.get(typeOfMessage);
    }

    public void send(Message m) {
        List<Subscriber<?>> subsList = subscribers.get(m.getClass());
        if (subsList==null) return;
        for (Subscriber sub : subsList) {
            sendHelper(m, sub);
        }
    }
    private <T extends Message, U extends Subscriber<? super T>> void sendHelper(T message, U sub){
        sub.receiveMessage(message);
    }
}
