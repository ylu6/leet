/**
 * Created by yeqing on 11/22/2017.
 */

public class MaximumLengthOfRepeatedSubarray {
    // use DP to solve this problem. A: [1,2,3,2,1] B: [3,2,1,4,7]
    // do double loop, one index in A, one index in j
    // dp[i][j]: store maximum length of repeated subarray ended at i-1 in A and j-1 in B
    public int findLength(int[] A, int[] B) {
        int[][] dp = new int[A.length+1][B.length+1];
        int maxLen = 0;
        // O(MN) time and space, space can be optimized to O(N)
        for (int i = 1; i < A.length+1; i++) {
            for (int j = 1; j < B.length+1; j++) {
                if (A[i-1] == B[j-1]) {
                    dp[i][j] = 1+dp[i-1][j-1]; // transition function
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return maxLen;
    }
}
