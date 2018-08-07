import java.util.*;

public class GraphNodeParents {
    Map<Integer, List<Integer>> adj;

    List<List<Integer>> nodeHasZeroOrOneParent(int[][] input) {
        adj = new HashMap<>(); //adj lists of the parents map
        buildGraph(input, adj);
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>()); // first list stores nodes with zero parent
        res.add(new ArrayList<>()); // 2nd list stores nodes with one parent

        for(Integer node : adj.keySet()) {
            if(adj.get(node).size() == 0) res.get(0).add(node);
            if(adj.get(node).size() == 1) res.get(1).add(node);
        }
        return res;
    }
    // v1, a node is ancestor of itself, e.g. 5->6->7, commonAncestor 5,7 return false
    boolean commonAncestor1(int v1, int v2) {
        Set<Integer> ancestors = new HashSet<>();
        bfs1(v1, ancestors); // get all ancestors of node v1

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();
        q.add(v2);  // do bfs starting from v2

        while(!q.isEmpty()) {
            int front = q.poll();
            if(ancestors.contains(front)) return true;
            visited.add(front);
            for(int w : adj.get(front)) {
                if(!visited.contains(w)) q.add(w);
            }
        }
        return false;
    }
    // v2, a node is not ancestor of itself,  e.g. 5->6->7, commonAncestor 5,7 return false
    boolean commonAncestor2(int v1, int v2) {
        List<Integer> ancestors1 = new ArrayList<>();
        bfs2(v1, ancestors1);
        List<Integer> ancestors2 = new ArrayList<>();
        bfs2(v2, ancestors2);
        Set<Integer> set = new HashSet<>(ancestors2);
        for(int v : ancestors1) {
            if(set.contains(v)) return true;
        }
        return false;
    }

    // find all ancestors of node v using bfs
    void bfs1(int v, Set<Integer> ancestors) {
        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        q.add(v);

        while(!q.isEmpty()) {
            int front = q.poll();
            visited.add(front);
            ancestors.add(front);
            for(int w : adj.get(front)) {
                if(!visited.contains(w)) {
                    q.add(w);
                }
            }
        }
    }

    void bfs2(int v, List<Integer> ancestors) {
        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        q.add(v);

        while(!q.isEmpty()) {
            int front = q.poll();
            visited.add(front);
            for(int w : adj.get(front)) {
                if(!visited.contains(w)) {
                    ancestors.add(w);
                    q.add(w);
                }
            }
        }
    }
    void buildGraph(int[][] input, Map<Integer, List<Integer>> adj) {
        for(int[] edge : input) {
            // Note!! Add both nodes to the graph, assign edge[0] as the parent of edge[1]
            adj.putIfAbsent(edge[0], new ArrayList<>());
            adj.putIfAbsent(edge[1], new ArrayList<>());
            adj.get(edge[1]).add(edge[0]); // edge[0] is the parent of edge[1]
        }
    }

    int highestAncestor(int v) {
        List<Integer> ancestors = new ArrayList<>();
        bfs2(v, ancestors);
        return ancestors.get(ancestors.size()-1);
    }

    public static void main(String[] args) {
        int[][] input = {{1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5}, {4, 8}, {8, 9}};
        GraphNodeParents sol = new GraphNodeParents();
        System.out.println(sol.nodeHasZeroOrOneParent(input));
        System.out.println("definition 1: a node is ancestor of itself");
        System.out.println(sol.commonAncestor1(3,8));
        System.out.println(sol.commonAncestor1(5,8));
        System.out.println(sol.commonAncestor1(6,8));
        System.out.println(sol.commonAncestor1(6,4));

        System.out.println("definition 2: a node is not ancestor of itself");
        System.out.println(sol.commonAncestor2(3,8));
        System.out.println(sol.commonAncestor2(5,8));
        System.out.println(sol.commonAncestor2(6,8));
        System.out.println(sol.commonAncestor2(6,4));

        System.out.println("highest common ancestor:");
        System.out.println(sol.highestAncestor(3));
        System.out.println(sol.highestAncestor(6));
        System.out.println(sol.highestAncestor(5));
        System.out.println(sol.highestAncestor(7));
        System.out.println(sol.highestAncestor(9));
    }
}

/*
1 没有爸爸 和只有一个爸爸的 hashmap
2 找两个人有没有一样的爸爸 bfs/dfs
3 找一个最远的爸爸 bfs

1  2  4
\ /  / \
 3  5   8
 \ / \   \
 6   7    9
输入是int[][] input, input[0]是input[1] 的parent，比如 {{1,4}, {1,5}, {2,5}, {3,6}, {6,7}}会形成上面的图
第一问是只有0个parents和只有1个parent的节点
第二问是 两个指定的点有没有公共祖先
3, 6 => true
5, 7 => false
4, 5 => true
4, 2 => false
第三问是就一个点的最远祖先，如果有好几个就只需要输出一个就好，举个栗子，这里5的最远祖先可以是1或者2，输出任意一个就可以

时间很紧，一个钟头基本上说说oa的思路就过去一刻钟了，然后昨晚还要分析时间复杂度和空间辅助度
 */
