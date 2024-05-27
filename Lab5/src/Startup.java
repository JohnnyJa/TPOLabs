import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Startup implements Callable<Result> {

    boolean isSpectated;

    public Startup(boolean isSpectated) {
        this.isSpectated = isSpectated;
    }

    @Override
    public Result call() throws Exception {
        var producerPool = Executors.newFixedThreadPool(10);
        var consumerPool = Executors.newFixedThreadPool(4);
        var spectatorPool = Executors.newFixedThreadPool(1);
        var queue = new LimitedQueue(6);
        for (int i = 0; i < 10; i++) {
            producerPool.execute(new Producer(queue, i));
        }

        for (int i = 0; i < 4; i++) {
            consumerPool.execute(new Consumer(queue, i));
        }

        if (isSpectated) {
            spectatorPool.execute(new Spectator(queue));
        }

        producerPool.shutdown();
        consumerPool.shutdown();

        if (isSpectated) {
            spectatorPool.shutdown();
        }
        System.out.println("System is started");

        try {
            boolean okProducer = producerPool.awaitTermination(30, TimeUnit.SECONDS);
            boolean okConsumer = consumerPool.awaitTermination(30, TimeUnit.SECONDS);

            queue.isOpen = false;
            if (isSpectated) {
                boolean okSpectator = spectatorPool.awaitTermination(30, TimeUnit.SECONDS);
            }
            return queue.getTotalStats();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
