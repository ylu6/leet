import java.util.*;

// DAG problem, topological sort
public class TaskByLevel {
    List<String> taskByLevel(String[][] input) {
        Map<String, List<String>> adj = new HashMap<>();
        for(String[] edge : input) { // build graph
            adj.putIfAbsent(edge[0], new ArrayList<>());
            adj.putIfAbsent(edge[1], new ArrayList<>());
            adj.get(edge[0]).add(edge[1]);
        }

        Set<String> visited = new HashSet<>();
        Deque<String> stack = new ArrayDeque<>();
        for(String cur : adj.keySet()) {
            if(!visited.contains(cur))
                postorder(adj, stack, cur, visited);
        }
        List<String> res = new ArrayList<>();
        while(!stack.isEmpty()) {
            res.add(stack.pollFirst());
        }
        return res;
    }

    void postorder(Map<String, List<String>> adj, Deque<String> stack, String cur, Set<String> visited){
        if(visited.contains(cur)) return;
        visited.add(cur);

        for(String nxt : adj.get(cur)) {
            if(!visited.contains(nxt)) {
                postorder(adj, stack, nxt, visited);
            }
        }
        stack.addFirst(cur);
    }

    public static void main(String[] args) {
        String[][] input = {
                {"cook", "eat"},   // do "cook" before "eat"
                {"study", "eat"},
                {"sleep", "study"}};

        TaskByLevel sol = new TaskByLevel();
        System.out.println(sol.taskByLevel(input));
    }
}

/*
input = {
{"cook", "eat"},   // do "cook" before "eat"
{"study", "eat"},
{"sleep", "study"}}

output (steps of a workflow):
{{"sleep", "cook"},.
{"study"},
{"eat"}}
 */
