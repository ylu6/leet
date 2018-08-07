import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Created by yeqing on 11/21/2017.
 */
public class CloneGraph {
    class UndirectedGraphNode {
       int label;
       List<UndirectedGraphNode> neighbors;
       UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    }

    // dfs approach
    Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null;
        if(map.containsKey(node)) return map.get(node);

        // preorder traversal
        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        map.put(node, copy);
        for(UndirectedGraphNode nxt : node.neighbors) {
            copy.neighbors.add(cloneGraph(nxt));
        }

        return copy;
    }

    // bfs approach
    public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
        if(node == null) return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        Queue<UndirectedGraphNode> queue = new ArrayDeque<>();
        queue.add(node);
        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        map.put(node, copy);

        while(!queue.isEmpty()) {
            UndirectedGraphNode cur = queue.poll();
            for(UndirectedGraphNode nxt : cur.neighbors) {
                if(!map.containsKey(nxt)) {
                    copy = new UndirectedGraphNode(nxt.label);
                    map.put(nxt, copy);
                    queue.add(nxt); // add nxt, not copy !!!
                }
                map.get(cur).neighbors.add(map.get(nxt));
            }
        }
        return map.get(node);
    }
}
