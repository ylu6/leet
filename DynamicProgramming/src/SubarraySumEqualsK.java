import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeqing on 6/15/2017.
 */
public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1); // for sub-array starting from index 0
        int count = 0, sum = 0;

        for (int n : nums) {
            sum += n;
            count += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1};
        SubarraySumEqualsK sol = new SubarraySumEqualsK();
        System.out.println(sol.subarraySum(nums, 0));
    }
}
