import java.util.Arrays;

public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE); // for first row, it is impossible to come from top, only from left
        dp[0] = 0;

        for(int i = 0; i < m; i++) {
            dp[0] = grid[i][0] + dp[0]; // for first column, only come from top
            for(int j = 1; j < n; j++) {
                dp[j] = grid[i][j] + Math.min(dp[j], dp[j-1]);
            }
        }

        return dp[n-1];
    }
}
