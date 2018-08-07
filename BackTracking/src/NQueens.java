import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yeqing on 6/1/2017.
 */
public class NQueens {
    // 1st approach generate a pruned board every time a new Queen was placed on board
    // lots of nxn array will be generated, which is memory inefficient
    public List<List<String>> solveNQueens(int n) {
        boolean[][] pruned = new boolean[n][n];
        List<List<String>> res = new ArrayList<>();
        solveNQueens(n, 0, res, new ArrayList<String>(), pruned);
        return res;
    }

    // the board is always valid. Validata the new location every time a new queen was added
    // validation is done by loop through all previous board rows
    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> res = new ArrayList<>();
        backtrack(n, 0, res, new ArrayList<String>());
        return res;
    }

    // approach 3, so far the best approach
    // use 3 boolean array to keep track of used positions
    // it is straightforward for column, if a column i is used, cols[i] set to true
    // for diagonal, according to math, diagonal line can be written as col+row=const or col-row=const
    // for col+row, all possible const values are in range [0, 2*(n-1)]
    // for col-row, all possible const values are in range [-(n-1), n-1], add n+1 to map to [0, 2*(n-1)]
    public List<List<String>> solveNQueens3(int n) {
        List<List<String>> res = new ArrayList<>();
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2*n-1];
        boolean[] diag2 = new boolean[2*n-1];
        backtrack3(n, 0, res, new ArrayList<String>(), cols, diag1, diag2);
        return res;
    }

    private void backtrack3(int n, int row, List<List<String>> res, List<String> path,
                            boolean[] cols, boolean[] diag1, boolean[] diag2)
    {
        if (row == n) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int col = 0; col < n; col++) {
            int diagID1 = col + row; // in range [0, 2*n-1]
            int diagID2 = col - row + n - 1; // in range [0, 2*n-1]
            if (cols[col] || diag1[diagID1] || diag2[diagID2]) continue;
            cols[col] = true;
            diag1[diagID1] = true;
            diag2[diagID2] = true;
            path.add(generateRow(n, col));
            backtrack3(n, row+1, res, path, cols, diag1, diag2);
            if (path.size() > 0) path.remove(path.size()-1);
            cols[col] = false;
            diag1[diagID1] = false;
            diag2[diagID2] = false;
        }
    }

    private void solveNQueens(int n, int row, List<List<String>> res, List<String> path, boolean[][] pruned) {
        if (row == n) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!pruned[row][col]) {
                path.add(generateRow(n, col));
                solveNQueens(n, row+1, res, path, pruneBoard(pruned, row, col));
                if (path.size() > 0) path.remove(path.size()-1);
            }
        }
    }

    private void backtrack(int n, int row, List<List<String>> res, List<String> path) {
        if (row == n) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (!isValid(path, row, col)) continue;
            path.add(generateRow(n, col));
            backtrack(n, row+1, res, path);
            if (path.size() > 0) path.remove(path.size()-1);
        }
    }
    //generate a row of the board in String representation. Only one char is 'Q' at index 'pos'
    private String generateRow(int n, int pos) {
        char[] row = new char[n];
        Arrays.fill(row, '.');
        row[pos] = 'Q';
        return new String(row);
    }

    private boolean[][] pruneBoard(boolean[][] pruned, int row, int col) {
        boolean[][] res = new boolean[pruned.length][pruned.length];
        for (int i = row; i < pruned.length; i++)
            for (int j = 0; j < pruned.length; j++)
                res[i][j] = pruned[i][j];

        for (int i = 0; row+i < pruned.length; i++) {
            res[row+i][col] = true;
            if (col - i >= 0) res[row+i][col-i] = true;
            if (col + i < pruned.length) res[row+i][col+i] = true;
        }
        return res;
    }

    // check whether the new queen at index (row, col) is valid, based on previous queens already on board
    boolean isValid(char[][] board, int row, int col) {
        for (int i = 1; row-i >= 0; i++) {
            if (board[row-i][col] == 'Q') return false;
            if (col-i >= 0 && board[row-i][col-i] == 'Q') return false;
            if (col+i < board.length && board[row-i][col+i] == 'Q') return false;
        }
        return true;
    }

    // assume row is always path.size(), which is adding next row to current board
    boolean isValid(List<String> path, int row, int col) {
        if (row == 0) return true;
        for (int i = 1; row-i >= 0; i++) {
            String s = path.get(row-i);
            if (s.charAt(col) == 'Q') return false;
            if (col-i >= 0 && s.charAt(col-i) == 'Q') return false;
            if (col+i < s.length() && s.charAt(col+i) == 'Q') return false;
        }
        return true;
    }
    public static void main(String[] args) {
        NQueens nq = new NQueens();
        List<List<String>> solutions = nq.solveNQueens3(4);
        for (List<String> solution : solutions) {
            for (String row : solution)
                System.out.println(row);
            System.out.println();
        }
//        boolean[][] pruned = new boolean[4][4];
//        nq.pruneBoard(pruned, 0, 0);
//        nq.pruneBoard(pruned, 1, 2);
//        nq.unpruneBoard(pruned, 1, 2);
//        for (boolean[] row : pruned) {
//            for (boolean cell : row)
//                System.out.print(cell + " ");
//            System.out.println();
//        }
    }
}
