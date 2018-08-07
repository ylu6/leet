import java.util.*;

public class MakingALargeIsland {
    // loop through grid and do dfs
    // use a index to keep track of island
    // 1st island: index 2, 2nd island: index 3, 3rd island: index 4 ...
    // during dfs, change the grid value to its island index
    // use a hashmap to store {island_index: island_area}
    // do second loop for all 0 grids, check left, right, up, down for island area

    public int largestIsland(int[][] grid) {
        Map<Integer, Integer> memo = new HashMap<>();
        int index = 2;
        int[][] dirs = {{-1, 0},{1, 0},{0, -1},{0, 1}};
        // calculate area foreach island, store in HashMap
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid.length; j++) {
                if(grid[i][j] == 1) {
                    memo.put(index, dfs(grid, index, i, j));
                    index++;
                }
            }
        }

        // Now instead of 0 and 1, the grid consists of 0, 2, 3, 4 ....
        int maxA = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 0) {
                    maxA = Math.max(maxA, connect(grid, i, j, memo, dirs));
                }
            }
        }
        return maxA == 0 ? grid.length*grid[0].length : maxA; // in the end, maxA==0 means all numbers in original grid is 1
    }

    int dfs(int[][] grid, int index, int i, int j) {
        int area = 1;
        grid[i][j] = index;
        if(i-1 >= 0 && grid[i-1][j] == 1) area += dfs(grid, index, i-1, j);
        if(i+1 < grid.length && grid[i+1][j] == 1) area += dfs(grid, index, i+1, j);
        if(j-1 >= 0 && grid[i][j-1] == 1) area += dfs(grid, index, i, j-1);
        if(j+1 < grid[0].length && grid[i][j+1] == 1) area += dfs(grid, index, i, j+1);
        return area;
    }

    // [i,j] is 0, try to connect [i,j] to its up, down, left, right neighbors
    // if the index is valid and two of its neighbors are not belong to the same island, connect them
    int connect(int[][] grid, int i, int j, Map<Integer, Integer> memo, int[][] dirs) {
        Set<Integer> indexSet = new HashSet<>();
        int area = 1;
        for(int[] dir : dirs) { // left, right, up, down
            int r = i+dir[0], c = j+dir[1];
            if(r >= 0 && r < grid.length && c >= 0 && c < grid[0].length) { // validate index
                if(grid[r][c] != 0 && !indexSet.contains(grid[r][c])) { // exclude neighbors belong to same island
                    area += memo.get(grid[r][c]);
                    indexSet.add(grid[r][c]);
                }
            }
        }
        return area;
    }

    public static void main(String[] args) {
        int[][] grid = {{0,0,0,0,0,0,0},
                        {0,1,1,1,1,0,0},
                        {0,1,0,0,1,0,0},
                        {1,0,1,0,1,0,0},
                        {0,1,0,0,1,0,0},
                        {0,1,0,0,1,0,0},
                        {0,1,1,1,1,0,0}};

        MakingALargeIsland sol = new MakingALargeIsland();
        System.out.println(sol.largestIsland(grid));
    }
}
