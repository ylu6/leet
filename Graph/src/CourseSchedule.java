import java.util.ArrayList;

/**
 * Created by yeqing on 11/20/2017.
 */
public class CourseSchedule {
    ArrayList<Integer>[] adj; //[1,0] : 1<-0
    boolean[] visited;
    boolean[] onStack; // store whether vertex v is on current recursive call stack

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int V = numCourses; // number of vertices of this graph
        adj = (ArrayList<Integer>[])new ArrayList[V]; //[1,0] : 1<-0
        visited = new boolean[V];
        onStack = new boolean[V];

        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        for (int[] edge : prerequisites) {
            adj[edge[1]].add(edge[0]); //build the graph, which is stored as adjacent list
        }

        for (int v = 0; v < V; v++) {
            if (!visited[v]) { // if vertex v is not visited, do dfs search
                if (!dfs(v))
                    return false;
            }
        }
        return true;
    }
    // return false if cycle is detected starting from vertex v
    private boolean dfs(int v) {
        if (onStack[v])
            return false; // if vertex v is on call stack, and was visited again, a cycle is detected
        onStack[v] = true;
        visited[v] = true;
        for (int w : adj[v]){
            if (onStack[w]) return false; // return false if w is already onStack
            else if (!visited[w]) { // not on recursive call stack, check whether w was visited before
                if (!dfs(w)) return false; // if dfs(w) detect a cycle, return false -- break early
            }
        }
        onStack[v] = false;
        return true;
    }
}
