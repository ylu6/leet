package com.company;

import java.util.Iterator;
import java.util.List;

/**
 * Created by yeqing on 6/20/2017.
 */
public class MaximumDistanceInArrays {
    public int maxDistance(List<List<Integer>> arrays) {
        if (arrays == null || arrays.size() == 0) return 0;
        Iterator<List<Integer>> it = arrays.iterator();
        List<Integer> list = it.next();
        int min = list.get(0);
        int max = list.get(list.size()-1);

        int result = 0, head, tail;
        while (it.hasNext()) {
            list= it.next();
            head = list.get(0);
            tail = list.get(list.size()-1);
            result = Math.max(result, Math.abs(head - max));
            result = Math.max(result, Math.abs(tail - min));
            min = Math.min(head, min);
            max = Math.max(tail, max);
        }
        return result;
    }
}
