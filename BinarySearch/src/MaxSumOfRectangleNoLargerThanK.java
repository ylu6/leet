import java.util.TreeSet;

/**
 * Created by yeqing on 8/11/2017.
 */

public class MaxSumOfRectangleNoLargerThanK {
    // for 1D array, we can get the MaxSum of subarray by doing a prefix sum and add into a TreeSet
    // when processing num[i], prefix sum from 0 to i-1 is in the set, for index j in this range
    // sum[j,i) = sum[0,i)-sum[0,j) <= k  => sum[0,j) >= sum[0,i)-k
    // we want to find the smallest sum[0,j) which meets the above condition
    // which can be done by call set's ceiling function: set.ceiling(sum[0,j)-k)
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length ==0) return 0;
        int m = matrix.length, n = matrix[0].length, res = Integer.MIN_VALUE;
        // assume row number is much larger than column number
        for (int l = 0; l < n; l++) { // sum values from different column
            int[] sums = new int[m];
            for (int r = l; r < n; r++) { // all the column combinations: O(n^2)
                for (int i = 0; i < m; i++) {
                    sums[i] = sums[i] + matrix[i][r];
                }
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0); // prefix sum with no element
                int prefixSum = 0;
                for (int i : sums) {
                    prefixSum += i;
                    Integer num = set.ceiling(prefixSum-k); // num is a previous prefixSum stored in TreeSet
                    if (num != null) res = Math.max(res, prefixSum-num); // range sum = current prefixSum - previous prefixSum
                    set.add(prefixSum);
                }
            }
        }
        return res;
    }
}

