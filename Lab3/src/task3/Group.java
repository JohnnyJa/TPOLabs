package task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Group {
    private String groupCode;

    public String getGroupCode() {
        return groupCode;
    }

    private int weeksAmount;
    public HashMap<Integer, ArrayList<Integer>> marks = new HashMap<>();

    public HashMap<Integer, ArrayList<Integer>> getMarks() {
        return marks;
    }



    public Group(String groupCode, int sizeOfGroup, int weeksAmount){
        this.groupCode = groupCode;
        this.weeksAmount = weeksAmount;

        for (int i = 0; i < sizeOfGroup; i++) {
            marks.put(i + 1, new ArrayList<>(Collections.nCopies(weeksAmount, 0)));
        }
    }

    public void addMark(Integer num, Integer mark, int week) {
        synchronized (marks.get(num)) {
            ArrayList<Integer> studentMarks = marks.get(num);
            if (week >= 0 && week < weeksAmount) {
                studentMarks.set(week, mark);
            } else {
                throw new IllegalArgumentException("Invalid week: " + week);
            }
        }
    }

}
