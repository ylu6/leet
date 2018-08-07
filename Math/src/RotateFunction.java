/**
 * Created by yeqing on 12/13/2017.
 */
public class RotateFunction {
    //      4   3   2   6
    // F(0) 4*0 3*1 2*2 6*3
    // F(1) 6*0 4*1 3*2 2*3
    // F(2) 2*0 6*1 4*2 3*3
    // F(k)-F(k-1) = sum - n*A[n - k], here n is 4
    public int maxRotateFunction(int[] A) {
        int sum = 0, F = 0, n = A.length;
        for(int i = 0; i < A.length; i++) {
            sum += A[i];
            F += i*A[i];
        }
        int maxF = F;
        for(int k = 1; k < n; k++) {
            F = F + sum - n*A[n-k];
            maxF = Math.max(maxF, F);
        }
        return maxF;
    }
}
