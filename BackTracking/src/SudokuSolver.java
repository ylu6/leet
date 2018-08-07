/**
 * Created by yeqing on 6/2/2017.
 */
public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][10]; // for simplicity, store 0 - 9, but 0 is not used
        boolean[][] cols = new boolean[9][10];
        boolean[][] cells = new boolean[9][10];
        preprocess(board, rows, cols, cells); // preprocess the initial Sudoku board
        backtract(board, 0, rows, cols, cells);
    }

    private boolean backtract(char[][] board, int index,
                           boolean[][] rows, boolean[][] cols, boolean[][] cells )
    {
        int[] coord = nextCell(board, index);
        int row = coord[0];
        int col = coord[1];
        if (row == -1)  {
            return true;
        }

        for (int i = 1; i <= 9; i++) {
            // check whether number is used by same row, col and cell
            if (rows[row][i] || cols[col][i] || cells[row/3*3+col/3][i]) continue;
            // if not used, mark it as used, and recursive call
            rows[row][i] = true;
            cols[col][i] = true;
            cells[row/3*3 + col/3][i] = true;
            board[row][col] = (char) (i+'0'); // char '1' to '9'
            boolean found = backtract(board, index+1, rows, cols, cells);
            if (found) return true;
            rows[row][i] = false;
            cols[col][i] = false;
            cells[row/3*3 + col/3][i] = false;
            board[row][col] = '.'; // char '1' to '9'
        }
        return false;
    }
    private void preprocess(char[][] board, boolean[][] rows, boolean[][] cols, boolean[][] cells) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char c = board[row][col];
                if (c == '.') continue;
                rows[row][c-'0'] = true;
                cols[col][c-'0'] = true;
                cells[row/3*3 + col/3][c-'0'] = true;
            }
        }
    }
    // find next empty cell in range [index, end], index = row*9+col
    private int[] nextCell(char[][] board, int index) {
        for (int i = index; i < 81; i++) {
            if (board[i/9][i%9] == '.') return new int[]{i/9, i%9};
        }
        return new int[]{-1, -1};
    }

    // v2, without helper function nextCell
    public void solveSudoku2(char[][] board) {
        boolean[][] rows = new boolean[9][10]; // for simplicity, store 0 - 9, but 0 is not used
        boolean[][] cols = new boolean[9][10];
        boolean[][] cells = new boolean[9][10];
        preprocess(board, rows, cols, cells); // preprocess the initial Sudoku board
        solve(board, rows, cols, cells);
    }

    private boolean solve(char[][] board, boolean[][] rows, boolean[][] cols, boolean[][] cells) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] != '.') continue;
                for(int n = 1; n <= 9; n++) {
                    if(rows[i][n] || cols[j][n] || cells[i/3*3 + j/3][n]) continue;
                    board[i][j] = (char) ('0' + n);
                    rows[i][n] = true;
                    cols[j][n] = true;
                    cells[i/3*3 + j/3][n] = true;
                    if(solve(board, rows, cols, cells)) return true;
                    board[i][j] = '.';
                    rows[i][n] = false;
                    cols[j][n] = false;
                    cells[i/3*3 + j/3][n] = false;
                }
                return false;
            }
        }
        return true;
    }

    // v3, no boolean[], simplest code, slightly speed
    public void solveSudoku3(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] != '.') continue;
                for(char c = '1'; c <= '9'; c++) {
                    if(isValid(board, i, j, c)) {
                        board[i][j] = c;
                        if(solve(board)) return true;
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }
    // return true if board is valid after set (row, col) to c
    // needs to check 9 index, if use boolean[][] rows, cols, cells, only check 1
    private boolean isValid(char[][] board, int row, int col, char c){
        for(int i = 0; i < 9; i++) {
            if(board[i][col] == c) return false; //check row
            if(board[row][i] == c) return false; //check column
            if(board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
        }
        return true;
    }

    public static void main(String[] args) {
        String[] boardString = {"53..7....", "6..195...", ".98....6.", "8...6...3",
                                "4..8.3..1", "7...2...6", ".6....28.", "...419..5", "....8..79"};
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++)
            board[i] = boardString[i].toCharArray();

//        for (int row = 0; row < 9; row++) {
//            for (int col = 0; col < 9; col++) {
//                System.out.print(board[row][col] + " ");
//            }
//            System.out.println();
//        }

        SudokuSolver ss = new SudokuSolver();
//        int[] next = ss.nextCell(board, 8*9+7);
//        System.out.println(next[0] + " " + next[1]);

        ss.solveSudoku(board);
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}
