public class BombEnemy {
    public int maxKilledEnemies(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length ==0) return 0;
        int m = grid.length, n = grid[0].length;
        int[][] dp1 = new int[m][n];
        int[][] dp2 = new int[m][n];
        int[][] dp3 = new int[m][n];
        int[][] dp4 = new int[m][n];

        int count;
        for(int r = 0; r < m; r++) {
            count = 0;
            for(int c = 0; c < n; c++) {
                count = updateCount(grid, dp1, r, c, count);
            }
            count = 0;
            for(int c = n-1; c >= 0; c--) {
                count = updateCount(grid, dp2, r, c, count);
            }
        }

        for(int c = 0; c < n; c++) {
            count = 0;
            for(int r = 0; r < m; r++) {
                count = updateCount(grid, dp3, r, c, count);
            }
            count = 0;
            for(int r = m-1; r >= 0; r--) {
                count = updateCount(grid, dp4, r, c, count);
            }
        }

        int res = 0;
        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                res = Math.max(res, dp1[r][c]+dp2[r][c]+dp3[r][c]+dp4[r][c]);
            }
        }
        return res;
    }

    int updateCount(char[][] grid, int[][] dp, int r, int c, int count) {
        if(grid[r][c] == '0') dp[r][c] = count;
        if(grid[r][c] == 'W') count = 0;
        if(grid[r][c] == 'E') count++;
        return count;
    }

    public static void main(String[] args) {

    }
}
