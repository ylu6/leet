/**
 * Created by yeqing on 8/31/2017.
 */
public class SearchA2DMatrixII {
    // every row and column is sorted, from left to right and top to down respectively
    // start from top right corner (TR), if target smaller than TR, all the element on the column are larger than target
    // this whole column can be excluded and search search range can be reduced by one column
    // similarly, if target larger than TR, every element on the row is smaller than target, search range reduced by one row
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int row = 0, col = n-1;
        while(row < m && col >= 0) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] < target) row++;
            else col--;
        }
        return false;
    }
}
