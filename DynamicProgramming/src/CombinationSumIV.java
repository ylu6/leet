import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CombinationSumIV {
    // v1, top down solution with hashmap
    public int combinationSum4(int[] nums, int target) {
        Map<Integer, Integer> memo = new HashMap<>(); // {target, number_of_valid_combinations}
        Arrays.sort(nums);
        return dfs(nums, target, memo);
    }

    int dfs(int[] nums, int target, Map<Integer, Integer> memo) {
        if(target == 0) return 1;
        if(target < 0) return 0;
        if(memo.containsKey(target)) return memo.get(target);

        int res = 0;

        for(int i = 0; i < nums.length; i++) {
            if (target < nums[i]) break;
            else res += dfs(nums, target - nums[i], memo);
        }
        memo.put(target, res);
        return res;
    }

    public static void main(String[] args) {
        CombinationSumIV sol = new CombinationSumIV();
        int[] nums = {1,2,3};
        System.out.println(sol.combinationSum4(nums, 4));
    }
}
