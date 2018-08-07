import java.util.TreeMap;

/**
 * Created by yeqing on 11/27/2017.
 * Your class will have the method, book(int start, int end). Formally, this represents a booking on the half open
 * interval [start, end), the range of real numbers x such that start <= x < end.
 */
public class MyCalendarI {
    TreeMap<Integer, Integer> map;

    public MyCalendarI() {
        map = new TreeMap<>(); // entries of the map: {start: end}
    }
/*
    public boolean book(int start, int end) {
        if (map.isEmpty()) {
            map.put(start, end);
            return true;
        }
        Integer highKey = map.ceilingKey(end);
        Integer lowKey = highKey==null ? map.lastKey() : map.lowerKey(highKey);
        if(lowKey!=null && map.get(lowKey) > start) return false;
        map.put(start, end);
        return true;
    }
*/
    // more elegant way
    public boolean book(int start, int end) {
        Integer highKey = map.ceilingKey(start);
        if (highKey != null && end > highKey) return false;

        Integer lowKey = map.floorKey(start);
        if(lowKey!=null && map.get(lowKey)>start) return false;
        map.put(start, end);
        return true;
    }

    public static void main(String[] args) {
        MyCalendarI sol = new MyCalendarI();
        System.out.println(sol.book(10, 11)); // returns true
        System.out.println(sol.book(28, 34)); // returns false
        System.out.println(sol.book(40, 41)); // returns true
        System.out.println(sol.book(34,39));
    }
}
