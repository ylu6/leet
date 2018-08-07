package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by yeqing on 6/20/2017.
 * check if s is subsequence of t.
 */
public class IsSubsequence {
    // Straight forward approach, two pointer
    // Time Complexity O(s_length + t_length)
    public boolean isSubsequence(String s, String t) {
        int pos = 0;
        for (int i = 0; i < s.length(); i++) {
            pos = t.indexOf(s.charAt(i), pos);
            if (pos == -1) return false;
            pos++;
        }
        return true;
    }

    // version 2, follow up question,
    // if t is constant string a there are lots of incoming String s
    public boolean isSubsequence2(String s, String t) {
        Map<Character, TreeSet<Integer>> map = new HashMap<>();
        for(int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!map.containsKey(c))
                map.put(c, new TreeSet<Integer>());
            map.get(c).add(i);
        }
        Integer pos = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) return false;
            pos = map.get(s.charAt(i)).ceiling(pos);
            if (pos == null) return false;
            pos++;
        }
        return true;
    }

}
