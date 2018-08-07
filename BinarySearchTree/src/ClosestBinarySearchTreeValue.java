public class ClosestBinarySearchTreeValue {
    /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    // v1, maintain two treenode lo and hi, which is the ceil and floor of target in the BST
    public int closestValue(TreeNode root, double target) {
        TreeNode lo = null, hi = null;
        while(root != null) {
            if(root.val <= target) {
                lo = root;
                root = root.right;
            } else {
                hi = root;
                root = root.left;
            }
        }
        if(lo == null) return hi.val;
        if(hi == null) return lo.val;
        return (hi.val-target > target - lo.val) ? lo.val : hi.val;
    }

    // v2, maintain diff and res,
    public int closestValue2(TreeNode root, double target) {
        double diff = Math.abs(target-root.val);
        int res = root.val;
        while(root != null) {
            if(Math.abs(root.val - target) < diff) {
                res = root.val;
                diff = Math.abs(root.val - target);
            }
            if(root.val <= target) root = root.right;
            else root = root.left;
        }
        return res;
    }
}
