package task3;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        int weeksAmount = 4;

        ArrayList<Group> groups = new ArrayList<>(Arrays.asList(
                new Group("ІP-11", 2, weeksAmount),
                new Group("ІP-12", 2, weeksAmount),
                new Group("ІP-13", 2, weeksAmount)
        ));
        Journal journal = new Journal(groups);

        ArrayList<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < weeksAmount; i++) {
            final int finalWeek = i;
            final Thread t = new Thread(() -> {
                for (Group group : groups) {
                    for (Integer student : group.getMarks().keySet()) {
                        int mark = (int) (Math.random() * 100) + 1;
                        group.addMark(student, mark, finalWeek);
                        System.out.println("Thread: " + Thread.currentThread().getName() + " Added mark " + mark +
                                " to student " + student + " of group " + group.getGroupCode() +
                                " for week " + finalWeek);
                    }
                }
            });
            t.start();
            threads.add(t);
        }

        for (Thread thread : threads) {
            thread.join();
        }
        journal.printMarks();
    }
}
