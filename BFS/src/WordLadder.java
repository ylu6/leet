import java.util.*;

/**
 * Created by yeqing on 8/2/2017.
 */
public class WordLadder {
    // single direction search
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>();
        Deque<String> queue = new ArrayDeque<>();
        queue.add(beginWord);
        for (String s : wordList) { set.add(s); }
        if (!set.contains(endWord)) return 0;

        int count = 1;
        while (!queue.isEmpty()) {
            int N = queue.size();

            for (int i = 0; i < N; i++) {
                String curr = queue.pollFirst();
                if (curr.equals(endWord)) return count;
                List<String> neighbors = getNeighbors(curr, set);
                for (String neighbor : neighbors) {
                    queue.addLast(neighbor);
                    set.remove(neighbor);
                }
            }
            count++;
        }
        return 0;
    }
    // double end BFS search, faster version
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        Set<String> visited = new HashSet<>();
        Deque<String> front1 = new ArrayDeque<>();
        Deque<String> front2 = new ArrayDeque<>();
        front1.add(beginWord);
        front2.add(endWord);

        int count = 1;
        while (!front1.isEmpty() && !front2.isEmpty()) {
            // always do the search on front1, switch front1 and front2 is front2's size is smaller
            if (front1.size() > front2.size()) {
                Deque<String> tmp = front1;
                front1 = front2;
                front2 = tmp;
            }
            int N = front1.size();
            for (int i = 0; i < N; i++) {
                String curr = front1.pollFirst();
                if (visited.contains((curr))) continue;
                visited.add(curr);
                if (front2.contains(curr)) return count;
                List<String> neighbors = getNeighbors(curr, wordList);
                for (String neighbor : neighbors) {
                    if(!visited.contains(neighbor)) front1.addLast(neighbor);
                }
            }
            count++;
        }
        return 0;
    }
    // bidirectional BFS search, using HashSet instead of Queue to store front
    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        Set<String> visited = new HashSet<>();
        Set<String> front1 = new HashSet<>();
        Set<String> front2 = new HashSet<>();
        front1.add(beginWord);
        front2.add(endWord);

        int count = 1;
        while (!front1.isEmpty() && !front2.isEmpty()) {
            // always do the search on front1, switch front1 and front2 is front2's size is smaller
            if (front1.size() > front2.size()) {
                Set<String> tmp = front1;
                front1 = front2;
                front2 = tmp;
            }
            Set<String> tempSet = new HashSet<String>();
            for (String curr : front1) {
                if (visited.contains((curr))) continue;
                visited.add(curr);
                if (front2.contains(curr)) return count;
                List<String> neighbors = getNeighbors(curr, wordList);
                for (String neighbor : neighbors) {
                    tempSet.add(neighbor);
                }
            }
            front1 = tempSet;
            count++;
        }
        return 0;
    }

    List<String> getNeighbors(String word, Set<String> wordList) {
        List<String> neighbors = new ArrayList<>();
        for (String s : wordList) {
            if (isNeighbors(word, s))
                neighbors.add(s);
        }
        return neighbors;
    }
    List<String> getNeighbors(String word, List<String> wordList) {
        List<String> neighbors = new ArrayList<>();
        for (String s : wordList) {
            if (isNeighbors(word, s))
                neighbors.add(s);
        }
        return neighbors;
    }
    boolean isNeighbors(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                count++;
                if (count > 1) return false;
            }
        }
        return count == 1;
    }
    public static void main(String[] args) {
        WordLadder sol = new WordLadder();
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        Set<String> set = new HashSet<>();
        for (String s : wordList)
            set.add(s);
        //unit test for isNeighbors()
//        System.out.println(sol.isNeighbors("log", "abc"));
//        System.out.println(sol.isNeighbors("log", "dog"));
//        System.out.println(sol.isNeighbors("log", "lot"));
//        System.out.println(sol.isNeighbors("log", "log"));

        //unit test for getNeighbors()
//        System.out.println(sol.getNeighbors("dog", set).toString());
        System.out.println(sol.ladderLength2("hit", "cog", wordList));
    }
}
