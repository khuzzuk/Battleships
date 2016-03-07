package messagingHandler.Subscribers;

import messagingHandler.Actions.Action;
import messagingHandler.Messages.Message;
import messagingHandler.Messages.RemoveSubscriberMessage;

import java.util.*;

public class SubscribersList {
    private Map<Class<?>, List<Subscriber<?>>> subscribers;

    public SubscribersList() {
        subscribers = new HashMap<>();
    }
    public void add(Class<?> typeOfMessages, Subscriber<?> subscriber){
        synchronized (this){
            List<Subscriber<?>> subsList = subscribers.get(typeOfMessages);
            if (subsList==null) subsList = new ArrayList<>();
            subsList.add(subscriber);
            subscribers.put(typeOfMessages, subsList);
        }
    }

    public Iterator<Subscriber<?>> iterator(Class<?> typeOfMessage) {
        List<Subscriber<?>> list = subscribers.get(typeOfMessage);
        return list.iterator();
    }

    public int size() {
        return subscribers.size();
    }

    public void remove(Subscriber<?> subscriber) {
        synchronized (this){
            List<Subscriber<?>> subList;
            for (Class<?> key : subscribers.keySet()) {
                subList = subscribers.get(key);
                subList.remove(subscriber);
            }
        }
    }

    public List<Subscriber<?>> get(Class<?> typeOfMessage) {
        return subscribers.get(typeOfMessage);
    }

    public void send(Message m) {
        synchronized (this){
            if (m.getClass()==RemoveSubscriberMessage.class){
                receiveMessage((RemoveSubscriberMessage) m);
                return;
            }
            if (m instanceof Action) activateMessage((Action) m);
            List<Subscriber<?>> subsList = subscribers.get(m.getClass());
            if (subsList==null) return;
            for (Subscriber sub : subsList) {
                sendHelper(m, sub);
            }
        }
    }

    private void activateMessage(Action m) {
        Thread t = new Thread(()->m.activateMessage());
        t.start();
    }

    private <T extends Message, U extends Subscriber<? super T>> void sendHelper(T message, U sub){
        sub.receiveMessage(message);
    }

    public void receiveMessage(RemoveSubscriberMessage message) {
        remove(message.getSubscriber());
    }
}
