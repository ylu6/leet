/**
 * Created by yeqing on 11/18/2017.
 */
public class LongestWordInDictionary {
    int maxLen = 0;
    String res = "";

    class TrieNode{
        TrieNode[] child;
        boolean isEndNode;
        TrieNode() {
            child = new TrieNode[26];
            isEndNode = false;
        }
    }
    public String longestWord(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            insert(root, word);
        }
        StringBuilder sb = new StringBuilder();
        dfs(root, sb, 0);
        return res;
    }
    void dfs(TrieNode root, StringBuilder sb, int len) {
        if (len > 0 && !root.isEndNode) return;
        if (len > maxLen) {
            maxLen = len;
            res = sb.toString();
        }
        for (int i = 0; i < 26; i++) {
            if (root.child[i] == null) continue;
            dfs(root.child[i], sb.append((char)(i+'a')), len+1);
            if (sb.length() > 0) sb.deleteCharAt(sb.length()-1);
        }
    }
    void insert(TrieNode root, String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.child[c-'a'] == null)
                cur.child[c-'a'] = new TrieNode();
            cur = cur.child[c-'a'];
        }
        cur.isEndNode = true;
    }
}
