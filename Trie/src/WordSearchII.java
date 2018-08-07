import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeqing on 12/8/2017.
 */
public class WordSearchII {
    class TrieNode {
        TrieNode[] next;
        boolean isEndNode;
        public TrieNode(){
            next = new TrieNode[26];
            isEndNode = false;
        }
    }

    private void addWord(TrieNode root, String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            if (cur.next[word.charAt(i)-'a'] == null)
                cur.next[word.charAt(i)-'a'] = new TrieNode();
            cur = cur.next[word.charAt(i) - 'a'];
        }
        cur.isEndNode = true;
    }

    private void dfs(char[][] board, TrieNode root, List<String> res, int i, int j, StringBuilder sb) {
        if (board[i][j] == 'x') return; // board[i][j] used before
        char c = board[i][j];
        if (root.next[c-'a'] == null) return; // the prefix word formed in current dfs path not contained in the Trie
        board[i][j] = 'x'; // mark as visited
        sb.append(c);
        System.out.println(sb.toString());
        if (root.next[c-'a'].isEndNode) {
            res.add(sb.toString());
            root.next[c-'a'].isEndNode = false; // to avoid duplicate, remove this word from Trie
        }

        if (i > 0) dfs(board, root.next[c-'a'], res, i-1, j, sb);
        if (i < board.length-1) dfs(board, root.next[c-'a'], res, i+1, j, sb);
        if (j > 0) dfs(board, root.next[c-'a'], res, i, j-1, sb);
        if (j < board[0].length-1) dfs(board, root.next[c-'a'], res, i, j+1, sb);

        board[i][j] = c; // reset board[i][j] to original char after back from recursive call of dfs
        if (sb.length() > 0) sb.deleteCharAt(sb.length()-1);
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (board == null || board.length == 0 || board[0].length == 0) return res;
        TrieNode root = new TrieNode();
        for (String word : words) addWord(root, word);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, root, res, i, j, sb);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        WordSearchII sol = new WordSearchII();
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath"};
        for (String s :sol.findWords(board, words))
            System.out.println(s);
    }
}
