package Task3;

import java.util.*;
import java.util.concurrent.*;

public class DocumentSearchTask extends RecursiveTask<Set<String>> {
    private final String document;
    private final int start;
    private final int end;
    private static final int THRESHOLD = 10000;

    public DocumentSearchTask(String document, int start, int end) {
        this.document = document;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Set<String> compute() {
        if (end - start <= THRESHOLD) {
            return searchDirectly();
        } else {
            int mid = start + (end - start) / 2;

            while (mid < end && document.charAt(mid) != ' ') {
                mid++;
            }

            DocumentSearchTask task1 = new DocumentSearchTask(document, start, mid);
            DocumentSearchTask task2 = new DocumentSearchTask(document, mid, end);
            invokeAll(task1, task2);
            Set<String> result = new HashSet<>(task1.join());
            result.retainAll(task2.join());
            return result;
        }
    }

    private Set<String> searchDirectly() {
        String[] words = this.document.split("[^a-zA-Z0-9-]");

        return new HashSet<>(Arrays.asList(words));
    }
}