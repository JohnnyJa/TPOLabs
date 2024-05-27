package Task1;

import java.nio.file.Paths;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        var filePath = Paths.get("C:\\Worr\\TPO_Labs\\Lab4\\src\\Texts\\The Complete Works of William Shakespeare by William Shakespeare.txt");
        var processor = new FileProcessor(filePath);

        var statisticsSequential = processor.getStatisticsSequentialWithTime();
        printStatistics(statisticsSequential);

        var statisticsParallel = processor.getStatisticsParallelWithTime();
        printStatistics(statisticsParallel);

    }

    private static void printStatistics(Map<Integer, Integer> statistics) {
        var wordsCount = 0;
        var charCount = 0.0;

        for (var length : statistics.keySet()) {
            var count = statistics.get(length);
            wordsCount += count;
            charCount += length * count;
        }

        var avgWordLength = charCount / wordsCount;
        System.out.println("Average word length: " + avgWordLength);

        var dispersion = 0.0;

        for (var length : statistics.keySet()) {
            var count = statistics.get(length);
            dispersion += count * Math.pow(length - avgWordLength, 2);
        }

        dispersion /= wordsCount;

        System.out.println("Dispersion: " + dispersion);
        System.out.println("Standard deviation: " + Math.sqrt(dispersion));
    }
}