import java.util.*;

/**
 * Created by yeqing on 1/19/2018.
 */
public class WordLadderII {

    List<List<String>> res = new ArrayList<>();
    Set<String> wordSet = new HashSet<>();
    Map<String, Integer> distance = new HashMap<>(); // distance of node w from beginWord
    Map<String, List<String>> adj = new HashMap<>(); // adjacent list represent the graph

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        for (String s : wordList) wordSet.add(s);
        if(!wordSet.contains(endWord))  return res;

        boolean found = false;
        int len = 0;

        Queue<String> queue = new ArrayDeque<>();
        queue.add(beginWord);

        // level order BFS traversal
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                String v = queue.poll();
                int dist = distance.get(v);
                for(String w : getNeighbors(v, wordSet)) { //
                    if(dist+1 <= distance.getOrDefault(w, 0)) {
                        queue.add(w);
                        distance.put(w, dist+1);
                        if(w.equals(endWord)) found = true;
                        from.putIfAbsent(w, new ArrayList<>());
                        from.get(w).add(v);
                    }
                }
            }
            len++;
            if(found) break;
        }
        System.out.println(len);
        printTree(from, endWord);
        // use dfs to retrieve from all shortest path
        dfs(res, new ArrayList<>(), endWord, len, from);
        return res;
    }

    void bfs() {

    }

    List<String> getNeighbors(String s, Set<String> wordSet){
        List<String> neighbors = new ArrayList<>();
        for(String word : wordSet) {
            if(diffByOne(s, word)) neighbors.add(word);
        }
        return neighbors;
    }

    // length of s1 and s2 are the same, return true if s1 and s2 diff by one character
    private boolean diffByOne(String s1, String s2) {
        int count = 0;
        for(int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                count++;
                if(count > 1) return false;
            }
        }
        return count == 1;
    }

    void dfs(List<List<String>> res, List<String> tempList, String start, int count, Map<String, List<String>> from) {
        tempList.add(start);
        count--;
        if(count == 0) {
            res.add(new ArrayList<>(tempList));
            return;
        }

        for(String s : from.get(start)) {
            dfs(res, tempList, s, count, from);
        }
    }

    void printTree(Map<String, List<String>> tree, String root) {
        Queue<String> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            for(int count = queue.size(); count > 0; count--) {
                String front = queue.poll();
                System.out.print(front + " ");
                for(String nb : tree.get(front)) {
                    queue.add(nb);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        Set<String> wordSet = new HashSet<>(wordList);
        WordLadderII sol = new WordLadderII();
        for(List<String> list : sol.findLadders(beginWord, endWord, wordList)) {
            System.out.println();
        }
        System.out.println(sol.getNeighbors(beginWord, wordSet));
    }
}
