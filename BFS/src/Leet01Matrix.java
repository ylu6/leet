import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by yeqing on 12/1/2017.
 */
public class Leet01Matrix {
    // bfs search starting from 0
    public int[][] updateMatrix(int[][] matrix) {
        int[][] dirs = {{-1,0},{0,-1},{1,0},{0,1}};
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[][]{};
        int m = matrix.length, n = matrix[0].length;
        int[][] dist = new int[m][n];
        Queue<int[]> queue = new ArrayDeque<>();
        // initialize
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == 0) {
                    dist[r][c] = 0;
                    queue.add(new int[]{r,c}); // add coordinates of 0 cells to the queue, as starting points
                }
                else    dist[r][c] = m+n; // since there is at least one 0, the distance cannot be larger than m+n
            }
        }
        // bfs, is a cell was visited earlier, the distance stored on that cell must be smaller than newly calculated value
        // therefore, each cell in the matrix will be visited once, the time complexity is O(N)
        while (!queue.isEmpty()) {
            int[] cell = queue.poll(); // poll one cell from queue head
            for (int[] dir : dirs) {
                int r = cell[0] + dir[0], c = cell[1] + dir[1];
                if (r < 0 || r >= m || c < 0 || c >= n) continue; // out of boundary
                if (dist[r][c] <= dist[cell[0]][cell[1]] + 1) continue; // cell (r,c) was visited earlier
                queue.add(new int[]{r, c});
                dist[r][c] = dist[cell[0]][cell[1]] + 1;
            }
        }

        return dist;
    }
}
