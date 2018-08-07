import java.util.List;

public class MeetingRooms {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: if a person could attend all meetings
     */
    public boolean canAttendMeetings(List<Interval> intervals) {
        intervals.sort((a,b)->Integer.compare(a.start, b.start));

        if(intervals == null || intervals.size() < 2) return true;
        Interval prv = null;

        for(Interval i : intervals) {
            if(prv != null && i.start < prv.end) return false;
            prv = i;
        }
        return true;
    }
}
