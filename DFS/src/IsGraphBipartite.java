public class IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        int[] visited = new int[graph.length]; // 0:not visited, -1:set A, 1:set B
        for(int i = 0; i < graph.length; i++) {
            if(visited[i] == 0) {
                if(!dfs(graph, visited, i,-1)) return false; // once found an invalid node, return false
            }
        }
        return true; // all nodes are valid, return true
    }

    boolean dfs(int[][] graph, int[] visited, int i, int nextCode) {
        if(visited[i] == 0) visited[i] = nextCode; // i has not been visited before
        else if(visited[i] != nextCode) return false; // find a conflict, return false and stop searching
        else return true; // i has been visited before and function not returned, therefore node i is valid

        for(int j : graph[i]) {
            if(!dfs(graph, visited, j, -1*nextCode)) return false;
        }
        return true;
    }
}
