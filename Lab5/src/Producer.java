import java.util.Random;

public class Producer extends Thread{
    private LimitedQueue queue;
    private int producerId;

    public Producer(LimitedQueue queue, int producerId) {
        this.queue = queue;
        this.producerId = producerId;
    }

    @Override
    public void run() {
        int size = 500;
        int[] importantInfo = new int[size];
        for (int i = 0; i < importantInfo.length; i++) {
            importantInfo[i] = i + 1;
        }
        Random random = new Random();
        for (int i = 0; i < importantInfo.length; i++) {
            queue.push(new Message(importantInfo[i], producerId));
            try {
                Thread.sleep(10/*random.nextInt(5000)*/);
            } catch (InterruptedException e) {
            }
        }
        queue.push(new Message(-1, producerId));
    }
}
