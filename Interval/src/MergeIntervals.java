import java.util.ArrayList;
import java.util.List;

public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        if(intervals.size() <= 1) return intervals;
        List<Interval> res = new ArrayList<>();

        // sort interval by start, if draw, by end
        // for this problem, sort by start is enough
        intervals.sort(
                (a,b) -> Integer.compare(a.start, b.start) != 0 ? Integer.compare(a.start, b.start) : Integer.compare(a.end, b.end)
        );

        int start = intervals.get(0).start, end = intervals.get(0).end;
        for(Interval interval : intervals) {
            if(interval.start <= end) { // overlap
                end = Math.max(end, interval.end);
            } else { // no overlap
                res.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }

        // add the last interval into res
        res.add(new Interval(start, end));
        return res;
    }
}

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}
