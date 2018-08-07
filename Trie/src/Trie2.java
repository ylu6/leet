/**
 * Created by yeqing on 7/3/2017.
 */
public class Trie2 {
    TrieNode root;

    class TrieNode {
        TrieNode[] map;
        boolean isEndNode;

        TrieNode() {
            map = new TrieNode[26];
            isEndNode = false;
        }
    }

    /** Initialize your data structure here. */
    public Trie2() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.map[c-'a'] == null) {
                curr.map[c-'a'] = new TrieNode();
            }
            curr = curr.map[c-'a'];
        }
        curr.isEndNode = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.map[c-'a'] == null) return false;
            curr = curr.map[c-'a'];
        }
        return curr.isEndNode;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (curr.map[c-'a'] == null) return false;
            curr = curr.map[c-'a'];
        }
        return true;
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("tea");
        t.insert("ted");
        System.out.println(t.search("tea"));
        System.out.println(t.search("tesla"));
        System.out.println(t.startsWith("te"));
        System.out.println(t.startsWith("ta"));

    }
}
