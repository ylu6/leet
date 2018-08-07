/**
 * Created by yeqing on 12/13/2017.
 */
public class SuperWashingMachines {
    // first calculate sum of the array, if sum%len != 0, return false
    // for a specific position i, sum of left part is l, and sum of right part is r
    // leftDiff = l - avg*lenOfLeft is how far away left part is from the final status
    // rightDiff = r - avg*lenOfRight is for the right part
    // if both leftDiff and rightDiff is less than 0, means we need to move leftDiff+rightDiff from position i to left and right part
    // we can move 1 dress a time from position i, therefore requires abs(leftDiff) + abs(rightDiff)
    // if both > 0, we need to move from left and right to position i. e.g. 4,1,4, move from pos 0 and pos 2 to pos 1, this can be done in the same time
    public int findMinMoves(int[] machines) {
        int N = machines.length;
        int[] preSum = new int[N+1]; // prefix sum
        for(int i = 1; i <= N; i++) preSum[i] = preSum[i-1]+machines[i-1];
        int sum = preSum[N];
        if (sum%N != 0) return -1;
        int avg = sum/N;
        int res = Math.max(Math.abs(machines[0]-avg), Math.abs(machines[N-1]-avg)); // pos 0 and pos N-1 case
        for(int i = 1; i < N-1; i++) {
            int L = avg*i - preSum[i];
            int R = avg*(N-i-1) - (preSum[N]-preSum[i+1]);
            System.out.println("L: " + L + " R: " + R);
            if(L > 0 && R > 0)  // both left side and right side of current position need dresses
                res = Math.max(res, L+R);
            else
                res = Math.max(res, Math.max(Math.abs(L), Math.abs(R)));
        }
        return res;
    }
    public static void main(String[] args) {
        int[] machines = {0,3,0};
        SuperWashingMachines sol = new SuperWashingMachines();
        System.out.println(sol.findMinMoves(machines));
    }
}
