/**
 * Created by yeqing on 8/23/2017.
 */
public class PredictTheWinner {
    //dp[i][j]: for sub-array [i,j], maximum difference: CurrentPlayer - NextPlayer
    // dp[i][j] = max(nums[i]-dp[i+1][j], nums[j]-dp[i][j-1])
    public boolean PredictTheWinner(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        for (int j = 0; j < nums.length; j++) {
            dp[j][j] = nums[j]; //only nums[j] is available for pick
            for (int i = j-1; i >=0; i--) {
                dp[i][j] = Math.max(nums[i]-dp[i+1][j], nums[j]-dp[i][j-1]);
            }
        }
        return dp[0][nums.length-1] >= 0;
    }
}
