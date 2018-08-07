package com.company;
import java.util.*;
/**
 * Created by yeqing on 5/25/2017.
 */
public class MatrixSpiral {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix[0].length == 0) return null;
        int M = matrix.length, N = matrix[0].length;
        List<Integer> res = new ArrayList<>();
        int left = 0, top = 0, right = N-1, bot = M-1;
        while (true) {
            //move right
            for (int j = left; j <= right; j++)
                res.add(matrix[top][j]);
            top++;
            if (bot < top || left > right) break;

            //move down
            for (int i = top; i <= bot; i++)
                res.add(matrix[i][right]);
            right--;
            if (bot < top || left > right) break;

            //move left
            for (int j = right; j >= left; j--)
                res.add(matrix[bot][j]);
            bot--;
            if (bot < top || left > right) break;

            //move up
            for (int i = bot; i >= top; i--)
                res.add(matrix[i][left]);
            left++;
            if (bot < top || left > right) break;
        }
        return res;
    }

    public int[][] generateMatrix(int n) {
        if (n < 1) return null;
        int[][] matrix = new int[n][n];
        int left = 0, right = n-1, top = 0, bot = n-1, k = 1;
        int N2 = n*n;

        while (k <= N2) {
            for (int j = left; k <= N2 && j <= right; j++)
                matrix[top][j] = k++;
            top++;

            for (int i = top; k <= N2 && i <= bot; i++)
                matrix[i][right] = k++;
            right--;

            for (int j = right; k <= N2 && j >= left; j--)
                matrix[bot][j] = k++;
            bot--;

            for (int i = bot; k <= N2 && i >= top; i--)
                matrix[i][left] = k++;
            left++;
        }
        return matrix;
    }
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3},{4, 5, 6},{7, 8, 9}};
        MatrixSpiral ms = new MatrixSpiral();
        for (int n : ms.spiralOrder(matrix))
            System.out.print(n + " ");
        System.out.println();

        for (int[] row : ms.generateMatrix(3)) {
            for (int cell : row)
                System.out.print(cell + " ");
            System.out.println();
        }
    }
}
