import java.util.Arrays;

/**
 * Created by yeqing on 11/29/2017.
 */
public class KnightProbabilityInChessboard {
    //dp[ki][ri][ci]: after ki move, starting from (ri,ci), the possibility of staying on board
    public double knightProbability(int N, int K, int r, int c) {
        double[][][] dp = new double[K+1][N][N];
        int[][] dirs = {{1,2},{2,1},{-1,2},{-2,1},{-2,-1},{-1,-2},{1,-2},{2,-1}}; // 8 directions to move
        for (int i = 0; i < N; i++) Arrays.fill(dp[0][i], 1.0);


        for (int ki = 1; ki <= K; ki++) {
            for (int ri = 0; ri < N; ri++) {
                for (int ci = 0; ci < N; ci++) {
                    for (int[] dir : dirs) {
                        int row = ri + dir[0];
                        int col = ci + dir[1];
                        double p = (row>=0 && row<N && col>=0 && col<N) ? dp[ki-1][row][col] : 0.0;
                        dp[ki][ri][ci] += 0.125*p;
                    }
                }
            }
        }
        return dp[K][r][c];
    }
}
