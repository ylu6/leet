import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
 * Note: The solution set must not contain duplicate subsets.
 * Created by yeqing on 5/28/2017.
 */
public class Subsets2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, res, new ArrayList<>(), 0);
        return res;
    }
    private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> prvList, int pos) {
        res.add(new ArrayList<Integer>(prvList));

        for (int i = pos; i < nums.length; i++) {
            if (i > pos && nums[i] == nums[i-1]) continue;
            prvList.add(nums[i]);
            backtrack(nums, res, prvList, i+1);
            if (prvList.size() > 0) prvList.remove(prvList.size()-1);
        }
    }
}
