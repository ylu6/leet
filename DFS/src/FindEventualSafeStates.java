import java.util.ArrayList;
import java.util.List;

public class FindEventualSafeStates {
    // there exists a natural number K so that for any choice of where to walk,
    // we must have stopped at a terminal node in less than K steps.
    // so if a node is on a cycle, it is unsafe, otherwise its safe
    public List<Integer> eventualSafeNodes(int[][] graph) {
        boolean[] visited = new boolean[graph.length];
        boolean[] onStack = new boolean[graph.length];
        boolean[] isSafe = new boolean[graph.length]; //
        List<Integer> res = new ArrayList<>();

        for(int i = 0; i < graph.length; i++) {
            if(dfs(visited, onStack, i, graph, isSafe))
                res.add(i);
        }
        return res;
    }

    boolean dfs(boolean[] visited, boolean[] onStack, int node, int[][] graph, boolean[] isSafe) {
        if(onStack[node]) return false; // find a loop
        if(visited[node]) return isSafe[node]; // already visited before, return the cached value

        visited[node] = true; // add current node into call stack
        onStack[node] = true; // mark current node as visited

        for(int next : graph[node]) {
            if(!dfs(visited, onStack, next, graph, isSafe)) {
                isSafe[node] = false;
                return false;
            }
        }
        isSafe[node] = true; // add result into cache
        onStack[node] = false; // remove current node from call stack
        return true;
    }
}
