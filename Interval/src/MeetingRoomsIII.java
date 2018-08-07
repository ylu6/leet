import java.util.*;

public class MeetingRoomsIII {
    // return list of intervals which have the largest overlaps
    public List<Interval> busyIntervals(List<Interval> intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (Interval i : intervals) {
            map.put(i.start, map.getOrDefault(i.start, 0) + 1);
            map.put(i.end, map.getOrDefault(i.end, 0) - 1);
        }
        int maxOverlap = 0, overlap = 0;
        Iterator<Integer> it = map.keySet().iterator();
        int start = it.next();
        int max = map.get(start), prvOverlaps = max;
        List<Interval> res = new ArrayList<>();
        while(it.hasNext()) {
            int end = it.next();
            int curOverlaps = prvOverlaps + map.get(end);
//            System.out.println("end: " + end+ " prvOverlaps: " + prvOverlaps + " curOverlaps: " + curOverlaps);
            if(prvOverlaps > max) {
                max = prvOverlaps;
                res = new ArrayList<>();
                res.add(new Interval(start, end));
            } else if (prvOverlaps == max) {
                res.add(new Interval(start, end));
            }
            prvOverlaps = curOverlaps;
            start = end;
        }
        // after loop, prvOverlaps points to the last timestamp
        // the last timestamp always has 0 overlap, therefore no need to check
        return res;
    }

    public static void main(String[] args) {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(0,30));
        list.add(new Interval(0,15));
        list.add(new Interval(16,18));
        list.add(new Interval(15,20));
        MeetingRoomsIII sol = new MeetingRoomsIII();
        for(Interval i : sol.busyIntervals(list)) {
            System.out.println(i.start + " " + i.end);
        }
    }
}
