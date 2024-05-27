package Task4;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        try {
            String document1 = Files.readString(Paths.get("C:\\Worr\\TPO_Labs\\Lab4\\src\\Texts\\Book 1 - The Philosopher's Stone.txt")); // Ваш перший документ
            String word = "computer";
            ForkJoinPool pool = new ForkJoinPool();

            var task1 = new DocumentSearchWordTask(document1, word);

            var contains = pool.invoke(task1);


            System.out.println("Contains word " +word + " : " + contains);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
