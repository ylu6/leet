import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class SwimInRisingWater {
    // similar to Dijkstra's shortest distance algm
    public int swimInWater(int[][] grid) {
        int M = grid.length, N = grid[0].length;
        int[][] maxHTo = new int[M][N];
        for(int r = 0; r < M; r++)  Arrays.fill(maxHTo[r], Integer.MAX_VALUE);
        maxHTo[0][0] = grid[0][0];
        Queue<int[]> minPQ = new PriorityQueue<>((a,b)->a[2]-b[2]); // minPQ
        minPQ.add(new int[]{0,0,grid[0][0]});
        boolean[][] visited = new boolean[M][N];

        while(!minPQ.isEmpty()) {
            int[] curr = minPQ.poll();
            if(visited[curr[0]][curr[1]]) continue;
            visited[curr[0]][curr[1]] = true;
            if(relax(minPQ,curr[0],curr[1],maxHTo, visited, grid))
                return maxHTo[M-1][N-1]-grid[0][0];
        }

        return maxHTo[M-1][N-1]-grid[0][0];
    }
    boolean relax(Queue<int[]> minPQ, int i, int j, int[][] maxHTo, boolean[][] visited, int[][] grid){
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
        for(int[] dir : dirs) {
            int r = i+dir[0], c = j+dir[1];
            if(r<0 || c<0 || r>=maxHTo.length || c>=maxHTo[0].length) continue;
            if(visited[r][c]) continue;
            int h = Math.max(maxHTo[i][j], grid[r][c]);
            if(h < maxHTo[r][c]) {
                maxHTo[r][c] = h;
                minPQ.add(new int[]{r, c, h});
            }
            if(r==grid.length && c==grid[0].length) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] grid = {{0,2},{1,3}};
        SwimInRisingWater sol = new SwimInRisingWater();
        System.out.println(sol.swimInWater(grid));
    }

    void printMatrix(int[][] mat){
        for(int[] row : mat) {
            for(int cell : row) System.out.print(cell);
            System.out.println();
        }
    }
}
