import java.util.*;

/**
 * Created by yeqing on 5/27/2017.
 */
public class CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        dfs(res, tempList, n, 1, k);

        return res;
    }

    void dfs(List<List<Integer>> res, List<Integer> tempList, int target, int start, int k) {
        if(k == 0) {
            if(target == 0) {
                res.add(new ArrayList<>(tempList));
            }
            return;
        }

        for(int i = start; i < 10; i++) {
            if(i > target) break;
            tempList.add(i);
            dfs(res, tempList, target-i, i+1, k-1);
            if(tempList.size() > 0) tempList.remove(tempList.size()-1);
        }
    }
}
