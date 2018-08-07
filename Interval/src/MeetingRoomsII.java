import java.util.*;

public class MeetingRoomsII {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: the minimum number of conference rooms required
     */
    // v1, use TreeMap to store timeline, +1 for start, -1 for end
    public int minMeetingRooms(List<Interval> intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (Interval i : intervals) {
            map.put(i.start, map.getOrDefault(i.start, 0) + 1);
            map.put(i.end, map.getOrDefault(i.end, 0) - 1);
        }
        int maxOverlap = 0, overlap = 0;
        for (int key : map.keySet()) {
            overlap += map.get(key);
            maxOverlap = Math.max(overlap, maxOverlap);
        }
        return maxOverlap;
    }

    public int minMeetingRooms2(List<Interval> intervals) {
        int maxOverlap = 0;
        intervals.sort((a,b)->Integer.compare(a.start, b.start));
        Queue<Integer> minPQ = new PriorityQueue<>();

        for(Interval i : intervals) {
            while(!minPQ.isEmpty() && minPQ.peek() < i.start)
                minPQ.poll();

            minPQ.add(i.end);
            maxOverlap = Math.max(minPQ.size(), maxOverlap);
        }
        return maxOverlap;
    }
}