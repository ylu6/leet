/**
 * Created by yeqing on 8/30/2017.
 */
public class GameOfLife {
    // only the LSB was used in the integer of the input board
    // we can use the second LSB to store the updated status based on input
    // then loop through the whole board and shift right one bit
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;

        // if board[i][j]==01, lives <2 or >3, its new status should still be 01
        // if board[i][j]==01, 2 <= lives <= 3, its new status should be 11
        // if board[i][j]==00 and lives == 3, its new status should be 10
        // if board[i][j]==00 and lives != 3, its new status should still be 00
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int lives = liveNeighbors(board, i, j);
                if(board[i][j] == 1 && lives >= 2 && lives <= 3) board[i][j] = 3;
                if(board[i][j] == 0 && lives == 3) board[i][j] = 2;
            }
        }

        // right shift board value to get final results
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                board[i][j] = board[i][j] >> 1;
            }
        }
    }

    int liveNeighbors(int[][] board, int row, int col) {
        int m = board.length, n = board[0].length, count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int r = row + i, c = col + j;
                if (r < 0 || r >= m || c < 0 || c >= n) continue;
                if ((board[r][c]&1) == 1) count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        GameOfLife sol = new GameOfLife();
        int[][] board = {{1,1},{1,0}};
        System.out.println(sol.liveNeighbors(board, 1, 0));
    }
}
