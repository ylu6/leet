import java.util.Arrays;

/**
 * Created by yeqing on 6/8/2017.
 */
//Given an unsorted array of integers, find the length of longest increasing subsequence.
//        For example,
//        Given [10, 9, 2, 5, 3, 7, 101, 18]
//        [2, 3, 7, 18] is one result, return its length 4
public class LongestIncreasingSubsequence {
    // process the nums array from head to tail
    // use a dp array to store smallest tail value of subarray with length of i+1
    // i is the index in the dp-array
    // for [10, 9, 2, 5, 3, 7, 101, 18], dp length is 8
    // dp index  1, 2, 3, 4, 5, 6, 7, 8
    // num 10   10 increasing subsequence with length of 1, smallest tail value is 10
    // num 9    9  update tail value to 9
    // num 2    2  update tail value to 2
    // num 5    2, 5  5 is larger than 2, the length of increasing subsequence increased by 1
    // num 3    2, 3  update 5 with 3
    // num 7    2, 3, 7 increasing subsequence length increased by 1, add 7

    // O(NlogN), dp[i]: tail number of LIS with length i, do binary search for each new number
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int len = 0;

        for (int n : nums) {
            int idx = Arrays.binarySearch(dp, 0, len, n);
            if (idx < 0) {
                idx = -1*(idx+1);
                dp[idx] = n;
                if (idx == len) len++;
            }
        }
        return len;
    }

    // O(N^2), straightforward, dp[i]: length of LIS ends at nums[i]
    public int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        int maxLen = 0;
        for(int i = 0; i < nums.length; i++) {
            dp[i] = 1; // shortest increasing subsequence is 1, which is the nums[i] itself
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], 1+dp[j]);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    public static void main(String[] args){
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        LongestIncreasingSubsequence sol = new LongestIncreasingSubsequence();
        System.out.println(sol.lengthOfLIS(nums));
    }
}
