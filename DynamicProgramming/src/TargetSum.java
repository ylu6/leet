/**
 * Created by yeqing on 6/5/2017.
 */
public class TargetSum {
    /**
     * Use Dynamic programming, partition the array into two subsets, one all positive and the other all negative
     * Sum(P) - Sum(N) = target_S  (1)
     * Sum(P) + Sum(N) = Sum of Array nums (2)
     * add (1) and (2), get Sum(P) = (S + Sum)/2
     * Now the problem converted to: find number of subsets whose element sum equals to (S + Sum)/2
     * simple way is use 2D dp array, dp[i][j] stands for number of subsets from index range [0, i), and sum of subset is j
     * Transition function: dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i-1]]  ; pick (i-1)th number and not pick it
     * better approach: use a 1D dp array, and update the dp array from tail to head
     */
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int n : nums) sum += n;
        if ((Math.abs(S) > sum || (S+sum) % 2 != 0))  return 0;
        //return findTargetSumWays(nums, 0, S);
        int target = (S+sum)/2;
        int[] dp = new int[target + 1];
        dp[0] = 1; // initial condition, no element selected
        for (int n : nums)
            for (int j = target; j >= n; j--) // if j < n, then n cannot be picked
                dp[j] = dp[j] + dp[j - n];

        return dp[target];
    }

    // brute force recursive approach, time O(2^N)
    public int findTargetSumWays(int[] nums, int pos, int S) {
        if (pos == nums.length) return S == 0 ? 1 : 0;
        return findTargetSumWays(nums, pos+1, S+nums[pos]) + findTargetSumWays(nums, pos+1, S-nums[pos]);
    }

    public static void main (String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        TargetSum ts = new TargetSum();
        System.out.println(ts.findTargetSumWays(nums, 3));
    }
}
