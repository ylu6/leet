import java.util.*;

/**
 * Created by yeqing on 12/14/2017.
 */
public class InsertInterval {
    // v1, create a new list, use iterator instead of get(i)
    // using get(i) will lead to neater coding, but in case of linkedlist, get(i) will take O(N) time
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals==null || intervals.isEmpty()) return Arrays.asList(newInterval);
        List<Interval> result = new LinkedList<>();
        Iterator<Interval> it = intervals.iterator();
        Interval cur = it.next();
        while (cur.end < newInterval.start) { // find intervals before newInterval and doesn't overlap with newInterval
            result.add(cur);
            if(it.hasNext()) cur = it.next();
            else break;
        }
        while (cur.end >= newInterval.start && cur.start <= newInterval.end) { // overlap
            newInterval.start = Math.min(cur.start, newInterval.start);
            newInterval.end = Math.max(cur.end, newInterval.end);
            if(it.hasNext()) cur = it.next();
            else break;
        }
        result.add(newInterval);
        while (cur.start > newInterval.end) { // intervals after newInterval and doesn't overlap with newInterval
            result.add(cur);
            if(it.hasNext()) cur = it.next();
            else break;
        }
        return result;
    }
    // v2, use original interval list
    public List<Interval> insert2(List<Interval> intervals, Interval newInterval) {
        if (intervals==null || intervals.isEmpty()) return Arrays.asList(newInterval);
        int idx = Collections.binarySearch(intervals, newInterval, (a,b)->a.start-b.start);
        if (idx < 0) idx = -1*(idx+1);
        intervals.add(idx, newInterval);
        Iterator<Interval> it = intervals.iterator();
        Interval cur = it.next(), prv = cur;
        int tail = cur.end;
        while(it.hasNext()) {
            cur = it.next();
            if (cur.start > tail) {
                prv = cur;
                continue;
            }
            tail = Math.max(tail, cur.end);
            while(it.hasNext()) {
                cur = it.next();
                if(cur.start > tail) break;
                tail = Math.max(tail, cur.end);
                it.remove();
            }
            prv.end = tail;
            break;
        }
        return intervals;
    }


    class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
}
