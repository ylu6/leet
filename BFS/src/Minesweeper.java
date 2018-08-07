/**
 * Created by yeqing on 8/30/2017.
 */
public class Minesweeper {
    // dfs search
    public char[][] updateBoard(char[][] board, int[] click) {
        int row = click[0], col = click[1];
        int m = board.length, n = board[0].length;

        if (board[row][col] == 'M' || board[row][col] == 'X') {
            board[row][col] = 'X';
            return board;
        }

        // counts adjacent mines
        int count = 0;
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int r = row + i, c = col + j;
                if (r < 0 || r >= m || c < 0 || c >= n) continue;
                if (board[r][c] == 'M') count++;
            }
        }

        if (count > 0) board[row][col] = (char)('0'+count); // there are mines around, stop search
        else { // no mine around, continue search unrevealed empty square 'E'
            board[row][col] = 'B';
            for(int i = -1; i <= 1; i++) { // check all neighbors
                for(int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) continue;
                    int r = row + i, c = col + j;
                    if (r < 0 || r >= m || c < 0 || c >= n) continue;
                    if (board[r][c] == 'E') updateBoard(board, new int[]{r, c});
                }
            }
        }

        return board;
    }
}
