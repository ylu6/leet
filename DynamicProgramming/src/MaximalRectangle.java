import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by yeqing on 6/9/2017.
 */
// use the same idea in maximum area in histogram, use a stack to keep indices
// use dp to create a histogram for each row, find the largest rectangle in each histogram
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int maxArea = 0;
        int m = matrix.length, n = matrix[0].length;
        int[] heights = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < m; i++) { // each row of the matrix
            // to get heights of the histogram at (i,j), including the element (i,j)
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') heights[j]++;
                else heights[j] = 0;
            }
            printArray(heights);
            // use stack to get max rect in histogram
            int j = 0;
            while (j < n) {
                if (stack.isEmpty() || heights[j] >= heights[stack.peekFirst()])
                    stack.addFirst(j++);
                else {
                    int h = heights[stack.pollFirst()];
                    int w = stack.isEmpty() ? j : j - stack.peekFirst() - 1;
                    maxArea = Math.max(maxArea, h*w);
                }
            }
            while (!stack.isEmpty()) {
                int h = heights[stack.pollFirst()];
                int w = stack.isEmpty() ? j : j - stack.peekFirst() - 1;
                maxArea = Math.max(maxArea, h*w);
            }
        }
        return maxArea;
    }

    void printArray(int[] nums) {
        for (int n : nums)
            System.out.print(n + " ");
        System.out.println();
    }

    int maxAreaFromHist(int[] heights) {
        int j = 0, m = heights.length, maxArea = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        while (j < m) {
            if (stack.isEmpty() || heights[j] >= heights[stack.peekFirst()])
                stack.addFirst(j++);
            else {
                int h = heights[stack.pollFirst()];
                int w = stack.isEmpty() ? j : j - stack.peekFirst() - 1;
                maxArea = Math.max(maxArea, h*w);
            }
        }
        while (!stack.isEmpty()) {
            int h = heights[stack.pollFirst()];
            int w = stack.isEmpty() ? j : j - stack.peekFirst() - 1;
            maxArea = Math.max(maxArea, h*w);
        }
        return maxArea;
    }
    public static void main(String[] args) {
        char[][] matrix = {{'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};
        int[] heights = {3,1,3,2,2};
        MaximalRectangle sol = new MaximalRectangle();
//        System.out.println(sol.maxAreaFromHist(heights));
        System.out.println(sol.maximalRectangle(matrix));
    }
}
