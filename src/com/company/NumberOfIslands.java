package com.company;

/**
 * Created by yeqing on 4/20/2017.
 * Leetcode problem 200, number of islands
 */

public class NumberOfIslands {
    int idx2DTo1D (int p, int q, int numOfCol) {
        return p*numOfCol + q;  // 1d index of site (p, q)
    }
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length;
        if (grid[0] == null || grid[0].length == 0) return 0;
        int n = grid[0].length;

        int numOfOnes = 0;
        boolean[] isIsland = new boolean[m*n]; // true if it is island
        WeightedUnionFind uf = new WeightedUnionFind(m*n);
        for (int i = 0; i < m; i++) {
            for (int j =0; j < n; j++) {
                if (grid[i][j] == '1') {
                    numOfOnes++;
                    int idx = idx2DTo1D(i, j, n);
                    if (i+1 < m && grid[i+1][j] == '1')
                        uf.union(idx, idx+n);
                    if (j+1 < n && grid[i][j+1] == '1')
                        uf.union(idx, idx+1);
                }
            }
        }
        // m*n - uf.numOfComponents() gives the total number of union operation,
        // which only operates when grid[i][j] is 1
        // numberOfOnes - number of union operations on 1 is number of islands
        return numOfOnes - (m*n - uf.numOfComponents());
    }

    // v2, dfs without visited matrix, modify original grid
    public int numIslands2(char[][] grid) {
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }
    void dfs(char[][] grid, int i, int j) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return;
        if(grid[i][j] == '0') return;
        grid[i][j] = '0';
        dfs(grid, i-1, j);
        dfs(grid, i+1, j);
        dfs(grid, i, j-1);
        dfs(grid, i, j+1);
    }
    public static void main (String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        NumberOfIslands solution = new NumberOfIslands();
        int num = solution.numIslands(grid);
        System.out.println("number of islands = " + num);
    }
}