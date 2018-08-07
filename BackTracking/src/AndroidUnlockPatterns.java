public class AndroidUnlockPatterns {
    /**
     * @param m: an integer
     * @param n: an integer
     * @return: the total number of unlock patterns of the Android lock screen
     */
    int[][] jump = new int[10][10];
    public int numberOfPatterns(int m, int n) {
        // line {1,3} through 2, {1,7} through 4, {3,9} through 6, {7,9} through 8
        jump[1][3] = jump[3][1] = 2;
        jump[1][7] = jump[7][1] = 4;
        jump[3][9] = jump[9][3] = 6;
        jump[7][9] = jump[9][7] = 8;
        // {1,9},{3,7},{2,8}, {4,6} through 5
        jump[1][9] = jump[9][1] = jump[3][7] = jump[7][3] = jump[4][6]
                   = jump[6][4] = jump[2][8] = jump[8][2] = 5;

        // used array, store which numbers have been used on current path
        boolean[] used = new boolean[10];
        int count = 0;
        // 1,3,7,9 are symmetric, same as 2,4,6,8, therefore calculate once and multiply by 4
        count += 4*backtrack(1, m, n, 1, used);
        count += 4*backtrack(2, m, n, 1, used);
        count += backtrack(5, m, n, 1, used);

        return count;
    }

    int backtrack(int start, int m, int n, int len, boolean[] used) {
        if(len > n) return 0;
        int count = len >= m ? 1 : 0;

        used[start] = true;
        for(int next = 1; next <= 9; next++) {
            int j = jump[start][next];
            if (!used[next] && (j == 0 || used[j])) {
                count += backtrack(next, m, n, len+1, used);
            }
        }
        used[start] = false;
        return count;
    }

    public static void main(String[] args) {
        AndroidUnlockPatterns sol = new AndroidUnlockPatterns();
        System.out.println(sol.numberOfPatterns(1, 2));
    }
}
