import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeqing on 10/30/2017.
 */
public class DegreeOfAnArray {
    public int findShortestSubArray(int[] nums) {
        if (nums.length < 2) return nums.length;

        Map<Integer, Integer> mapCount = new HashMap<>();
        Map<Integer, Integer> mapIndex = new HashMap<>();

        mapIndex.put(nums[0],0);
        mapCount.put(nums[0],1);
        int maxFreq = 1, lenOfMaxFreq = 1;

        for (int i = 1; i < nums.length; i++) {
            int n = nums[i];
            int freq = mapCount.getOrDefault(n, 0) + 1;
            mapCount.put(n, freq);
            if (freq == 1) mapIndex.put(n, i);
            if (freq > maxFreq) {
                maxFreq = freq;
                lenOfMaxFreq = i - mapIndex.get(n) + 1;
            }
            else if (freq == maxFreq && freq > 1) {
                lenOfMaxFreq = Math.min(i - mapIndex.get(n) + 1, lenOfMaxFreq);
            }
        }
        return lenOfMaxFreq;
    }
}
