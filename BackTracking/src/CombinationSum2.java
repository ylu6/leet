import java.util.*;

/**
 * Created by yeqing on 5/27/2017.
 */
public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        dfs(res, tempList, candidates, target, 0);

        return res;
    }

    void dfs(List<List<Integer>> res, List<Integer> tempList, int[] candidates, int target, int start) {
        if(target == 0) {
            res.add(new ArrayList<>(tempList));
            return;
        }

        for(int i = start; i < candidates.length; i++) {
            if(i > start && candidates[i] == candidates[i-1]) continue; // skip duplicate
            if(candidates[i] > target) break; // prune remaining branch
            tempList.add(candidates[i]);
            dfs(res, tempList, candidates, target-candidates[i], i+1); // next start = i+1 because number can be used once
            if(tempList.size() > 0) tempList.remove(tempList.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {10,1,2,7,6,1,5};
        CombinationSum2 solution = new CombinationSum2();
        for (List<Integer> list : solution.combinationSum2(nums, 8)) {
            for (int n : list)
                System.out.print(n + " ");
            System.out.println();
        }
    }
}
