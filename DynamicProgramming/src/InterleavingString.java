/**
 * Created by yeqing on 8/7/2017.
 */
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()) return false;

        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];

        // initialize boundary
        dp[0][0] = true;
        for(int i = 1; i <= s1.length(); i++) {
            dp[i][0] = s1.charAt(i-1) == s3.charAt(i-1) && dp[i-1][0];
        }
        for(int j = 1; j <= s2.length(); j++) {
            dp[0][j] = s2.charAt(j-1) == s3.charAt(j-1) && dp[0][j-1];
        }

        // dynamic processing
        for(int i = 1; i <= s1.length(); i++) {
            for(int j = 1; j <= s2.length(); j++) {
                dp[i][j] = (s1.charAt(i-1)==s3.charAt(i+j-1) && dp[i-1][j])
                        || (s2.charAt(j-1)== s3.charAt(i+j-1) && dp[i][j-1]);
            }
        }

        return dp[s1.length()][s2.length()];
    }

    void printMat(boolean[][] mat){
        for(boolean[] row : mat) {
            for(boolean b : row)
                System.out.print(b + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String s1 = "aab";
        String s2 = "d";
        String s3 = "aadb";
        InterleavingString sol = new InterleavingString();
        System.out.println(sol.isInterleave(s1, s2, s3));
    }
}
