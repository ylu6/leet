/**
 * Created by yeqing on 12/17/2017.
 */
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        if(cost == null || cost.length < 2) return 0;
        int prv1 = 0, prv2 = 0, cur = 0;
        for (int i = 2; i <= cost.length; i++) {
            cur = Math.min(prv1+cost[i-1], prv2+cost[i-2]);
            System.out.println("prv1="+prv1 + " prv2="+prv2 + " cur=" + cur);
            prv2 = prv1;
            prv1 = cur;
        }
        return cur;
    }
    public static void main(String[] args) {
        int[] cost = {0,1,1,1};
        MinCostClimbingStairs sol = new MinCostClimbingStairs();
        System.out.println(sol.minCostClimbingStairs(cost));
    }
}
