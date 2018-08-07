/**
 * Created by yeqing on 6/9/2017.
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        int maxSize = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0)
                        dp[i][j] = matrix[i][j] - '0';
                    else
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;

                    if (dp[i][j] > maxSize) maxSize = dp[i][j];
                }
            }
        }
        return maxSize*maxSize;
    }
    // version 2, memory optimized
    public int maximalSquare2(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[] dp = new int[n];
        int maxSize = 0, topleft = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int temp = dp[j];
                if (matrix[i][j] == '0') dp[j] = 0;
                else {
                    if (i == 0 || j == 0)
                        dp[j] = matrix[i][j] - '0';
                    else
                        dp[j] = Math.min(Math.min(dp[j], dp[j-1]), topleft) + 1;

                    if (dp[j] > maxSize) maxSize = dp[j];
                }
                topleft = temp;
            }
        }
        return maxSize*maxSize;
    }
    public static void main(String[] args) {

    }
}
