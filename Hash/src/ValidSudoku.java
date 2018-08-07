public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][10]; // 9 rows, nums are 1-9, for convenient, array index is [0-9]
        boolean[][] cols = new boolean[9][10]; // 9 cols
        boolean[][] cells = new boolean[9][10]; // 9 cells

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j]=='.') continue;
                int num = board[i][j] - '0';
                if(rows[i][num] || cols[j][num] || cells[i/3*3 + j/3][num]) return false;

                rows[i][num] = true;
                cols[j][num] = true;
                cells[i/3*3 + j/3][num] = true;
            }
        }
        return true;
    }
}
