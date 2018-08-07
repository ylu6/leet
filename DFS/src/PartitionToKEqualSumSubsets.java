/**
 * Created by yeqing on 12/12/2017.
 */
public class PartitionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum%k != 0) return false;
        int target = sum/k;
        boolean[] used = new boolean[nums.length];
        return canPartition(nums, used, 0, 0, target, k);
    }
    // return true if can be partitioned
    private boolean canPartition(int[] nums, boolean[] used, int startIdx, int sum, int target, int k) {
        if (k == 1) return true; // partition into 1 subset, which must be true
        if (sum > target) return false; // this branch is impossible because sum is already larger than target
        if (sum == target) return canPartition(nums, used, 0, 0, target, k-1);
        for (int i = startIdx; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            if(canPartition(nums, used, startIdx++,sum+nums[i], target, k)) return true;
            used[i] = false;
        }
        return false;
    }
}
