import java.util.Arrays;

/**
 * Created by yeqing on 11/29/2017.
 */
public class NumberOfLongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {
        // len[i]: length of longest subsequence ended at nums[i]
        // cnt[i]: number of longest subsequence ended at nums[i]
        int[] len = new int[nums.length];
        int[] cnt = new int[nums.length];
        Arrays.fill(len, 1);
        Arrays.fill(cnt, 1);

        int globalMaxLen = 1; // global length of LIS
        for (int i = 1; i < nums.length; i++) {
            int maxLen = 1, count = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) { // nums[i] can be appended after nums[j] to form a IS
                    if (1+len[j] > maxLen) { // found an IS has length longer than previous maxLen
                        maxLen = 1+len[j];   // update maxLen, which is appending nums[j] to increasing Sub ends at num[i]
                        count = cnt[j];      // reset count, which is cnt[j], because one element was appended to sub ends at nums[j]
                    } else if (1+len[j] == maxLen) {
                        count+= cnt[j];     // find a new subsequence whose length is the same as maxLen, update count
                    } else continue;;
                }
            }
            len[i] = maxLen;
            cnt[i] = count;
            globalMaxLen = Math.max(globalMaxLen, maxLen);
        }

        int res = 0;
        for (int i = 0; i < nums.length; i++) { // loop again, count number of longest increasing subsequence
            if (len[i] == globalMaxLen) res += cnt[i];
        }
        return res;
    }
}
