import java.util.Arrays;

/**
 * Created by yeqing on 11/28/2017.
 */
public class OutOfBoundaryPaths {
    // bottom up, simpler code
    // dp[Ni][row][col]: move Ni times, number of paths move the ball out of boundary, starting from position (row, col)
    public int findPaths(int m, int n, int N, int i, int j) {
        int[][][] dp = new int[N+1][m][n];
        for (int Ni = 1; Ni <= N; Ni++) {
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    long left    = col == 0      ? 1 : dp[Ni-1][row][col-1];
                    long right   = col == n-1    ? 1 : dp[Ni-1][row][col+1];
                    long up      = row == 0      ? 1 : dp[Ni-1][row-1][col];
                    long down    = row == m-1    ? 1 : dp[Ni-1][row+1][col];
                    dp[Ni][row][col] = (int) ((left + right + up + down) % 1000000007);
                }
            }
        }
        return dp[N][i][j];
    }

    // top down
    public int findPaths2(int m, int n, int N, int i, int j) {
        int[][][] dp = new int[N+1][m][n];
        for(int k = 0; k <= N; k++) {
            for(int ii = 0; ii < m; ii++) {
                Arrays.fill(dp[k][ii], -1);
            }
        }
        return dfs(m, n, N, i, j, dp);
    }

    int dfs(int m, int n, int N, int i, int j, int[][][] dp) {
        if(dp[N][i][j] != -1) return dp[N][i][j];
        if(N==0) {
            dp[N][i][j] = 0;
            return 0;
        }
        long left = j == 0 ? 1 : dfs(m, n, N-1, i, j-1, dp);
        long right = j == n-1 ? 1 : dfs(m, n, N-1, i, j+1, dp);
        long up = i == 0 ? 1 : dfs(m, n, N-1, i-1, j, dp);
        long down = i == m-1 ? 1 : dfs(m, n, N-1, i+1, j, dp);
        long count = (left + right + up + down)%1000000007;
        dp[N][i][j] = (int) count;
        return (int) count;
    }
}
