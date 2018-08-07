import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeqing on 7/3/2017.
 * 211. Add and Search Word - Data structure design
 */
public class WordDictionary {
    TrieNode root;

    class TrieNode {
        Map<Character, TrieNode> next;
        boolean isEndNode;
        TrieNode() {
            next = new HashMap<Character, TrieNode>();
        }
    }
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.next.containsKey(c))
                curr.next.put(c, new TrieNode());
            curr = curr.next.get(c);
        }
        curr.isEndNode = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word, 0, root);
    }
    private boolean search(String word, int pos, TrieNode head) {
        if (pos == word.length()) return head.isEndNode;

        char c = word.charAt(pos);
//        if (head.next.isEmpty()) // not needed, if next is empty, the for loop below will not execute, then false will be returned
//            return false;

        if (c != '.') {
            if (!head.next.containsKey(c)) return false;
            return search(word, pos + 1, head.next.get(c));
        }
        for (TrieNode node : head.next.values()) {
            if (search(word, pos+1, node))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        WordDictionary dict = new WordDictionary();
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");
        System.out.println(dict.search("pad"));
        System.out.println(dict.search("bad"));
        System.out.println(dict.search(".ad"));
        System.out.println(dict.search("b.."));
    }
}
