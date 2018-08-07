public class LargestSumOfAverages {
    // memo[n][k]: largestSumOfAverages when partition sub-array [0, n) into k groups
    public double largestSumOfAverages(int[] A, int K) {
        double[][] memo = new double[A.length+1][K+1];
        memo[0][1] = A[0];
        int[] sums = new int[A.length+1];
        for(int i = 1; i <= A.length; i++)
            sums[i] = sums[i-1] + A[i-1];
        return topdown(sums, memo, A.length, K); // partition whole array [0, N) into K groups
    }

    // return largestSumOfAverages when partition sub-array [0, n) into k groups
    double topdown(int[] sums, double[][] memo, int n, int k) {
        if(memo[n][k] > 0) return memo[n][k]; // calculated before, return cached value
        if(k==1) return 1.0*sums[n]/n; // no partitioning at all, return average value of sub-array [0,n)

        // why starting from k-1? if i < k-1, the sub-array [0, i) cannot be partitioned into k-1 groups
        for(int i = k-1; i < n; i++) {
            memo[n][k] = Math.max(memo[n][k], topdown(sums, memo, i, k-1) + 1.0*(sums[n]-sums[i])/(n-i));
        }
        return memo[n][k];
    }

    public static void main(String[] args) {
        LargestSumOfAverages sol = new LargestSumOfAverages();
        int[] A = {9,1,2,3,9};
        System.out.println(sol.largestSumOfAverages(A, 3));
    }
}
