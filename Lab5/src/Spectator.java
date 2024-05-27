public class Spectator  extends Thread {

    private LimitedQueue queue;

    public Spectator(LimitedQueue queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        while(queue.isOpen) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}

            System.out.println("Queue size: " + queue.getCurrentQueueLength()
                    + ", fail probability: " + queue.calculateRejectedPercentage());
        }
    }
}
