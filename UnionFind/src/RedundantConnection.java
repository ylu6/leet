import java.util.Arrays;

/**
 * Created by yeqing on 11/7/2017.
 * The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N),
 * with one additional edge added. The added edge has two different vertices chosen from 1 to N,
 * and was not an edge that already existed.
 * The size of the input 2D-array will be between 3 and 1000
 */

public class RedundantConnection {
    // weighted union-find with path compression
    public int[] findRedundantConnection(int[][] edges) {
        int[] parent = new int[2*edges.length];
        int[] sz = new int[parent.length];
        // initialize parent array, every element points to itself
        for (int i = 0; i < parent.length; i++) parent[i] = i;
        Arrays.fill(sz, 1);

        for (int[] edge : edges) {
            int p = find(parent, edge[0]);
            int q = find(parent, edge[1]);
            if (p==q) return edge;
            union(parent, sz, p, q);
        }
        return new int[2]; // default return, if no such edge found
    }

    int find(int[] parent, int n) {
        while (n != parent[n]) {
            parent[n] = parent[parent[n]]; // path compression
            n = parent[n];
        }
        return n;
    }
    void union(int[] parent, int[] sz, int p, int q) {
        int pRoot = find(parent, p);
        int qRoot = find(parent, q);
        if (pRoot == qRoot) return;

        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}
