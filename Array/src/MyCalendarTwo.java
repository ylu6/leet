import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yeqing on 12/11/2017.
 */
public class MyCalendarTwo {

    List<int[]> events;
    List<int[]> overlaps;

    public MyCalendarTwo() {
        events = new ArrayList<>();
        overlaps = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for(int[] o : overlaps) {
            if(start < o[1] && end > o[0]) // new event overlaps with a previous overlap
                return false;
        }

        // add all new overlaps into the overlaps_list
        for(int[] e : events) {
            if(start < e[1] && end > e[0])
                overlaps.add(new int[]{Math.max(start, e[0]), Math.min(end, e[1])});
        }
        // no triple booking for this new event, add it to the events list
        events.add(new int[]{start, end});
        return true;
    }

    public static void main(String[] args) {
        MyCalendarTwo cal = new MyCalendarTwo();
        cal.book(36,41);
        cal.book(28,34);
        cal.book(40,46);
        cal.book(10,18);
        cal.book(4,11);
        cal.book(25,34);
        cal.book(36,44);
        System.out.println(cal.book(32,40));
        System.out.println(cal.book(34,39));
        cal.book(40,49);
    }
}
