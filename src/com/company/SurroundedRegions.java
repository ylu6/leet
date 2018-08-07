package com.company;

/**
 * Created by yeqing on 4/20/2017.
 */
public class SurroundedRegions {
    public static void solve(char[][] board) {
        //if (board == null || board.length == 0) return 0;
        int m = board.length;
        if (board[0] == null || board[0].length == 0) return;
        int n = board[0].length;
        int sz = m*n, virtualNode = sz;
        WeightedUnionFind uf = new WeightedUnionFind(sz + 1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] =='O') {
                    int idx = i*n + j;
                    if (i==0 || i==m-1 || j==0 || j==n-1)
                        uf.union(virtualNode, idx);
                    if (i+1 < m && board[i+1][j] == 'O') // union down
                        uf.union(idx, idx + n);
                    if (j+1 < n && board[i][j+1] == 'O') // union right
                        uf.union(idx, idx + 1);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] =='O') {
                    int idx = i*n + j;
                    if (uf.connected(idx, virtualNode))
                        continue;
                    board[i][j] = 'X';
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X' },
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        solve(board);
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + ", ");
            }
            System.out.println();
        }
    }
}
