package com.company;
import java.util.*;
/**
 * Created by yeqing on 5/23/2017.
 */
public class RightInterval {
    class Interval {
        int start, end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }

    public int[] findRightInterval(Interval[] intervals) {
        int[] res = new int[intervals.length];
        TreeMap<Integer, Integer> indexMap = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++)
            indexMap.put(intervals[i].start, i);

        Integer rightKey;
        for (int i = 0; i < intervals.length; i++) {
            rightKey = indexMap.ceilingKey(intervals[i].end);
            if (rightKey == null) res[i] = -1;
            else    res[i] = indexMap.get(rightKey);
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
