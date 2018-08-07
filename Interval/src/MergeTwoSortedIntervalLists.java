import java.util.ArrayList;
import java.util.List;

public class MergeTwoSortedIntervalLists {
    /**
     * @param list1: one of the given list
     * @param list2: another list
     * @return: the new sorted list of interval
     */
    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        list1.sort((a,b)->Integer.compare(a.start,b.start));
        list2.sort((a,b)->Integer.compare(a.start,b.start));
        List<Interval> res = new ArrayList<>();

        int i = 0, j = 0;
        while(i < list1.size() || j < list2.size()) {
            int n = res.size();
            if(j == list2.size() || i < list1.size() && list1.get(i).start < list2.get(j).start) {
                if(n != 0 && res.get(n-1).end >= list1.get(i).start) {
                    res.get(n-1).end = Math.max(res.get(n-1).end, list1.get(i).end);
                } else {
                    res.add(list1.get(i));
                }
                i++;
            } else {
                if(n != 0 && res.get(n-1).end >= list2.get(j).start) {
                    res.get(n-1).end = Math.max(res.get(n-1).end, list2.get(j).end);
                } else {
                    res.add(list2.get(j));
                }
                j++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Interval> l1 = new ArrayList<>();
        List<Interval> l2 = new ArrayList<>();
        l1.add(new Interval(0,2));
        l1.add(new Interval(5,10));
        l1.add(new Interval(16,20));
        l2.add(new Interval(3,4));
        l2.add(new Interval(12, 18));
        l2.add(new Interval(20, 23));
        MergeTwoSortedIntervalLists sol = new MergeTwoSortedIntervalLists();
        List<Interval> res = sol.mergeTwoInterval(l1,l2);
        for(Interval i : res) {
            System.out.print(i.start + "," + i.end + " ");
        }
    }
}
