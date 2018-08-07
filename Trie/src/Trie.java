import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeqing on 7/3/2017.
 */
public class Trie {
    TrieNode root;

    class TrieNode {
        Map<Character, TrieNode> map;
        boolean isEndNode;

        TrieNode() {
            map = new HashMap<Character, TrieNode>();
            isEndNode = false;
        }
    }

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new TrieNode());
            }
            curr = curr.map.get(c);
        }
        curr.isEndNode = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.map.containsKey(c)) return false;
            curr = curr.map.get(c);
        }
        return curr.isEndNode;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!curr.map.containsKey(c)) return false;
            curr = curr.map.get(c);
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
