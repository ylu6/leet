/**
 * Created by yeqing on 7/3/2017.
 * 211. Add and Search Word - Data structure design
 */
public class WordDictionary2 {
    TrieNode root;

    class TrieNode {
        TrieNode[] next;
        boolean isEndNode;
        TrieNode() {
            next = new TrieNode[26];
        }
    }
    /** Initialize your data structure here. */
    public WordDictionary2() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (curr.next[idx] == null)
                curr.next[idx] =new TrieNode();
            curr = curr.next[idx];
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

        if (c != '.') {
            if (head.next[c-'a'] == null) return false;
            return search(word, pos + 1, head.next[c-'a']);
        }
        for (TrieNode node : head.next) {
            if (node == null) continue;
            if (search(word, pos+1, node))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        WordDictionary2 dict = new WordDictionary2();
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");
        System.out.println(dict.search("pad"));
        System.out.println(dict.search("bad"));
        System.out.println(dict.search(".ad"));
        System.out.println(dict.search("b.."));
    }
}
