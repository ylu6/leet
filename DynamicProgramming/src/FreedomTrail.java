/**
 * Created by yeqing on 8/25/2017.
 */
public class FreedomTrail {
    // dp[i][j]: for key[i], the minimum step till last key if ring[j] if aligned at 12:00 position
    // The process starts from the last key position, and ends at the first key position
    // The game starts with ring[0] aligned at 12:00, therefore return dp[0][0]
    public int findRotateSteps(String ring, String key) {
        int[][] dp = new int[key.length()+1][ring.length()];

        for (int i = key.length()-1; i >= 0; i--) { // match key[i]
            for (int j = 0; j < ring.length(); j++) { // ring[j] is aligned at 12:00
                dp[i][j] = Integer.MAX_VALUE; // initialize dp[i][j]
                for (int k = 0; k < ring.length(); k++) { // try all ring positions
                    if (ring.charAt(k) != key.charAt(i)) continue; // no match
                    int diff = Math.abs(k-j); // start from j, ends at k, diff is abs(k-j)
                    int step = Math.min(diff, ring.length()-diff); // min of clockwise and counter clockwise
                    // 1 for spell, dp[i+1][k] is starting from ring position k, optimal solution to spell sub-key[i+1,end]
                    dp[i][j] = Math.min(dp[i][j], 1+step+dp[i+1][k]); //
                }
            }
        }
        return dp[0][0];
    }
}
