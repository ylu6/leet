import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {
    List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(n, 2, new ArrayList<Integer>(), res);
        return res;
    }

    void dfs(int n, int start, List<Integer> tempList, List<List<Integer>> res) {
        for(int i = start; i*i <= n; i++) {
            if(n%i == 0) {
                tempList.add(i);
                List<Integer> list = new ArrayList<>(tempList);
                list.add(n/i);
                res.add(list);
                dfs(n/i, i, tempList, res);
                if(tempList.size() > 0) tempList.remove(tempList.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        FactorCombinations sol = new FactorCombinations();
        int n = 32;
        for(List list : sol.getFactors(n))
            System.out.println(list.toString());
    }
}
