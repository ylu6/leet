import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yeqing on 7/27/2017.
 */
public class ReplaceWords {
    // v1, use hashset. Time complexity O(n*k^2), k is average length of string
    public String replaceWords(List<String> dict, String sentence) {
        Set<String> set = new HashSet<String>();
        for (String s : dict)
            set.add(s);

        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            String prefix = "";
            for (int i = 1; i <= word.length(); i++) {
                prefix = word.substring(0, i);
                if (set.contains(prefix)) break;
            }
            sb.append(prefix).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // v2, use trie
    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        boolean isEndNode;
    }

    class Trie {
        TrieNode head;
        Trie() { head = new TrieNode(); }
        void add(String s) {
            TrieNode cur = head;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (cur.next[c-'a'] == null)
                    cur.next[c-'a'] = new TrieNode();
                cur = cur.next[c-'a'];
            }
            cur.isEndNode = true;
        }
        String getPrefix(String s) {
            if (s == null || s.length() == 0) return "";
            StringBuilder sb = new StringBuilder();
            TrieNode cur = head;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (cur.isEndNode) return sb.toString(); // find one prefix, return
                if (cur.next[c-'a'] == null) {
                    return "";
                }
                sb.append(c);
                cur = cur.next[c-'a'];
            }
            return "";
        }
    }

    public String replaceWords2(List<String> dict, String sentence) {
        Trie trie = new Trie();
        for (String s : dict) trie.add(s);
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            String prefix = trie.getPrefix(word);
            if (prefix == "") sb.append(word).append(" ");
            else sb.append(prefix).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static void main(String[] args) {
        List<String> dict = Arrays.asList("cat", "bat", "rat");
        String sentence = "the cattle was rattled by the battery";
        ReplaceWords sol = new ReplaceWords();
        System.out.println(sol.replaceWords2(dict, sentence));
    }
}