public class ValidTicTacToeState {
    public boolean validTicTacToe(String[] board) {
        // whether any one wins? check char on 8 lines, 3 vertical, 3 lateral and 2 cross
        int[][] lines = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        int numOfX = 0, numOfO = 0;
        for(String row : board) {
            for(int i = 0; i < row.length(); i++){
                if(row.charAt(i)=='O') numOfO++;
                else if(row.charAt(i)=='X') numOfX++;
            }
        }
        if(numOfO > numOfX || numOfX - numOfO > 1) // O more than X or X-O more than 1, invalid
            return false;

        boolean xWin = false, oWin = false;
        for(int[] line : lines) {
            // convert 1d index to 2d index, row = idx/3, col = idx%3
            char c0 = board[line[0]/3].charAt(line[0]%3);
            char c1 = board[line[1]/3].charAt(line[1]%3);
            char c2 = board[line[2]/3].charAt(line[2]%3);
            if(c0 == c1 && c1 == c2) {
                if(c0 == 'X') xWin = true;
                if(c0 == 'O') oWin = true;
            }
        }
        if(xWin && oWin) return false; // both X and O win, invalid status
        if(xWin) return numOfX - numOfO == 1; // if X wins, numOfX - numOfO should be 1
        if(oWin) return numOfX == numOfO; // if O wins, numOfX should equals to numOfO
        return true;
    }

    public static void main(String[] args) {
        String[] board = {"XOX","O O","XOX"};
        ValidTicTacToeState sol = new ValidTicTacToeState();
        System.out.println(sol.validTicTacToe(board));
    }
}
