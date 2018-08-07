import java.util.TreeMap;

/**
 * Created by yeqing on 12/11/2017.
 */
public class MyCalendarThree {
    // store all segments on a time line
    // number of overlaps on at certain time stamp: sum_of_#_of_starts - sum_of_#_of_ends
    // use a TreeMap to store {time, count}, for start, store as position count, for end, store as negtive count
    TreeMap<Integer, Integer> counts;
    public MyCalendarThree() {
        counts = new TreeMap<>();
    }

    public int book(int start, int end) {
        int maxCount = 0, runningToal=0;
        counts.put(start, counts.getOrDefault(start, 0)+1);
        counts.put(end, counts.getOrDefault(end, 0)-1);

        for (int v : counts.values()) {
            maxCount = Math.max(maxCount, runningToal+v);
        }
        return maxCount;
    }
}
