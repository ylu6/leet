/**
 * Created by yeqing on 8/30/2017.
 */
public class BinaryTreeMaximumPathSum {
    int maxSum;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxSum;
    }

    // return max path sum only goes down, and starts at the input node root
    int maxPathDown(TreeNode root) {
        if (root == null) return 0;
        int lVal = Math.max(0, maxPathDown(root.left)); // if maxPathDown of left is less than 0, don't use it
        int rVal = Math.max(0, maxPathDown(root.right)); // same as above
        maxSum = Math.max(maxSum, lVal + rVal + root.val);
        return Math.max(lVal, rVal) + root.val;
    }
}
