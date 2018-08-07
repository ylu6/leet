import java.util.Arrays;

public class NonoverlappingIntervals {
    // greedy, if several interval overlaps, keep the one ends first
    public int eraseOverlapIntervals(Interval[] intervals) {
        if(intervals == null || intervals.length <= 1) return 0;

        Arrays.sort(intervals, (a,b)->Integer.compare(a.end, b.end));
        int count = 0, tail = intervals[0].end;
        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i].start < tail) count++;
            else tail = intervals[i].end;
        }
        return count;
    }
}
