import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeqing on 11/22/2017.
 */
public class LongestConsecutiveSequence {
    // use a HashMap to store number:length of longestConsecutive sequence
    // always make sure the two end of one sub sequence is correct
    // add 1: {1:1}
    // add 3: {1:1, 3:1}
    // add 2: {1:3, 2:3, 3:3}
    // add 4: {1:4, 2:3, 3:3, 4:4}
    // if add 3 again, since it is already in the HashMap, do nothing
    public int longestConsecutive(int[] nums) {
        int maxLen = 0, len = 0, left = 0, right = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (map.containsKey(n)) continue; // critical

            left = map.getOrDefault(n-1, 0);
            right = map.getOrDefault(n+1, 0);
            len = left+right+1;
            maxLen = Math.max(maxLen, len);
            map.put(n, len);
            if (left != 0) map.put(n-left, len);
            if (right != 0) map.put(n+right, len);
        }
        return maxLen;
    }
}
