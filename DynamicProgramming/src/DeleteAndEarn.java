import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yeqing on 12/8/2017.
 */
public class DeleteAndEarn {
    // dynamic programing
    // first preprocess the nums, store unique values in nums as key, and freq*val as value in map
    // iterate from first key to last key, do dynamic programming
    public int deleteAndEarn(int[] nums) {
        if (nums==null || nums.length==0) return 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int n : nums)
            map.put(n, n + map.getOrDefault(n, 0));
        for (int n : map.keySet()) System.out.println(n + ": " + map.get(n));

        int[] dp = new int[map.size()+1];
        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        Map.Entry<Integer, Integer> entry = it.next();
        dp[1] = entry.getValue();
        int idx = 2, prv = entry.getKey();
        // dp[idx]: maximum achievable score till (idx-1)th element in the TreeMap
        while (it.hasNext()) {
            entry = it.next();
            if (entry.getKey() == prv+1) // the neighbor of current num which is (num-1) exist
                dp[idx] = Math.max(dp[idx-2] + entry.getValue(), dp[idx-1]);
            else // if not exist, just add score from current number to previous dp value
                dp[idx] = dp[idx-1] + entry.getValue();

            idx++;
            prv = entry.getKey();
        }
        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        int[] nums = {3,3,3,4,2};
        DeleteAndEarn sol = new DeleteAndEarn();
        sol.deleteAndEarn(nums);
    }
}
