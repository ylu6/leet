package com.company;

/**
 * Created by yeqing on 4/20/2017.
 */
public class FriendCircles {
    public static int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) return 0;
        int n = M.length;
        WeightedUnionFind uf = new WeightedUnionFind(n);
        int res = n;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (M[i][j] ==1 ) uf.union(i, j);
            }
        }
        return uf.numOfComponents();
    }
    public static void main(String[] args) {
        int[][] M = new int[][] {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        System.out.println(findCircleNum(M));
    }
}
