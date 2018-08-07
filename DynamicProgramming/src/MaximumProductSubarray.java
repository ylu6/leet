/**
 * Created by yeqing on 8/10/2017.
 */

public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        // max[i]: product of subarray starting from any position from 0 to i and ends at i, which has largest product
        // min[i]: product of subarray starting from any position from 0 to i and ends at i, which has smallest product
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        int res = nums[0];
        max[0] = nums[0]; min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // max[i] can be current val multiply previous max, or previous min, or starting from fresh (in case previous value is 0)
            max[i] = Math.max(Math.max(nums[i]*max[i-1], nums[i]*min[i-1]), nums[i]);
            min[i] = Math.min(Math.min(nums[i]*max[i-1], nums[i]*min[i-1]), nums[i]);
            res = Math.max(res, max[i]);
        }
        return res;
    }

}
