package Task3;

import Task3.DocumentSearchTask;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        try {
            String document1 = Files.readString(Paths.get("C:\\Worr\\TPO_Labs\\Lab4\\src\\Texts\\Book 1 - The Philosopher's Stone.txt")); // Ваш перший документ
            String document2 = Files.readString(Paths.get("C:\\Worr\\TPO_Labs\\Lab4\\src\\Texts\\Book 7 - The Deathly Hallows.txt")); // Ваш перший документ

            ForkJoinPool pool = new ForkJoinPool();

            Task3.DocumentSearchTask task1 = new Task3.DocumentSearchTask(document1, 0, document1.length());
            Task3.DocumentSearchTask task2 = new Task3.DocumentSearchTask(document2, 0, document1.length());

            Set<String> words1 = pool.invoke(task1);
            Set<String> words2 = pool.invoke(task2);

            words1.retainAll(words2);

            System.out.println("Common words: " + words1);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
