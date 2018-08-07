package com.company;

/**
 * Created by yeqing on 4/20/2017.
 */
public class WeightedUnionFind {
    private int[] id;
    private int[] sz;
    private int counts;

    public WeightedUnionFind (int n) {
        counts = n;
        id = new int[n];
        for (int i = 0; i < n; i++) id[i] = i;
        sz = new int[n];
    }

    public void union (int p, int q) { // union p, q
        if (p == q) return;
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);
        if (pRoot == qRoot) return;
        if (sz[pRoot] > sz[qRoot]) {    // attach q to p
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
        else {                          // attach p to q
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];     // update sz of qRoot
        }
        counts--;
    }

    int findRoot(int p) {
        while (p != id[p]) {
            p = id[p];
            id[p] = id[id[p]]; // for path compression
        }
        return p;
    }

    public int numOfComponents() {
        return counts;
    }

    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }
}


