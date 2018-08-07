import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class EmployeeFreeTime {

    // add all intervals into a minPQ which is ordered by start of Interval
    // when Interval Ni was polled, all remaining intervals has start value >= Ni.start
    // if previous max end value is smaller than Ni.start, we found a common free time
    // which is max(prv.end), Ni.start
    // If Ni.start <= max(prv.end), there is a overlap, just update max_end

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        Queue<Interval> minPQ = new PriorityQueue<>((a,b) -> a.start-b.start);
        for(List<Interval> list : schedule){
            minPQ.addAll(list);
        }
        List<Interval> res = new ArrayList<>();
        if(minPQ.size()==1) return res;

        int maxEnd = minPQ.poll().end;
        while(!minPQ.isEmpty()){
            Interval curr = minPQ.poll(); // retrieve interval with minimum start value from remaining intervals
            if(curr.start > maxEnd){ // find a common free time
                res.add(new Interval(maxEnd, curr.start));
            }
            maxEnd = Math.max(maxEnd, curr.end); // update maxEnd of previously polled Intervals
        }

        return res;
    }

    class Interval {
       int start;
       int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
}
