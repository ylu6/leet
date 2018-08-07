import java.util.*;

/**
 * Created by yeqing on 8/31/2017.
 */
public class IncreasingSubsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
//        Arrays.sort(nums); // the original order should be kept, therefore the array might be unsorted
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, path, nums, 0);
        return res;
    }
    void dfs(List<List<Integer>> res, List<Integer> path, int[] nums, int pos) {
        if (path.size() > 1) res.add(new ArrayList<>(path));
        if (pos == nums.length) return;

        Set<Integer> used = new HashSet<>();
        for (int i = pos; i < nums.length; i++) {
            if(used.contains(nums[i])) continue;
            if(path.size()==0 || nums[i]>=path.get(path.size()-1)) {
                used.add(nums[i]);
                path.add(nums[i]);
                dfs(res, path, nums, i + 1);
                if (path.size() != 0) path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {4, 3,2,1};
        IncreasingSubsequences sol = new IncreasingSubsequences();
        List<List<Integer>> res = sol.findSubsequences(nums);
        for (List<Integer> list : res) System.out.println(list.toString());
    }
}
