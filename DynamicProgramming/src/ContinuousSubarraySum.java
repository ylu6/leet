import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeqing on 6/14/2017.
 */
public class ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        // in case of k == 0, check whether there are continuous zeros in the array
        if (k == 0) {
            for (int i = 0; i < nums.length-1; i++)
                if (nums[i]==0 && nums[i+1]==0) return true;
            return false;
        }
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>(); // 0, 1 ... k-1
        map.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            sum = sum%k;
            Integer prv = map.get(sum);
            if (prv == null)
                map.put(sum, i);
            else
                if (i - prv > 1) return true; // length larger than 1
        }
        return false;
    }
}
