/**
 * Created by yeqing on 6/2/2017.
 */
public class NQueensII {
    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2*n-1];
        boolean[] diag2 = new boolean[2*n-1];
        return backtrack3(n, 0, cols, diag1, diag2);
    }

    private int backtrack3(int n, int row, boolean[] cols, boolean[] diag1, boolean[] diag2)
    {
        int count = 0;
        if (row == n) {
            return 1;
        }
        for (int col = 0; col < n; col++) {
            int diagID1 = col + row; // in range [0, 2*n-1]
            int diagID2 = col - row + n - 1; // in range [0, 2*n-1]
            if (cols[col] || diag1[diagID1] || diag2[diagID2]) continue;
            cols[col] = true;
            diag1[diagID1] = true;
            diag2[diagID2] = true;
            count += backtrack3(n, row+1, cols, diag1, diag2);
            cols[col] = false;
            diag1[diagID1] = false;
            diag2[diagID2] = false;
        }
        return count;
    }

    public static void main (String[] args) {
        NQueensII nq = new NQueensII();
        System.out.println(nq.totalNQueens(4));
    }
}
