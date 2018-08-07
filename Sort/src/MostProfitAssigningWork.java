import java.util.*;

public class MostProfitAssigningWork {
    // sort + two pointer
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        ArrayList<int[]> DP = new ArrayList<>(profit.length); // zip difficulty and profit
        for(int i = 0; i < profit.length; i++) DP.add(new int[]{difficulty[i], profit[i]});
        DP.sort((a,b)->a[0]-b[0]); // sort by difficulty
        Arrays.sort(worker);

        int idx = -1, maxP = 0, res = 0;
        for(int w : worker) {
            while(idx+1 < DP.size() && DP.get(idx+1)[0] <= w) {
                idx++;
                maxP = Math.max(maxP, DP.get(idx)[1]);
            }
            res += maxP;
        }
        return res;
    }
}
