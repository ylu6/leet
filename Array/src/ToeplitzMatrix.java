public class ToeplitzMatrix {
    public boolean isToeplitzMatrix(int[][] matrix) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0) return false;
        int rows = matrix.length, cols = matrix[0].length;

        for (int k = -rows+1; k < cols; k++) {
            int i = k<0 ? -k : 0;
            int id = matrix[i][i+k];
            for (i=i+1; i < rows && i+k < cols; i++) {
                int j = i+k;
                if(matrix[i][j] != id) return false;
            }
        }
        return true;
    }
}
