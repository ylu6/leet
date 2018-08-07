import java.util.*;

/**
 * Created by yeqing on 12/12/2017.
 */
public class NetworkDelayTime {
    // Dijkstra's shortest path algm, time complexity O((V+E)logE)
    public int networkDelayTime(int[][] times, int N, int K) {
        int[] distTo = new int[N+1]; // distTo[w]: minimum distance from node K to w
        boolean[] visited = new boolean[N+1];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[K] = 0; // starting from K, therefore distTo[K] is 0
        Map<Integer, List<int[]>> adj = new HashMap<>(); // graph represented by adjacent list, stored in HashMap
        // construct adjacent list from input edges
        for (int[] time : times) {
            adj.putIfAbsent(time[0], new ArrayList<int[]>());
            adj.get(time[0]).add(new int[]{time[1], time[2]});
        }
        // minPQ stores {nodeID, distToThisNode}, ordered by distTo
        Queue<int[]> minPQ = new PriorityQueue<>((a,b)->a[1]-b[1]);
        minPQ.add(new int[]{K, 0});

        while(!minPQ.isEmpty()) {
            int[] node = minPQ.poll();
            if (visited[node[0]]) continue; // this vertex was visited before, therefore shortest path was found already
            distTo[node[0]] = node[1];
            visited[node[0]] = true; // mark current node as visited
            relax(adj, distTo, visited, minPQ, node[0]); // relax all edges stored in the adjacent list of current node
        }
        int res = 0;
        for (int i = 1; i <= N; i++) {
            res = Math.max(res, distTo[i]);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    // relax edges starting from node v
    private void relax(Map<Integer, List<int[]>> adj, int[] distTo, boolean[] visited, Queue<int[]> minPQ, int v) {
        if (adj.get(v) != null) {
            for (int[] n : adj.get(v)) {
                int w = n[0], weight = n[1];
                if (visited[w]) continue;
                if (distTo[v] + weight < distTo[w]) {
                    minPQ.add(new int[]{w, distTo[v] + weight});
                }
            }
        }
    }

    public static void main(String[] args) {
        NetworkDelayTime sol = new NetworkDelayTime();
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        System.out.println(sol.networkDelayTime(times, 4, 2));
    }
}
