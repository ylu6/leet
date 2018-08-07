/**
 * Created by yeqing on 11/1/2017.
 */

class MapSum {

    TrieNode root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        TrieNode cur = root;
        for(char c : key.toCharArray()) {
            if(cur.next[c] == null) {
                cur.next[c] = new TrieNode();
            }
            cur = cur.next[c];
        }
        cur.val = val;
    }

    public int sum(String prefix) {
        TrieNode cur = root;
        for(char c : prefix.toCharArray()) {
            if(cur.next[c] == null) return 0;
            cur = cur.next[c];
        }
        return sumTrie(cur);
    }

    int sumTrie(TrieNode node) {
        if(node == null) return 0;
        int sum = node.val;
        for(TrieNode nxt : node.next) {
            sum += sumTrie(nxt);
        }
        return sum;
    }

    class TrieNode {
        TrieNode[] next;
        int val;
        public TrieNode(){
            next = new TrieNode[256];
            val = 0;
        }
    }

    public static void main(String[] args) {
        MapSum sol = new MapSum();
        sol.insert("aa", 3);
        System.out.println(sol.sum("a"));
        sol.insert("aa", 2);
        System.out.println(sol.sum("a"));
        System.out.println(sol.sum("aa"));
        sol.insert("aaa", 3);
        System.out.println(sol.sum("aaa"));
        System.out.println(sol.sum("aa"));
        System.out.println(sol.sum("bbb"));

    }
}
