import java.util.ArrayList;
import java.util.List;

// list1: 0,2  5,10
// list2: 1,4  10, 18
// res:   0,18
public class IntervalsUnion {
    public List<Interval> unionIntervals(List<Interval> l1, List<Interval> l2){
        List<Interval> res = new ArrayList<>();
        int i = 0, j = 0;
        while(i < l1.size() || j < l2.size()) {
            int n = res.size();
            if(j == l2.size() || (i != l1.size() && l1.get(i).start <= l2.get(j).start)) {
                if(n==0 || l1.get(i).start > res.get(n-1).end+1) {
                    res.add(l1.get(i));
                } else {
                    res.get(n-1).end = Math.max(l1.get(i).end, res.get(n-1).end);
                }
                i++;
            }
            else {
                if(n == 0 || l2.get(j).start > res.get(n-1).end+1) {
                    res.add(l2.get(j));
                } else {
                    res.get(n-1).end = Math.max(l2.get(j).end, res.get(n-1).end);
                }
                j++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        IntervalsUnion sol = new IntervalsUnion();
        List<Interval> l1 = new ArrayList<>();
        List<Interval> l2 = new ArrayList<>();
        l1.add(new Interval(0,2));
        l1.add(new Interval(5,10));
        l1.add(new Interval(12,14));
//        l2.add(new Interval(1,4));
//        l2.add(new Interval(12, 13));
        l2.add(new Interval(3, 11));

        for(Interval i : sol.unionIntervals(l1, l2)) {
            System.out.print(i.start + " " + i.end + ", ");
        }
    }
}
