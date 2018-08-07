public class PaintHouse {
    /**
     * @param costs: n x 3 cost matrix
     * @return: An integer, the minimum cost to paint all houses
     */
    public int minCost(int[][] costs) {
        int prv0 = 0, prv1 = 0, prv2 = 0;
        int cur0 = 0, cur1 = 0, cur2 = 0;

        for(int[] cost : costs) {
            cur0 = cost[0] + Math.min(prv1, prv2);
            cur1 = cost[1] + Math.min(prv0, prv2);
            cur2 = cost[2] + Math.min(prv0, prv1);
            prv0 = cur0;
            prv1 = cur1;
            prv2 = cur2;
        }

        return Math.min(cur0, Math.min(cur1, cur2));
    }
}
