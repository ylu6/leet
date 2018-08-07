import java.util.*;

/**
 * Created by yeqing on 11/21/2017.
 */
public class MinimumHeightTrees {
    // leaf has degree of 1, tree is a connected undirected graph
    // removing leaf from the tree, the graph is still connected
    // 1. find all leafs in the original graph
    // 2. remove leafs layer by layer,
    // 3. onece a leaf was removed, update the adj list of neighbour nodes of the removed leaf
    // In the end, there will be 2 nodes or 1 nodes left, which is the root we are looking for
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1) return Collections.singletonList(0);
        List<Set<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++)  adj.add(new HashSet<Integer>());
        for(int[] edge : edges) { // construct the adj from array of edges
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        List<Integer> leafs = new ArrayList<>();
        for(int v = 0; v < n; v++) {
            if(adj.get(v).size() == 1)  leafs.add(v); // the adj list of v only has 1 element, v is a leaf node
        }
        while(n>2) {
            List<Integer> newLeafs = new ArrayList<>();
            for(int v : leafs) { // remove all leafs
                n--; // decrease the total node count by 1
                for(int w : adj.get(v)) { // w is the neighbour of v
                    adj.get(w).remove(v); // remove v from adj list of w
                    if(adj.get(w).size()==1) newLeafs.add(w); // w becomes a leaf after remove v
                }
            }
            leafs = newLeafs;
        }
        return leafs; // in the end, there will be 1 or 2 nodes left, those must be leafs, return them as results
    }
}
