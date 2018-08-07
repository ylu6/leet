public class UniqueBinarySearchTrees {
    // pick i=1,2…n as root of the tree, count = countOfLeftSubTree * countOfRightSubTree;
    // number of unique BST is the same for array {1,2,3} and {5,6,7};
    // Therefore, dp[j] is the number of unique BST for continuous subarray with length of j.
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= i; j++) {
                dp[i] += dp[j-1]*dp[i-j];
            }
        }
        return dp[n];
    }
}
