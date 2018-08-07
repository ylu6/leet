/**
 * Created by yeqing on 11/1/2017.
 */
public class LongestUnivaluePath {
    int longestPath = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        helperPathDown(root);
        return longestPath;
    }

    int helperPathDown(TreeNode root) {
        int leftLen = 0, rightLen = 0;
        if (root.left != null) {
            if (root.val ==root.left.val) leftLen = 1 + helperPathDown(root.left);
            else    helperPathDown(root.left);
        }
        if (root.right != null) {
            if (root.val == root.right.val) rightLen = 1+ helperPathDown(root.right);
            else    helperPathDown(root.right);
        }

        longestPath = Math.max(longestPath, leftLen + rightLen);
        return Math.max(leftLen, rightLen);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(5);
        LongestUnivaluePath sol = new LongestUnivaluePath();
        System.out.println(sol.longestUnivaluePath(root));
    }
}
