import javax.sound.midi.SysexMessage;
import java.util.Arrays;

public class PaintHouseII {
    /**
     * @param costs: n x k cost matrix
     * @return: an integer, the minimum cost to paint all houses
     */
    // same idea as paint house I, use int[] curr and int[] prv, O(n*k*k)
    public int minCostII(int[][] costs) {
        int[] cur = new int[costs[0].length]; // length is k (number of colors)
        int[] prv = new int[costs[0].length];

        for(int[] cost : costs) { // loop through all houses
            for(int k = 0; k < cost.length; k++) { // get minCost foreach color after painting current house
                cur[k] = cost[k] + minCostExceptSelf(prv, k);
            }
            prv = Arrays.copyOf(cur, cur.length);
        }
        int minCost = Integer.MAX_VALUE;
        for(int cost : cur) {
            minCost = Math.min(minCost, cost);
        }
        return minCost;
    }

    // return the minimum cost inside costs[], exclude index k
    int minCostExceptSelf(int[] cost, int k) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < cost.length; i++) {
            if(i != k) min = Math.min(min, cost[i]);
        }
        return min;
    }

    // v2, O(n*k)
    public int minCostII_v2(int[][] costs) {
        if(costs == null || costs.length == 0 || costs[0].length == 0) return 0;
        int K = costs[0].length;

        int prvMinIdx = -1, minIdx = -1, min1 =Integer.MAX_VALUE, min2, prvMin1 = 0, prvMin2 = 0;
        for(int[] cost : costs) {
            min1 = Integer.MAX_VALUE;
            min2 = Integer.MAX_VALUE;
            for(int k = 0; k < K; k++) {
                int currCost;
                if (k != prvMinIdx) currCost = cost[k] + prvMin1;
                else currCost = cost[k] + prvMin2;
                if (currCost < min1) {
                    min2 = min1;
                    min1 = currCost;
                    minIdx = k;
                } else if (currCost < min2) {
                    min2 = currCost;
                }
            }
            prvMinIdx = minIdx;
            prvMin1 = min1;
            prvMin2 = min2;
            System.out.println(minIdx + ", " + min1 + " " + min2);
        }
        return min1;
    }

    public static void main(String[] args) {
        int[][] costs = {{14,2,11},{11,14,5},{14,3,10}};
        PaintHouseII sol = new PaintHouseII();
        System.out.println(sol.minCostII(costs));
        System.out.println(sol.minCostII_v2(costs));
    }
}
