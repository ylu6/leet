public class DominoAndTrominoTiling {
    // for i-th column, there are 3 possible states, all filled, top is filled, bottom is filled
    // use 3 array to store possible ways for each state
    // fill[i], top[i], bot[i]
    // there are 4 possible cases can lead to column i filled
    // i-1 is filled, add a 2x1 tile vertically, like ||
    // i-2 is filled, add two 1x2 tile, like =
    // i-1 column's top is filled, add a L shaped tile
    // i-1 column's bottom is filled, add a L shaped tile
    // therefore fill[i] = fill[i-1] + fill[i-2] + top[i-1] + bot[i-1]
    // similarly for top and bot, for i-th column's top be filled, there are 2 cases
    // bottom of column i-1 is filled or column i-2 is filled
    // therefore top[i] = bot[i-1] + fill[i-2]
    public int numTilings(int N) {
        if(N<=2) return N;
        int mod = 1000000007;
        int[] fill = new int[N];
        int[] top = new int[N];
        int[] bot = new int[N];

        fill[0] = 1;
        fill[1] = 2;
        top[0] = 0;
        top[1] = 1;
        bot[0] = 0;
        bot[1] = 1;

        for(int i = 2; i < N; i++) {
            // every number in fill, top and bot will be smaller than 1e9+7
            // add two number will not overflow int
            // add four may overflow
            // (x+y)%z = (x%z + y%z) % z
            fill[i] = (int) ((0L + fill[i-1] + fill[i-2] + top[i-1] + bot[i-1]) % mod);
            top[i] = (bot[i-1] + fill[i-2]) % mod;
            bot[i] = (top[i-1] + fill[i-2]) % mod;
        }

        return fill[N-1];
    }

    // version 2, optimize space complexity to O(1)
    public int numTilings2(int N) {
        if(N<=2) return N;
        int mod = 1000000007;
        int fill = 0, fill_pre = 2, fill_prepre = 1;
        int top = 0, top_pre = 1;
        int bot = 0, bot_pre = 1;

        for(int i = 2; i < N; i++) {
            // every number in fill, top and bot will be smaller than 1e9+7
            // add two number will not overflow int
            // add four may overflow
            // (x+y)%z = (x%z + y%z) % z
            fill = (int) ((0L + fill_pre + fill_prepre + top_pre + bot_pre) % mod);
            top = (bot_pre + fill_prepre) % mod;
            bot = (top_pre + fill_prepre) % mod;

            fill_prepre = fill_pre;
            fill_pre = fill;
            top_pre = top;
            bot_pre = bot;
        }

        return fill;
    }

    public static void main(String[] args) {
        DominoAndTrominoTiling sol = new DominoAndTrominoTiling();
        System.out.println(sol.numTilings(40));
        System.out.println(sol.numTilings2(40));
    }
}
