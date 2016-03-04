package messagingHandler;

import java.util.concurrent.BlockingQueue;

public class MessageWorker implements MessageExecutor<Runnable, BlockingQueue<Runnable>> {
    private BlockingQueue<Runnable> channel;

    public MessageWorker(BlockingQueue<Runnable> channel) {
        this.channel = channel;
    }
    public void activate(){
        new Thread(new Worker()).start();
    }
    public class Worker implements Runnable {
        @Override
        public void run() {
            Runnable currentTask;
            while (true){
                currentTask = channel.poll();
                if (currentTask==null) {
                    try{
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    currentTask.run();
                }
            }
        }
    }
}
