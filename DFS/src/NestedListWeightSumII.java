import java.util.ArrayList;
import java.util.List;

public class NestedListWeightSumII {
    public int depthSum(List<NestedInteger> nestedList) {
        List<Integer> list = new ArrayList<>();
        dfs(nestedList, 1, list);

        int sum = 0, weight = 1;
        for(int i = list.size()-1; i >= 0; i--) {
            sum += weight*list.get(i);
            weight++;
        }
        return sum;
    }

    void dfs(List<NestedInteger> nestedList, int depth, List<Integer> list) {
        for(NestedInteger ni : nestedList) {
            if(ni.isInteger()) {
                if(depth-1 >= list.size()) {
                    list.add(ni.getInteger());
                } else {
                    list.set(depth-1, list.get(depth-1)+ni.getInteger());
                }
            } else {
                dfs(ni.getList(), depth+1, list);
            }
        }
    }
}
