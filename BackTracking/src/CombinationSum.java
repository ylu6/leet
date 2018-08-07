import java.util.*;
/**
 * Created by yeqing on 5/26/2017.
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        dfs(res, candidates, tempList, target, 0);

        return res;
    }

    void dfs(List<List<Integer>> res, int[] candidates, List<Integer> tempList, int target, int start) {
        if(target == 0) {
            res.add(new ArrayList<>(tempList));
            return;
        }

        for(int i = start; i < candidates.length; i++) {
            if(target < candidates[i]) break; // prune remaining branches
            tempList.add(candidates[i]);
            dfs(res, candidates, tempList, target-candidates[i], i); // not i+1, because same number can be used multiple times
            if(tempList.size() > 0) tempList.remove(tempList.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {7, 2, 3, 6};
        CombinationSum solution = new CombinationSum();
        List<List<Integer>> res = solution.combinationSum(nums, 7);
        for (List<Integer> list : res) {
            for (int n : list) {
                System.out.print(n + ", ");
            }
            System.out.println();
        }
    }
}
