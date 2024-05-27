package Task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class FileProcessor {
    private final String text;

    public FileProcessor(Path filePath) {
        try {
            text = Files.readString(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<Integer, Integer> getStatisticsParallel() {
        var pool =  ForkJoinPool.commonPool();
        var task = new RecursiveTextStatistic(text);

        var result = pool.invoke(task);
        pool.shutdown();

        return result;
    }

    public HashMap<Integer, Integer> getStatisticsParallelWithTime() {
        var startTime = System.currentTimeMillis();
        var res =  getStatisticsParallel();
        var endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime));

        return res;
    }

    public HashMap<Integer, Integer> getStatisticsSequential() {
        var result = new HashMap<Integer, Integer>();
        var wordsList = List.of(text.split("\\s+"));
        for (String word : wordsList) {
            int length = word.length();
            result.put(length, result.getOrDefault(length, 0) + 1);
        }
        return result;
    }

    public HashMap<Integer, Integer> getStatisticsSequentialWithTime() {
        var startTime = System.currentTimeMillis();
        var res =  getStatisticsSequential();
        var endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime));

        return res;
    }
}
