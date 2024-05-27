package Task1;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class RecursiveTextStatistic extends RecursiveTask<HashMap<Integer, Integer>>
{
    private final String  text;
    private final int start;
    private final int end;

    public RecursiveTextStatistic(String text)
    {
        this.text = text;
        this.start = 0;
        this.end = text.length();
    }

    public RecursiveTextStatistic(String text, int start, int end)
    {
        this.text = text;
        this.start = start;
        this.end = end;
    }

    @Override
    protected HashMap<Integer, Integer> compute()
    {
        if (end - start  < 200_000)
        {
            return countWordsLength();
        }

        var middle = (end + start) / 2;
        while (middle < end && text.charAt(middle) != ' ')
        {
            middle++;
        }

        var leftTask = new RecursiveTextStatistic(text, start, middle);
        leftTask.fork();

        var rightTask = new RecursiveTextStatistic(text, middle, end);

        var result = rightTask.compute();
        leftTask.join().forEach((lengthKey, count) ->
                result.merge(lengthKey, count, Integer::sum)
        );

        return result;
    }

    private HashMap<Integer, Integer> countWordsLength() {
        var table = new HashMap<Integer, Integer>();
        var wordsList = List.of(text.substring(start, end).split("[^a-zA-Z0-9-]"));
        for (String s : wordsList) {
            var length = s.length();
            table.put(length, table.getOrDefault(length, 0) + 1);
        }
        return table;
    }
}
