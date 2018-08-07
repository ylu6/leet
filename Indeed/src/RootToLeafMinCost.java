import java.util.ArrayList;
import java.util.List;

public class RootToLeafMinCost {
    int minCost = Integer.MAX_VALUE;
    List<Edge> res = new ArrayList<>();

    // find the minCost, dfs
    public int rootToLeafMinCost(TreeNode root){
        dfs(root, 0);
        return minCost;
    }

    void dfs(TreeNode root, int cost){
        if(root == null) return;
        if(root.edges.size() == 0) minCost = Math.min(minCost, cost); // find a leaf node, update minCost

        for(Edge edge : root.edges) {
            TreeNode nxt = edge.endNode;
            dfs(nxt, cost + edge.cost);
        }
    }

    // find the min path, backtracking problem
    public List<Edge> rootToLeafMinPath(TreeNode root) {
        dfs2(root,0, new ArrayList<Edge>());
        return res;
    }

    void dfs2(TreeNode root, int cost, List<Edge> tempList) {
        if(root == null) return;

        if(root.edges.size() == 0) { // leaf node was found
            if(cost < minCost) {
                minCost = cost;
                res = new ArrayList<>(tempList);
            }
        }

        for(Edge edge : root.edges) {
            TreeNode nxt = edge.endNode;
            tempList.add(edge);
            dfs2(nxt, cost + edge.cost, tempList);
            if(tempList.size() > 0) tempList.remove(tempList.size()-1);
        }
    }

    public static void main(String[] args) {
        RootToLeafMinCost sol = new RootToLeafMinCost();
        /*
         *       n1
         *   e1 /  \ e3
         *     n2   n3
         * e2 /
         *   n4
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
        n1.edges.add(e1);
        n1.edges.add(e3);
        n2.edges.add(e2);
//        n2.edges.add(e4);

        List<Edge> path = sol.rootToLeafMinPath(n1);
        for(Edge edge : path) {
            System.out.print(edge.cost + " ");
        }
    }

}


class TreeNode{
    List<Edge> edges;
    public TreeNode(){
        edges = new ArrayList<>();
    }
}

class Edge {
    TreeNode endNode;
    int cost;
    public Edge(TreeNode node, int cost){
        endNode = node;
        this.cost = cost;
    }
}
