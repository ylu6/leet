/**
 * Created by yeqing on 8/27/2017.
 */
public class TwoSumIV {
    public boolean findTarget(TreeNode root, int k) {
        BSTIterator fIT = new BSTIterator(root);
        BSTIterator rIT = new BSTIterator(root, true);
        for (int lo = fIT.next(), hi = rIT.next(); lo < hi; ) {
            System.out.println(lo + " " + hi);
            if (lo + hi == k)       return true;
            else if (lo + hi > k)   hi = rIT.next();
            else                    lo = fIT.next();

        }
        return false;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
//        root.left.left = new TreeNode(2);
//        root.left.right = new TreeNode(4);
//        root.right.right = new TreeNode(7);
        TwoSumIV sol = new TwoSumIV();
        System.out.println(sol.findTarget(root, 1));
    }
}



