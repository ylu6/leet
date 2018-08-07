package com.company;
import java.util.*;

/**
 * Created by yeqing on 5/15/2017.
 */

public class SummaryRanges {
    private TreeMap<Integer, Interval> map;

    class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    /** Initialize your data structure here. */
    public SummaryRanges()
    {
        map = new TreeMap<Integer, Interval>();
    }

    public void addNum(int val)
    {
        if (map.containsKey(val)) return;

        Map.Entry<Integer, Interval> lo = map.floorEntry(val);
        Map.Entry<Integer, Interval> hi = map.ceilingEntry(val);

        if (lo != null && hi != null &&
                lo.getValue().end + 1 == val && hi.getValue().start == val + 1)
        {
            lo.getValue().end = hi.getValue().end;
            map.remove(hi.getKey());
        }
        else if (lo != null && val <= lo.getValue().end + 1)
            lo.getValue().end = Math.max(val, lo.getValue().end);
        else if (hi != null && hi.getValue().start == val + 1)
        {
            map.put(val, new Interval(val, hi.getValue().end));
            map.remove(hi.getKey());
        }
        else
            map.put(val, new Interval(val, val));
    }

    public List<Interval> getIntervals() {
        return new ArrayList<Interval>(map.values());
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 7, 2, 6};
        SummaryRanges s = new SummaryRanges();
        for (int n : nums) {
            s.addNum(n);
            for (Interval i : s.getIntervals())
                System.out.print(i.start + " " + i.end + ", ");
            System.out.println();
        }
    }
}