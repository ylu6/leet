import java.util.ArrayList;
import java.util.HashSet;

public class SumOfDistancesInTree {
    // numOfNodes[i]: number of nodes in subtree rooted at node i, in the original tree structure
    // res[i]: sum of distances between node i to all nodes in subtree when tree is rooted at node i
    // perform a dfs search first to calculate numOfNodes and res[0], when the root of the whole tree is at node 0
    // for edge [0,i], if we assign
    ArrayList<HashSet<Integer>> tree;
    int[] res, numOfNodes;
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        tree = new ArrayList<>();
        res = new int[N]; numOfNodes = new int[N];
        // store the tree in a adjacent list, initialize the tree from edges
        for(int i = 0; i < N; i++) tree.add(new HashSet<>());
        for(int[] edge : edges) tree.get(edge[0]).add(edge[1]);
        dfs(0, new HashSet<>(), 0);
        dfs2(0, new HashSet<>());
        return res;
    }
    // return number of nodes in subtree rooted at node n
    int dfs(int n, HashSet<Integer> seen, int dist) {
        seen.add(n);
        res[0] += dist;
        int count = 1;
        for(int i : tree.get(n)) {
            if(!seen.contains(i)) count += dfs(i, seen, dist+1);
        }
        numOfNodes[n] = count;
        return count;
    }

    void dfs2(int n, HashSet<Integer> seen) {
        seen.add(n);
        for(int i : tree.get(n)) {
            if(!seen.contains(i)) {
                res[i] = res[n] - numOfNodes[i] + (res.length - numOfNodes[i]);
            }
        }
    }
}
