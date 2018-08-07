import java.util.*;

/**
 * Created by yeqing on 1/15/2018.
 */
public class PartitionLabels {
    // v1, use a hashset to check search for valid partition
    public List<Integer> partitionLabels(String S) {
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (char c : S.toCharArray())
            map.put(c, 1 + map.getOrDefault(c, 0));
        List<Integer> res = new ArrayList<>();
        int cnt = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            cnt++;
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0) {
                set.remove(c);
                if (set.size() == 0) {
                    res.add(cnt);
                    cnt = 0;
                }
            } else set.add(c);
        }
        return res;
    }

    // v2, store last index of char in S into map, instead of store frequency count
    public List<Integer> partitionLabels2(String S) {
        int[] map = new int[26]; // last index of char in S
        for (int i = 0; i < S.length(); i++) {
            map[S.charAt(i) - 'a'] = i;
        }
        List<Integer> res = new ArrayList<>();
        int start = -1, end = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            end = Math.max(end, map[c - 'a']);
            if (end == i) {
                res.add(end - start);
                start = end;
            }
        }
        return res;
    }
}