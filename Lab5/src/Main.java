import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        task2(5);

    }

    private static void task1() throws Exception {
        var task = new Startup(false);
        var results = task.call();

        System.out.println("Total message: " + results.totalMessages);
        System.out.println("Total approved: " + results.totalApproved);
        System.out.println("Total rejected: " + results.totalRejected);

        System.out.println("Total fail probability: " + results.rejectedPercentage);
        System.out.println("Average queue size: " + results.averageQueueLength);
    }

    private static void task2(int testAmount) throws Exception {
        var executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        var tasks = new ArrayList<Callable<Result>>();

        for (int i = 0; i < testAmount; i++)
            tasks.add(new Startup(false));

        List<Future<Result>> resultList = executor.invokeAll(tasks);
        executor.shutdown();

        double totalAveragesMessages = 0, totalPercentages = 0;
        for(var result : resultList) {
            var info = result.get();

            totalAveragesMessages += info.averageQueueLength;
            totalPercentages += info.rejectedPercentage;
        }


        System.out.println("Total fail probability: " +totalPercentages / resultList.size());
        System.out.println("Average queue size: " +totalAveragesMessages / resultList.size());
    }

    private static void task3() throws Exception {
        var task = new Startup(true);
        var results = task.call();


    }
}