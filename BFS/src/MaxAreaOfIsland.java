import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yeqing on 10/31/2017.
 */
public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int res = 0, m = grid.length, n = grid[0].length;

        boolean[][] seen = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !seen[i][j]) {
                    res = Math.max(res, bfs(i, j, grid, seen));
                }
            }
        }
        return res;
    }

    int bfs(int i, int j, int[][] grid, boolean[][] seen) {
        Queue<Integer> frontier = new LinkedList<>();
        frontier.add(i); frontier.add(j);
        int area = 0;
        while (!frontier.isEmpty()) {
            int x = frontier.poll();
            int y = frontier.poll();
            if (seen[x][y]) continue;;
            seen[x][y] = true;
            area++;
            if (x>0 && grid[x-1][y]==1) {
                frontier.add(x-1); frontier.add(y);
            }
            if (x<grid.length-1 && grid[x+1][y]==1) {
                frontier.add(x+1); frontier.add((y));
            }
            if (y>0 && grid[x][y-1]==1) {
                frontier.add(x); frontier.add(y-1);
            }
            if (y<grid[0].length-1 && grid[x][y+1]==1) {
                frontier.add(x); frontier.add(y+1);
            }
        }
        return area;
    }

    public static void main(String[] args) {
        int[][] grid = {{1,0,0},{0,1,1},{0,1,1}};
        boolean[][] seen = new boolean[3][3];
        MaxAreaOfIsland sol = new MaxAreaOfIsland();
        System.out.println(sol.bfs(0,0, grid, seen));
        System.out.println(sol.maxAreaOfIsland(grid));
    }
}
