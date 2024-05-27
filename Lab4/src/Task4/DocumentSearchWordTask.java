package Task4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

public class DocumentSearchWordTask extends RecursiveTask<Boolean> {
    private final String document;

    private final String word;
    private final int start;
    private final int end;
    private static final int THRESHOLD = 10000;

    public DocumentSearchWordTask(String document, String word, int start, int end) {
        this.document = document;
        this.word = word;
        this.start = start;
        this.end = end;
    }
    public DocumentSearchWordTask(String document, String word) {
        this.document = document;
        this.word = word;
        this.start = 0;
        this.end = document.length();
    }

    @Override
    protected Boolean compute() {
        if (end - start <= THRESHOLD) {
            return searchDirectly();
        } else {
            int mid = start + (end - start) / 2;

            while (mid < end && document.charAt(mid) != ' ') {
                mid++;
            }

            DocumentSearchWordTask task1 = new DocumentSearchWordTask(document, word, start, mid);
            DocumentSearchWordTask task2 = new DocumentSearchWordTask(document, word, mid, end);
            invokeAll(task1, task2);
            var result = task1.join();
            result = result || task2.join();
            return result;
        }
    }

    private Boolean searchDirectly() {
        var words = Arrays.asList( this.document.split("[^a-zA-Z0-9-]"));

        return words.contains(word);
    }
}