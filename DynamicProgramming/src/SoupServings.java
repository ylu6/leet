import java.util.Arrays;

public class SoupServings {
    // The probability that A empties first is larger, therefore for larger enough N
    // the results will approach 1, after some trial, N = 10000 is a safe number
    // memo[i][j]: the probability if we have i serving of A and j serving of B
    public double soupServings(int N) {
        if(N > 10000) return 1.0;
        N = (N + 24) / 25; // convert ml into serving, 1 servering is 25 mL
        double[][] memo = new double[N+1][N+1];
        for(int i = 0; i <= N; i++) Arrays.fill(memo[i], -1);
        return dfs(memo, N, N);
    }

    double dfs(double[][] memo, int a, int b) {
        if(a <= 0 && b <= 0) return 0.5;
        if(a <= 0) return 1.0;
        if(b <= 0) return 0.0;

        if(memo[a][b] >= 0.0) return memo[a][b];
        double p = 0.25*(dfs(memo, a-4, b) + dfs(memo, a-3, b-1)
                + dfs(memo, a-2, b-2) + dfs(memo, a-1, b-3));

        memo[a][b] = p;
        return p;
    }

    public static void main(String[] args) {
        SoupServings sol = new SoupServings();
        System.out.println(sol.soupServings(5000));
    }
}
