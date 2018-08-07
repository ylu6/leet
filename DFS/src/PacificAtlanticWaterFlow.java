import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeqing on 12/1/2017.
 */
public class PacificAtlanticWaterFlow {
    int[][] dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};

    public List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        boolean[][] atlantic = new boolean[m][n]; // dfs search start from atlantic, atlantic[i][j] will be true if (i,j) is reachable
        boolean[][] pacific = new boolean[m][n]; // dfs search start from pacific, pacific[i][j] will be true if (i,j) is reachable
        for (int row = 0; row < m; row++) {
            dfs(matrix, pacific, row, 0);
            dfs(matrix, atlantic, row, n-1);
        }
        for (int col = 0; col < n; col++) {
            dfs(matrix, pacific, 0, col);
            dfs(matrix, atlantic, m-1, col);
        }
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (atlantic[i][j] && pacific[i][j])
                    res.add(new int[]{i,j});
            }
        }
        return res;
    }

    void dfs(int[][] matrix, boolean[][] visited, int i, int j) {
        if(visited[i][j]) return; // visited earlier
        visited[i][j] = true;

        for (int[] dir : dirs) {
            int r = i+dir[0], c = j+dir[1];
            if (r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length) continue;
            if (matrix[r][c] < matrix[i][j]) continue;
            dfs(matrix, visited, r, c);
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        PacificAtlanticWaterFlow sol = new PacificAtlanticWaterFlow();
        for (int[] cell : sol.pacificAtlantic(matrix)) {
            System.out.println(cell[0] + ", " + cell[1]);
        }
    }
}
