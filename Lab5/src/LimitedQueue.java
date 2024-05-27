import java.util.ArrayDeque;
import java.util.Queue;

public class LimitedQueue {
    private Queue<Message> queue;

    public boolean isOpen = true;
    private int maxSize;

    private int rejectCounter = 0;
    private int approveCounter = 0;

    private int totalMessages = 0;

    private int sumQueueSize = 0;

    LimitedQueue(int maxSize) {
        this.queue = new ArrayDeque<>();
        this.maxSize = maxSize;
    }

    public synchronized void push(Message item) {
        totalMessages++;
        sumQueueSize += queue.size();
        if(queue.size() >= maxSize) {
            rejectCounter++;
            return;
        }

        approveCounter++;
        queue.add(item);
        notifyAll();
    }

    public synchronized Message pop() {
        while(queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }

        return queue.poll();
    }

    public Result getTotalStats() {
        return new Result(totalMessages, approveCounter, rejectCounter, sumQueueSize);
    }

    public int getCurrentQueueLength() {
        return queue.size();
    }

    public double calculateRejectedPercentage() {
        return rejectCounter / (double)totalMessages;
    }
}
