public class RangeSumQuery2DMutable {
    int[][] bit;
    int[][] mat;
    public RangeSumQuery2DMutable(int[][] matrix) {
        bit = new int[matrix.length+1][matrix[0].length+1];
        mat = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++) { // initialize the 2D bit and mat
            for(int j = 0; j < matrix[0].length; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        int delta = val - mat[row][col];
        for(int r = row+1; r < bit.length; r += r&(-r)) {
            for(int c = col+1; c < bit[0].length; c += c&(-c)) {
                bit[r][c] += delta;
            }
        }
        mat[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) { // row1 <= row2, col1<=col2
        return getSum(row2,col2) - getSum(row2, col1-1) -
                getSum(row1-1, col2) + getSum(row1-1, col1-1);
    }

    int getSum(int row, int col) {
        int sum = 0;
        for(int r = row + 1; r > 0; r -= r&(-r)) {
            for(int c = col + 1; c > 0; c -= c&(-c)) {
                sum += bit[r][c];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };

        RangeSumQuery2DMutable sol = new RangeSumQuery2DMutable(matrix);
        System.out.println(sol.getSum(0,1));
        System.out.println(sol.sumRegion(2, 1, 4, 3));
        sol.update(3, 2, 2);
        System.out.println(sol.sumRegion(2, 1, 4, 3));
    }
}
