/**
 * Created by yeqing on 6/8/2017.
 */
// same idea as LongestIncreasingSubsequency

public class IncreasingTripletSubsequence {
    public boolean increasingTriplet(int[] nums) {
        // dp array store smallest tail value for sub-sequeence with length i+1
        int[] dp = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        int len = 0;
        for (int n : nums) {
            int idx;
            for (idx = len; idx > 0; idx--) {
                if (dp[idx] >= n && dp[idx-1] < n) break;
            }
            dp[idx] = n;
            if (len == idx) len++;
            if (len == 3) return true;

//            int idx = Arrays.binarySearch(dp, n);
//            if (idx < 0) {
//                idx = -1*(idx+1);
//                dp[idx] = n;
//                if (len == idx) len++;
//                if (len==3) return true;
//            }
        }
        return false;
    }

    public static void main(String[] args) {
        IncreasingTripletSubsequence sol = new IncreasingTripletSubsequence();
        int[] nums = {1,4,2, 5, 1};
        System.out.println(sol.increasingTriplet(nums));
    }
}
