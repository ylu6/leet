/**
 * Created by yeqing on 11/7/2017.
 */
public class RedundantConnectionII {
    // for a tree to be valid: 1. no node has more than one parent, 2. no cycle
    /* 3 possible combinations for invalid tree
    a. one node has two parents (edge1, edge2), no cycle return edge2
    b. one node has two parent, has cycle, return the edge in the cycle
    c. no node has more than one parent, has cycle, return the last edge which forms the cycle

    1. loop through all edges, search for node with 2 parents, save as edge1 and edge2
        for edge2, set the parent node (edge2[1]) to 0. Now no node will have 2 parents
    2. union find, loop through all edges
        if cycle formed after current edge:
            if edge1 and edge2 are null, return current edge, case c
            if not null, return edge1. because edge2 was modified and cannot form a cycle. case b, edge1 in cycle
    3. no cycle detected after loop, return edge 2. case a, or case b: edge2 is in the cycle,
        but modified, therefore no cycle can be detected
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] parent = new int[edges.length+1];
        int[] edge1 = null, edge2 = null;
        for (int[] edge : edges) { // step 1, search for node with 2 parents
            if (parent[edge[1]] == 0)
                parent[edge[1]] = edge[0];
            else {
                edge2 = new int[]{edge[0], edge[1]};
                edge1 = new int[]{parent[edge[1]], edge[1]};
                edge[1] = 0; // modify this edge, now there is no node with 2 parents
            }
        }
        // step 2, union find
        for (int i = 1; i < parent.length; i++) parent[i] = i;

        for (int[] edge : edges) {
            if (edge[1] == 0) continue; // this is the modified edge, don't use it
            int p = edge[0], q = edge[1];
            int pRoot = find(parent, p);
            int qRoot = find(parent, q);
            if (pRoot == qRoot) { // cycle detected
                if (edge1 == null) return edge; // case 3, there is no node has 2 parents
                else {                          // case 2
                    return edge1;
                }
            }
        }
        return edge2; // no cycle detected
    }

    int find(int[] parent, int n) { // find root of node 'n'
        while (n != parent[n]) {
            //parent[n] = parent[parent[n]]; // path compression
            n = parent[n];
        }
        return n;
    }
}
