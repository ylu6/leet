import java.util.*;

/**
 * Created by yeqing on 11/21/2017.
 */
// 实际问题: 从原点出发，画一条线包含所有segments
// if one route hit dead end and cannot go any where, this route must be the last segment of the itinerary
// there can be multiple cycles start from JFK, choose by lexical order
public class ReconstructItinerary {
    public List<String> findItinerary(String[][] tickets) {
        // graph is stored as adjacent list, the adjacent list of a vertex is stored in PriorityQueue
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (String[] ticket : tickets) {
            graph.putIfAbsent(ticket[0], new PriorityQueue<>());
            graph.get(ticket[0]).add(ticket[1]); // add ticket[1] to the adjacent list of ticket[0]
        }
        List<String> res = new LinkedList<>();
        dfs("JFK", graph, res);
        return res;
    }
    // post-order dfs
    private void dfs(String v, Map<String, PriorityQueue<String>> graph, List<String> res) {
        // an airport can be visited multiple times, so no need to check whether it is visited
        while (graph.get(v) != null && !graph.get(v).isEmpty()) {
            String nextAirport  = graph.get(v).poll();
            dfs(nextAirport, graph, res);
        }
        res.add(0, v);
    }

    public static void main(String[] args) {
        String[][] tickets = {{"JFK", "KUL"},{"JFK", "NRT"},{"NRT","JFK"}};
        ReconstructItinerary sol = new ReconstructItinerary();
        System.out.println(sol.findItinerary(tickets).toString());
    }
}
