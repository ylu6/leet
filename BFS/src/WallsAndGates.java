import java.util.ArrayDeque;
import java.util.Queue;

public class WallsAndGates {
    /**
     * @param rooms: m x n 2D grid
     * @return: nothing
     */
    // BFS, because we need to add shortest distance of empty room, DFS is not a good approach
    public void wallsAndGates(int[][] rooms) {
        final int M = rooms.length, N = rooms[0].length;
        Queue<int[]> queue = new ArrayDeque<>(); // queue of coordinates {i,j}
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(rooms[i][j] == 0) queue.add(new int[]{i,j});
            }
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int dist = 1;
        while(!queue.isEmpty()) {
            for(int i = 0, size = queue.size(); i < size; i++) {
                int[] front = queue.poll();
                for (int[] dir : dirs) {
                    int r = front[0] + dir[0], c = front[1] + dir[1];
                    if (r < 0 || r >= M || c < 0 || c >= N) continue; // continue if {r,c} out of boundary
                    if (rooms[r][c] == -1 || rooms[r][c] != Integer.MAX_VALUE)  // continue if this room if barrier or visited before
                        continue;
                    rooms[r][c] = dist; // update distance
                    queue.add(new int[]{r,c}); // add this room to the frontier queue
                }
            }
            dist++; // current level completed, increase distance by 1
        }
    }
}
