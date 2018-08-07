import java.util.*;

public class AllPathsFromSourceToTarget {
    // DFS, travel all possible paths starting from 0
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(path, res, graph, 0);
        return res;
    }

    private void dfs(List<Integer> path, List<List<Integer>> res, int[][] graph, int src) {
        path.add(src);
        if(src == graph.length-1)
            res.add(new ArrayList<>(path));

        for(int next : graph[src])
            dfs(path, res, graph, next);

        path.remove(path.size()-1);
    }

    // v2, DFS, but use memoization, each node will be visited exactly once
    public List<List<Integer>> allPathsSourceTarget2(int[][] graph) {
        int N = graph.length;
        Map<Integer, List<List<Integer>>> memo = new HashMap<>();
        dfs2(memo, graph, 0);
        return memo.get(0);
    }

    // get all paths to node N-1 starting from node src
    private List<List<Integer>> dfs2(Map<Integer, List<List<Integer>>> memo, int[][] graph, int src) {
        if(memo.containsKey(src)) return memo.get(src);

        List<List<Integer>> res = new ArrayList<>();
        if(src == graph.length-1) {
            LinkedList<Integer> newPath = new LinkedList<>();
            newPath.add(src);
            res.add(newPath);
        } else {
            for(int next : graph[src]) {
                for (List<Integer> path : dfs2(memo, graph, next)) {
                    LinkedList<Integer> newPath = new LinkedList<>(path);
                    newPath.addFirst(src);
                    res.add(newPath);
                }
            }
        }
        memo.put(src, res);
        return res;
    }
}
