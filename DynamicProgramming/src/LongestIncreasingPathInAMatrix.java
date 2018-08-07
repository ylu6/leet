/**
 * Created by yeqing on 11/22/2017.
 */
public class LongestIncreasingPathInAMatrix {
    // dfs memoization solution, dp[i][j] records length of longest increasing path, starting from (i,j)
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int maxLen = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                maxLen = Math.max(maxLen, dfs(i, j, matrix, dp));
            }
        }
        for(int[] row : dp) {
            for (int cell : row)
                System.out.print(cell + " ");
            System.out.println();
        }
        return maxLen;
    }
    int dfs(int i, int j, int[][] matrix, int[][] dp) {
        if(dp[i][j] > 0) return dp[i][j]; // (i,j) was visited before, return dp[i][j]

        int len = 1;
        if (i > 0 && matrix[i-1][j] > matrix[i][j])
            len = Math.max(len, 1+dfs(i-1, j, matrix, dp));
        if (i < matrix.length-1 && matrix[i+1][j] > matrix[i][j])
            len = Math.max(len, 1+dfs(i+1, j, matrix, dp));
        if (j > 0 && matrix[i][j-1] > matrix[i][j])
            len = Math.max(len, 1+dfs(i, j-1, matrix, dp));
        if (j < matrix[0].length-1 && matrix[i][j+1] > matrix[i][j])
            len = Math.max(len, 1+dfs(i, j+1, matrix, dp));

        dp[i][j] = len;
        return dp[i][j];
    }

    public static void main(String[] args) {
        int[][] matrix = {{9,9,4},{6,6,8},{2,1,1}};
        LongestIncreasingPathInAMatrix sol = new LongestIncreasingPathInAMatrix();
        System.out.println(sol.longestIncreasingPath(matrix));
    }
}
