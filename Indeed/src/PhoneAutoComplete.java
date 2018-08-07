import java.util.ArrayList;
import java.util.List;

public class PhoneAutoComplete {
    List<String> query(String prefix, String[] dict) {
        TrieNode root = new TrieNode();
        for(String word : dict) {
            addWord(root, word);
        }

        TrieNode cur = root;
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for(char c : prefix.toCharArray()) {
            if(cur.next[c-'a'] == null) return res;
            sb.append(c);
            cur = cur.next[c-'a'];
        }
        dfs(res, sb, cur);
        return res;
    }

    void dfs(List<String> res, StringBuilder sb, TrieNode root) {
        if(root.isLeaf) res.add(sb.toString());

        for(char c = 'a'; c <= 'z'; c++) {
            if(root.next[c-'a'] != null) {
                sb.append(c);
                dfs(res, sb, root.next[c-'a']);
                if(sb.length() > 0) sb.deleteCharAt(sb.length()-1);
            }
        }
    }

    void addWord(TrieNode root, String word) {
        for(char c : word.toCharArray()) {
            int idx = c-'a';
            if(root.next[idx] == null) root.next[idx] = new TrieNode();
            root = root.next[idx];
        }
        root.isLeaf = true;
    }
    class TrieNode{
        TrieNode[] next;
        boolean isLeaf;
        public TrieNode(){
            next = new TrieNode[26];
            isLeaf = false;
        }
    }

    public static void main(String[] args) {
        String[] dict = {"apple", "alpha", "app", "boss", "bot", "answer"};
        PhoneAutoComplete sol = new PhoneAutoComplete();
        System.out.println(sol.query("a", dict));
        System.out.println(sol.query("ap", dict));
        System.out.println(sol.query("bo", dict));
        System.out.println(sol.query("ah", dict));
    }
}
