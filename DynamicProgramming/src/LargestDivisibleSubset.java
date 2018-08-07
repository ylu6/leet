import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yeqing on 8/9/2017.
 */
public class LargestDivisibleSubset {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        //dp[i]: length of largest divisible subset, whose largest number is nums[i]
        //prv[i]: previous number of nums[i] in this largest divisible subset
        //prv[i] is used to get the final result list.
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        int[] prv = new int[nums.length];
        int maxLen = 0, maxIdx = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j >= 0; j--) {// start from i, because if no other number can divide nums[j],
                if (nums[i]%nums[j]==0 && dp[i] < dp[j]+1) {// nums[j] will be the only number in the set, dp[i] = 1 instead of 0
                    dp[i] = dp[j]+1;
                    prv[i] = j;
                    if (dp[i] > maxLen) {
                        maxLen = dp[i];
                        maxIdx = i;
                    }
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < maxLen; i++) {
            res.add(nums[maxIdx]);
            maxIdx = prv[maxIdx];
        }
        return res;
    }
    public static void main(String[] args) {
        LargestDivisibleSubset sol = new LargestDivisibleSubset();
        int[] nums = {1,2,4,6,8};
        System.out.println(sol.largestDivisibleSubset(nums).toString());
    }
}
