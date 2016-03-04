package messagingHandler;

import java.util.Queue;

public interface MessageExecutor<T extends Runnable, U extends Queue<T>> {
    default void execute(T task, U channel){
        channel.add(task);
    }
}
