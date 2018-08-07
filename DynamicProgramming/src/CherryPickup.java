//public class CherryPickup {
//    public int cherryPickup(int[][] grid) {
//        int N = grid.length, res = 0;
//        int[][] dp = new int[N][N];
//
//        dp[0][0] = grid[0][0];
//        for(int j = 0; j < N; j++) { // init first row
//            if(grid[0][j] == -1 || dp[0][j-1] == -1) dp[0][j] = -1;
//            else dp[0][j] = dp[0][j-1] + grid[0][j];
//        }
//        for(int i = 1; i < N; i++) {
//            dp[i][0] =
//            for(int j )
//        }
//
//        return res;
//    }
//}
