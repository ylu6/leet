import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class SummaryRanges {
    TreeMap<Integer, Integer> tm; // stores {start, end} of an interval

    /** Initialize your data structure here. */
    public SummaryRanges() {
        tm = new TreeMap<>();
    }

    public void addNum(int val) {
        if(tm.containsKey(val)) return;

        Integer loKey = tm.lowerKey(val);
        Integer hiKey = tm.higherKey(val);

        if(loKey != null && tm.get(loKey) + 1 == val && hiKey!=null && val + 1 == hiKey) {
            tm.put(loKey, tm.get(hiKey));
            tm.remove(hiKey);
        } else if(hiKey != null && val + 1 == hiKey) {
            tm.put(val, tm.get(hiKey)); // val connects hi interval only
            tm.remove(hiKey); // remove hiKey
        } else if(loKey != null && tm.get(loKey)+1 >= val) {
            tm.put(loKey, Math.max(tm.get(loKey), val));
        } else {
            tm.put(val, val);
        }
    }

    public List<Interval> getIntervals() {
        List<Interval> list = new ArrayList<>();
        for(Integer key : tm.keySet()) {
            list.add(new Interval(key, tm.get(key)));
        }
        return list;
    }
}
