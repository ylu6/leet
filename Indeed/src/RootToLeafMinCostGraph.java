import java.util.*;

// instead of tree, this time it is a DAG
// Tree: each node at most has one parent
// Graph: node can have more than one parents

public class RootToLeafMinCostGraph {

    Map<TreeNode, Integer> distTo = new HashMap<>();
    Map<TreeNode, Edge> edgeTo = new HashMap<>();
    Map<TreeNode, TreeNode> comeFrom = new HashMap<>();

    PriorityQueue<TreeNode> minPQ = new PriorityQueue<TreeNode>(
            (a,b)->Integer.compare(distTo.get(a), distTo.get(b))
    ); // node will only be added into the minPQ after they are relaxed, when we poll minPQ, the node must exist in the distTo map
    int minCost = Integer.MAX_VALUE;
    TreeNode minLeaf = null;

    List<Edge> rootToLeafMinPath(TreeNode root){
        distTo.put(root, 0);
        minPQ.add(root);
        while(!minPQ.isEmpty()) {
            TreeNode v = minPQ.poll();
            relax(v);
        }
        LinkedList<Edge> res = new LinkedList<>();
        TreeNode cur = minLeaf;
        while(comeFrom.containsKey(cur)) {
            res.addFirst(edgeTo.get(cur));
            cur = comeFrom.get(cur);
        }
        return res;
    }

    // starting from node v, relax all nodes w connected to v by v.edges
    void relax(TreeNode v){
        for(Edge edge : v.edges) {
            TreeNode w = edge.endNode;
            int d = distTo.get(v) + edge.cost;
            if(d < distTo.getOrDefault(w, Integer.MAX_VALUE)) { // find a shorter path, update
                distTo.put(w, d); // update dist to w
                // update the minPQ by remove w first, then add it back
                minPQ.remove(w); // remove from minPQ, time complexity is O(N), therefore this algm is O(EVlogV),
                                // if use a index priority queue, remove can be O(1), then dijkstra's algm is O(ElogV)
                minPQ.add(w);
                edgeTo.put(w, edge); // update edgeTo and comeFrom, to trace the shortest path
                comeFrom.put(w, v);

                if(w.edges.size() == 0) { // find a leaf node
                    if(d < minCost) { // update, if distance of root to current leaf is less than minCost
                        minCost = d;
                        minLeaf = w;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        /*
         *       n1
         *   e1 /   \ e3
         *     n2    n3
         * e2 /  \e6  \ e4
         *   n4    -  n5
         *
         * */
        TreeNode n1 = new TreeNode();
        TreeNode n2 = new TreeNode();
        TreeNode n3 = new TreeNode();
        TreeNode n4 = new TreeNode();
        TreeNode n5 = new TreeNode();
        Edge e1 = new Edge(n2,1);
        Edge e2 = new Edge(n4,2);
        Edge e3 = new Edge(n3,5);
        Edge e4 = new Edge(n5, 1);
        Edge e6 = new Edge(n5, 1);
        n1.edges.add(e1);
        n1.edges.add(e3);
        n2.edges.add(e2);
        n3.edges.add(e4);
        n2.edges.add(e6);
        RootToLeafMinCostGraph sol = new RootToLeafMinCostGraph();
        List<Edge> path = sol.rootToLeafMinPath(n1);
        for(Edge edge : path) {
            System.out.print(edge.cost + " ");
        }
    }
}
