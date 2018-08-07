/**
 * Created by yeqing on 8/11/2017.
 */
public class DungeonGame {
    // InitH[i][j]: the initial health when knight enters room [i][j]
    // starting from the lower right corner, the initial condition:
    // we know when knight enter room, his health should be at least 1 -> InitH[m][n] >= 1
    // At the end of this room, its health should be also at least 1 -> InitH[m][n] + dungeon[m][n] >= 1
    // combine the two conditions, and choose the smallest possible value -> InitH[m][n] = max(1, 1+dungeon[m][n])

    // similarly, for a middle point (not edge or corner):
    // when knight enter room [i,j], his health should be at least 1 -> InitH{i][j] >= 1
    // if go right to [i, j+1], which has a Initial Health requirement -> InitH[i][j] +dungeon[i][j] >= InitH[i][j+1]
    // if go down -> InitH[i][j] + dungeon[i][j] >= InitH[i+1][j]
    // since we only pick the smaller one between right and down
    // Minimum possible value for InitH[i][j] = max(1, min(InitH[i][j+1], InitH[i+1][j])-dungeon[i][j]))
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;
        int m = dungeon.length, n = dungeon[0].length;
        int[][] initH = new int[m][n];

        initH[m-1][n-1] = Math.max(1, 1-dungeon[m-1][n-1]);
        for (int i = m-2; i >= 0; i--) // go vertical direction
            initH[i][n-1] = Math.max(1, initH[i+1][n-1]-dungeon[i][n-1]);
        for (int j = n-2; j >= 0; j--) // go horizontal direction
            initH[m-1][j] = Math.max(1, initH[m-1][j+1]-dungeon[m-1][j]);
        System.out.println(initH[0][0] + " : " + initH[0][1]);

        for (int i = m-2; i >= 0; i--) {
            for (int j = n-2; j >= 0; j--) {
                initH[i][j] = Math.max(1, Math.min(initH[i+1][j], initH[i][j+1])-dungeon[i][j]);
            }
        }
        return initH[0][0];
    }
    // v2: optimize memory
    public int calculateMinimumHP2(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;
        int m = dungeon.length, n = dungeon[0].length;
        int[] dp = new int[n];
        // initialize boundary condition
        dp[n-1] = Math.max(1, 1-dungeon[m-1][n-1]);
        for(int c = n-2; c >= 0; c--) {
            dp[c] = Math.max(1, dp[c+1]-dungeon[m-1][c]);
        }

        // loop the matrix
        for(int r = m-2; r >= 0; r--) {
            dp[n-1] = Math.max(1, dp[n-1]-dungeon[r][n-1]);
            for(int c = n-2; c >= 0; c--) {
                dp[c] = Math.min(dp[c], dp[c+1]) - dungeon[r][c];
                dp[c] = Math.max(1, dp[c]);
            }
        }
        return dp[0];
    }
    public static void main(String[] args) {
        int[][] dungeon = {{0, -3}};
        DungeonGame sol = new DungeonGame();
        System.out.println(sol.calculateMinimumHP(dungeon));
        System.out.println(sol.calculateMinimumHP2(dungeon));
    }
}
