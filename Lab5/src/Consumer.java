import java.util.Random;

public class Consumer extends Thread{
    private LimitedQueue queue;
    private int consumerId;

    public Consumer(LimitedQueue queue, int consumerId){
        this.queue = queue;
        this.consumerId = consumerId;
    }

    @Override
    public void run(){
        Random random = new Random();
        for (Message message = queue.pop(); message.data != -1; message = queue.pop()) {
//            System.out.format("Consumer %s, Producer %s MESSAGE RECEIVED: %s%n", consumerId,  message.user, message.data);
            try {
                Thread.sleep(10/*random.nextInt(5000)*/);
            } catch (InterruptedException e) {
            }
        }
    }
}
