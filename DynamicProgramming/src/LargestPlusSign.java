import java.util.Arrays;

/**
 * Created by yeqing on 1/15/2018.
 */
public class LargestPlusSign {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int[][] up = new int[N][N];
        int[][] down = new int[N][N];
        int[][] left = new int[N][N];
        int[][] right = new int[N][N];
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++)
            Arrays.fill(matrix[i], 1);
        for (int[] mine : mines)
            matrix[mine[0]][mine[1]] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 0) continue;
                up[i][j] = i==0||matrix[i-1][j]==0 ? 0 : 1+up[i-1][j];
                left[i][j] = j==0||matrix[i][j-1]==0 ? 0 : 1+left[i][j-1];
            }
        }
        int res = 0;
        for (int i = N-1; i >= 0; i--) {
            for (int j = N-1; j >= 0; j--) {
                if (matrix[i][j] == 0) continue;
                down[i][j] = i==N-1||matrix[i+1][j]==0 ? 0 : 1+down[i+1][j];
                right[i][j] = j==N-1||matrix[i][j+1]==0 ? 0 : 1+right[i][j+1];
                int temp = Math.min(Math.min(up[i][j],left[i][j]), Math.min(down[i][j], right[i][j]));
                res = Math.max(res, 1+temp);
            }
        }
        return res;
    }
}
