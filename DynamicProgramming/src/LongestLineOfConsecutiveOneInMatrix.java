public class LongestLineOfConsecutiveOneInMatrix {

    public int longestLine(int[][] M) {
        int max = 0;
        if(M == null || M.length == 0 || M[0].length == 0) return 0;
        int m = M.length, n = M[0].length;
        int[][] v = new int[m][n];
        int[][] h = new int[m][n];
        int[][] s1 = new int[m][n];
        int[][] s2 = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(M[i][j] == 0) continue;
                v[i][j] = i-1>=0 ? v[i-1][j] + 1 : 1;
                s1[i][j] = (i-1 >= 0 && j-1 >= 0) ? s1[i-1][j-1] + 1 : 1;
                s2[i][j] = (i-1 >= 0 && j+1 < n) ? s2[i-1][j+1] + 1 : 1;
                max = Math.max(Math.max(max, s2[i][j]), Math.max(v[i][j], s1[i][j]));
                h[i][j] = (j-1 >= 0) ? h[i][j-1] + 1 : 1;
                max = Math.max(max, h[i][j]);
            }
        }
        return max;
    }

    void printM(int[][] M) {
        for(int[] row : M) {
            for(int n : row) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] M = {{0,1,1,0}, {0,1,1,0}, {0,0,0,1}};
        LongestLineOfConsecutiveOneInMatrix sol = new LongestLineOfConsecutiveOneInMatrix();
        sol.longestLine(M);
    }
}
