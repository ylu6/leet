import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeqing on 5/28/2017.
 */
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        combineDFS(n, k, 1, new ArrayList<Integer>(), res);
        return res;
    }

    private void combineDFS(int n, int k, int curr, List<Integer> prvList, List<List<Integer>> res) {
        if (k == 0) {
            res.add(new ArrayList<>(prvList));
            return;
        }
        for (int i = curr; i <= n; i++) {
            prvList.add(i);
            combineDFS(n, k-1, i+1, prvList, res);
            if (prvList.size() > 0) prvList.remove(prvList.size()-1);
        }
    }

}
