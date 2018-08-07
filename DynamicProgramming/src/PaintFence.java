public class PaintFence {
    /**
     * @param n: non-negative integer, n posts
     * @param k: non-negative integer, k colors
     * @return: an integer, the total number of ways
     */
    // at each post, there are two states
    // same: use same color as previous post
    // diff: use different color as previous post
    public int numWays(int n, int k) {
        int same = 0, diff = k;
        if(n == 0 || k == 0) return 0;

        int temp;
        for(int i = 2; i <= n; i++) {
            temp = same;
            same = diff;
            diff = (temp+diff)*(k-1);
        }
        return same+diff;
    }

    public static void main(String[] args) {
        PaintFence sol = new PaintFence();
        System.out.println(sol.numWays(3,2));
    }
}
