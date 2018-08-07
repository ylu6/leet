/**
 * Created by yeqing on 6/5/2017.
 */
// add up all numbers in the array, if the sum is odd, then the array cannot be partitioned
// into two subset with same sum value
// Dynamic Programming approach, time O(N*sum/2)
public class EqualSubsetSum {
    // version 1, use 2-d dp array to store results of sub-problem, space O(N*sum/2)
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int n : nums) sum += n;
        if(sum % 2 != 0) return false;

        sum /= 2;
        // dp[i][j]: can value j be reached by adding up numbers picked from subset nums[0:i)
        boolean[][] dp = new boolean[nums.length+1][sum + 1];
        dp[0][0] = true;  // dp[0][j]: can value j be reached by using a empty subset? only true for j==0

        for(int i = 1; i <= nums.length; i++) { //index i corresponding to nums[i-1]
            dp[i][0] = true; // for value 0, we can always use empty subset, theerfore is true
            for(int j = nums[i-1]; j <= sum; j++) {
                dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]]; // true if dp[i-1][j] is true and not pick nums[i-1],
                                            // or dp[i-1][j] is false but dp[i-1][j-nums[i-1]] is true, pick nums[i-1]
            }
        }
        return dp[nums.length][sum];
    }

    // version 2, use 1d dp array instead, space O(sum/2)
    // scan value from sum to zero; if start from zero, previous dp elements were already modified when used
    public boolean canPartition2(int[] nums) {
        if (nums == null || nums.length == 0) return true;
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum%2 == 1) return false; // sum of the nums array is odd
        sum = sum/2;
        boolean[] dp = new boolean[sum+1];
        dp[0] = true;
        for (int i = 1; i <= nums.length; i++) {
            // decision for (i-1)th number, if num[i-1] is larger than j, then num[i-1] cannot be picked
            // therefore for j < nums[i-1], dp[j] is the same as previous loop, no need to update
            // this speed up the program
            for (int j = sum; j >= nums[i-1]; j--) {
                // either picking or not picking (i-1)th number in the array
                dp[j] = dp[j] || dp[j-nums[i-1]];  // transition function
            }
            System.out.println(dp[sum]);
            if (dp[sum]) return true; // early break
        }
        return dp[sum];
    }
    public static void main(String[] args) {
        EqualSubsetSum sol = new EqualSubsetSum();
        int[] nums = {1, 2, 5};
        System.out.println(sol.canPartition2(nums));
    }
}
