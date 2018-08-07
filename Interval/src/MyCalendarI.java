import java.util.TreeMap;

/*
    July 5 2018
 */
public class MyCalendarI {
    TreeMap<Integer, Integer> tm; // stores {start : end}

    public MyCalendarI() {
        tm = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Integer lo = tm.floorKey(start);
        Integer hi = tm.ceilingKey(start);

        if(lo != null && tm.get(lo) > start) return false; // overlap with previous time slot
        if(hi != null && end > hi) return false; // overlap with next time slot

        tm.put(start, end); // no overlap, add the booking into treemap
        return true;
    }

    public static void main(String[] args) {
        MyCalendarI sol = new MyCalendarI();
        System.out.println(sol.book(10,11));
        System.out.println(sol.book(28,34));
        System.out.println(sol.book(40,41));
        System.out.println(sol.book(36,39));
    }
}
