import java.util.*;

/**
 * Created by yeqing on 11/20/2017.
 */
public class CourseScheduleII {
    ArrayList<Integer>[] adj; // adjacent list which stores the graph
    boolean[] visited; // record whether a vertex has been visited
    boolean[] onStack; // record whether a vertex is on the DFS recursive call stack
    int V; // number of vertices in the graph
    Deque<Integer> stack = new ArrayDeque<>();

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        V = numCourses;
        adj = (ArrayList<Integer>[]) new ArrayList[V];
        visited = new boolean[V];
        onStack = new boolean[V];
        for (int i = 0; i < V; i++) adj[i] = new ArrayList<Integer>();
        for (int[] edge : prerequisites) adj[edge[1]].add(edge[0]);
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (!dfs(i)) return new int[0]; // cycle detected, return empty array
            }
        }
        int[] res = new int[V];
        for (int i = 0; i < V; i++)
            res[i] = stack.poll();
        return res;
    }
    // return true if no cycle was detected
    private boolean dfs(int v){
        if (onStack[v]) return false; // v is already on the call stack, cycle detected
        onStack[v] = true;
        visited[v] = true;

        for (int w : adj[v]) {
            if (onStack[w]) return false;
            else if (!visited[w]) {
                if (!dfs(w)) return false; // cycle detected
            }
        }
        stack.addFirst(v); // post-order traversal
        onStack[v] = false; // remove v from call stack
        return true; // no cycle detected, return true
    }

    public static void main(String[] args) {
        int[][] pre = {{1,0},{2,1}};
        CourseScheduleII sol = new CourseScheduleII();
        for (int n : sol.findOrder(3, pre))
            System.out.print(n + ", ");

        List<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[10];
        adj[0] = new ArrayList<Integer>();
        adj[0].add(1);
        adj[0].addAll(Arrays.asList(2,3,4));
        for(int i : adj[0])
            System.out.println(i);
    }
}
