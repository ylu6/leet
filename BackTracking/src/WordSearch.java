/**
 * Created by yeqing on 5/31/2017.
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) return false;
        int M = board.length, N = board[0].length;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (dfs(board, word, 0, i, j)) return true;
            }
        }
        return false;
    }
    public boolean dfs(char[][] board, String word, int pos, int i, int j) {
        if (pos == word.length()) return true;
        if (i < 0 || i == board.length || j < 0 || j == board[i].length) return false;
        if (board[i][j] != word.charAt(pos)) return false;
        board[i][j] = '-';
        boolean found = dfs(board, word, pos+1, i-1, j) || dfs(board, word, pos+1, i+1, j) ||
                dfs(board,word, pos+1, i, j-1) || dfs(board, word, pos+1, i, j+1);
        board[i][j] = word.charAt(pos);
        return found;
    }

    public static void main(String[] args) {
        char[][] board =   {{'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
        WordSearch ws = new WordSearch();
        System.out.println(ws.exist(board, "ABCB"));
    }
}
