import java.util.ArrayList;
import java.util.List;

// [0,1], [2,2] is not valid interval, should be written as [0, 2]

// list1: 0,2  5, 10
// list2: 1,5  10, 18
// res:   1,2  5,5  10,10
public class IntervalsAnd {
    List<Interval> intervalsAnd(List<Interval> l1, List<Interval> l2) {
        List<Interval> res = new ArrayList<>();
        int i = 0, j = 0;
        while(i < l1.size() && j < l1.size()) {
            Interval tmp = and(l1.get(i), l2.get(j));
            if(tmp != null) res.add(tmp);
            if(l1.get(i).end < l2.get(j).end) {
                i++;
            } else if(l1.get(i).end > l2.get(j).end) {
                j++;
            } else{
                i++;
                j++;
            }
        }
        return res;
    }

    Interval and(Interval i1, Interval i2){
        if(i1.end < i2.start || i2.end < i1.start) return null;
        return new Interval(Math.max(i1.start, i2.start), Math.min(i1.end, i2.end));
    }

    public static void main(String[] args) {
        Interval i1 = new Interval(1,5);
        Interval i2 = new Interval(7,8);
        IntervalsAnd sol = new IntervalsAnd();
        Interval res = sol.and(i1, i2);
        if(res != null) {
            System.out.println(res.start + " " + res.end);
        }

        List<Interval> l1 = new ArrayList<>();
        List<Interval> l2 = new ArrayList<>();
        l1.add(new Interval(0,2));
        l1.add(new Interval(5,10));
        l1.add(new Interval(16,20));
        l2.add(new Interval(1,5));
        l2.add(new Interval(10, 18));
        l2.add(new Interval(20, 23));

        for(Interval i : sol.intervalsAnd(l1, l2)) {
            System.out.print(i.start + " " + i.end + ", ");
        }
    }
}
