/**
 * Created by yeqing on 12/13/2017.
 */
public class MaximumSumOf3NonOverlappingSubarrays {
    // 1. calculate prefix sum: prefixSum[i] is sum of subarray indexed in [0,i)
    // 2. scan from left to right, calculate leftMaxSum, rightMaxSun
    // leftMaxIdx[i]: idx where max value of sum of subarray [i, i+k-1], for i in range 0 to i
    // rightMaxIdx[i]: idx wheremax value of sum of subarray [i, i+k-1], for i in range i to len-k
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int N = nums.length;
        int[] prefixSum = new int[N+1];
        for(int i = 1; i <= N; i++) prefixSum[i] = prefixSum[i-1] + nums[i-1];
        int[] leftMaxIdx = new int[N], rightMaxIdx = new int[N];
        // scan from left to right, calculate leftMaxIdx for each i
        for (int i = 1, maxSum = prefixSum[k]-prefixSum[0]; i <= N-3*k; i++) {
            if(prefixSum[i+k]-prefixSum[i] > maxSum) {
                maxSum = prefixSum[i+k] - prefixSum[i];
                leftMaxIdx[i] = i;
            } else leftMaxIdx[i] = leftMaxIdx[i-1];
        }
        // scan from right to left, calculate rightMaxIdx for each i
        rightMaxIdx[N-k] = N-k;
        for (int i = N-k-1, maxSum = prefixSum[N]-prefixSum[N-k]; i>=2*k; i--) {
            if(prefixSum[i+k]-prefixSum[i] > maxSum) {
                maxSum = prefixSum[i+k]-prefixSum[i];
                rightMaxIdx[i] = i;
            } else rightMaxIdx[i] = rightMaxIdx[i+1];
        }
        // scan left index of the middle sub-array
        int maxSum = 0;
        int[] res = new int[3];
        for(int i = k; i <= N-2*k; i++) { // i is left index of mid sub-array, in range [k, N-2*k]
            int idx0 = leftMaxIdx[i-k]; // idx where left maxSubArray sum is achieved for current middle sub-array
            int idx2 = rightMaxIdx[i+k]; // index where max right sub-array sum is achieved for current middle sub-array position
            int sum = (prefixSum[idx0+k]-prefixSum[idx0]) + prefixSum[i+k]-prefixSum[i] + prefixSum[idx2+k]-prefixSum[idx2];
            if(sum > maxSum) {
                maxSum = sum;
                res = new int[]{idx0, i, idx2};
            }
        }
        return res;
    }
}
