import java.util.*;

public class GitCommit {
    // given a git node, find all its ancestors
    public List<GitNode> findAllCommits(GitNode node){
        Set<GitNode> visited = new HashSet<>();
        Queue<GitNode> queue = new ArrayDeque<>();
        queue.add(node);
        List<GitNode> res = new ArrayList<>();

        while(!queue.isEmpty()) {
            // level order BFS
            for(int i = 0, size = queue.size(); i < size; i++) {
                GitNode cur = queue.poll();

                for(GitNode nxt : cur.parents) {
                    if(!visited.contains(nxt)) {
                        res.add(nxt);
                        queue.add(nxt);
                        visited.add(nxt);
                    }
                }
            }
        }
        return res;
    }
    // level order traversal from node1 and node2 at the same time CANNOT solve the problem
    // e.g. node3 is 3 steps away from node1 and node2, so totalDist = 3+3 = 6
    // node4 is 4 steps away from node1 and 1 steps away from node2, totalDist = 4+1 = 5
    // if use level order BFS, we will return node3, which is WRONG !!!
    public GitNode findLCA(GitNode node1, GitNode node2) {
        Map<GitNode, Integer> map1 = new HashMap<>();
        Map<GitNode, Integer> map2 = new HashMap<>();
        bfs(node1, map1);
        bfs(node2, map2);

        int minDist = Integer.MAX_VALUE;
        GitNode LCA = null;
        for(GitNode node : map2.keySet()) {
            if(map1.containsKey(node)) {
                int dist = map1.get(node) + map2.get(node);
                if(dist < minDist) {
                    minDist = dist;
                    LCA = node;
                }
            }
        }
        return LCA;
    }

    void bfs(GitNode node, Map<GitNode, Integer> map) {
        Set<GitNode> visited = new HashSet<>();
        Queue<GitNode> queue = new ArrayDeque<>();
        queue.add(node);

        int dist = 0;
        while(!queue.isEmpty()) {
            for(int i = 0, size = queue.size(); i < size; i++) {
                GitNode cur = queue.poll();
                map.put(cur, dist);

                for(GitNode nxt : cur.parents) {
                    if(!visited.contains(nxt)) {
                        visited.contains(nxt); // mark as visited
                        queue.add(nxt);
                    }
                }
            }
            dist++;
        }
    }

    public static void main(String[] args) {
        GitCommit sol = new GitCommit();
        /*
         *
         *   5 <-      4  <- 2
         *    \         \
         *     \ <- 3 <- 1
         * */
        GitNode g1 = new GitNode(1);
        GitNode g2 = new GitNode(2);
        GitNode g3 = new GitNode(3);
        GitNode g4 = new GitNode(4);
        GitNode g5 = new GitNode(5);

        g1.parents.add(g3);
        g1.parents.add(g4);
        g2.parents.add(g4);
        g3.parents.add(g5);
        g4.parents.add(g5);
//        List<GitNode> ancestors = sol.findAllCommits(g1);
//        for(GitNode node : ancestors) {
//            System.out.print(node.id + " ");
//        }
        GitNode node = sol.findLCA(g3, g2);
        System.out.println(node.id);
    }
}


class GitNode{
    int id;
    List<GitNode> parents;
    public GitNode(int id){
        this.id = id;
        this.parents = new ArrayList<>();
    }
}