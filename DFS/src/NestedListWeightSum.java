import java.util.List;

// similar to 341. Flatten Nested List Iterator
// Tree Traversal Problem, each node is a List of NestedInteger
// Use ListIterator to iterate from tail to head

public class NestedListWeightSum {
    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    int dfs(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for(NestedInteger ni : nestedList) {
            if(ni.isInteger()) {
                sum += depth*ni.getInteger();
            } else {
                sum += dfs(ni.getList(), depth+1);
            }
        }
        return sum;
    }
}



interface NestedInteger {
            // @return true if this NestedInteger holds a single integer,
             // rather than a nested list.
    public boolean isInteger();

            // @return the single integer that this NestedInteger holds,
            // if it holds a single integer
            // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

            // @return the nested list that this NestedInteger holds,
            // if it holds a nested list
            // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}