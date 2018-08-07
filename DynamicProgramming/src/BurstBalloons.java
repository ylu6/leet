/**
 * Created by yeqing on 8/9/2017.
 */
public class BurstBalloons {
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // dp[i][j] is the maximum # of coins that can be obtained
        // by popping balloons only in the range [i,j],
        // and the left and right boundary of [i,j], which is i-1 and j+1 is not popped yet
        int[][] dp = new int[nums.length][nums.length];
        // in sub-range [i,j], assume the last balloon popped is k
        // varying k from i to j, and pick the biggest value, which is dp[i][j]
        // for a specific k, dp[i][j]_k = dp[i][k-1] + dp[k+1][j] + nums[i-1]*nums[k]*nums[j+1]
        for (int len = 1; len <= nums.length; len++) {
            for (int start = 0; start <= nums.length - len; start++ ) {
                int end = start + len -1;
                int maxVal = 0;
                for (int mid = start; mid <= end; mid++) {
                    int prv = start-1 < 0 ? 1 : nums[start-1];
                    int nxt = end+1 >= nums.length ? 1 : nums[end+1];
                    int val = (mid < 1 ? 0 : dp[start][mid-1]) + prv*nums[mid]*nxt +
                            (mid >= nums.length-1 ? 0 : dp[mid+1][end]);
                    maxVal = Math.max(maxVal, val);
                }
                dp[start][end] = maxVal;
            }
        }
        return dp[0][nums.length-1];
    }
    public static void main(String[] args) {
        BurstBalloons sol = new BurstBalloons();
        int[] nums = {3, 1, 5, 8};
        System.out.println(sol.maxCoins(nums));
    }
}
