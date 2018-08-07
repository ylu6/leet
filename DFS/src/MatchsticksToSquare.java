import java.util.Arrays;

/**
 * Created by yeqing on 12/1/2017.
 */
public class MatchsticksToSquare {
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length < 4) return false;
        int total = 0, maxNum = 0;
        for (int n : nums) {
            total+= n;
            maxNum = Math.max(maxNum, n);
        }
        if (total % 4 != 0) return false;
        int target = total / 4; // target edge length
        if (maxNum > target) return false;
        int[] sums = new int[4]; // an array to store sum of each edge, there are 4 edges in a square
        Arrays.sort(nums); // sort the nums array in DESC order, which helps prune branch in the tree as early as possible
        // It chokes a bit when nums cannot be divided into 4 such subset as it has to explore the whole tree.
        for (int i = 0, j = nums.length-1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return dfs(nums, sums, 0, target);
    }
    boolean dfs(int[] nums, int[] sums, int idx, int target) {
        if (idx == nums.length) { // all the numbers in nums array are used, do final check, whether targets are meet
            return sums[0]==target && sums[1]==target && sums[2]==target;
        }

        for (int i = 0; i < 4; i++) {
            if (sums[i]+nums[idx] > target) continue;
            sums[i] += nums[idx];
            if (dfs(nums, sums, idx+1, target)) return true;
            sums[i] -= nums[idx]; // reset the sums array to original status, then move to next step
        }
        return false; // no solution found
    }
}
