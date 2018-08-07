package com.company;

/**
 * Created by yeqing on 6/27/2017.
 */
public class DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return null;
        int m = matrix.length;
        int n = matrix[0].length;
        int idx = 0;
        int[] res = new int[m*n];

        for (int a = 0; a < m+n-1; a++) {
            if (a%2 == 0) {
                for (int row = Math.min(a, m-1); row >= Math.max(0, a+1-n); row--)
                    res[idx++] = matrix[row][a-row];
            }
            else {
                for (int row = Math.max(0, a+1-n); row <= Math.min(a, m-1); row++)
                    res[idx++] = matrix[row][a-row];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        DiagonalTraverse sol = new DiagonalTraverse();
        for (int[] row : matrix) {
            for (int n : row)
                System.out.print(n + " ");
            System.out.println();
        }
        for (int n : sol.findDiagonalOrder(matrix))
            System.out.print(n + " ");
    }
}
