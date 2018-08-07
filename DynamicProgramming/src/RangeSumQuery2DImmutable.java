/**
 * Created by yeqing on 8/11/2017.
 */
public class RangeSumQuery2DImmutable {
    int[][] sum;
    public RangeSumQuery2DImmutable(int[][] matrix) {
        sum = new int[matrix.length+1][matrix[0].length+1];
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                sum[i][j] = sum[i][j-1]-sum[i-1][j-1]+sum[i-1][j]+matrix[i-1][j-1];
            }
        }
        printMatrix(sum);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum[row2+1][col2+1] - sum[row2+1][col1] - sum[row1][col2+1] + sum[row1][col1];
    }

    void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int cell : row)
                System.out.print(cell + "  ");
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int[][] matrix = {{3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};
        RangeSumQuery2DImmutable sol = new RangeSumQuery2DImmutable(matrix);
        System.out.println(sol.sumRegion(2,1,4,3));
    }
}
