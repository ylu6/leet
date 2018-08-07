import java.util.HashMap;
import java.util.Map;

public class LargestBSTSubtree {
    int max = 0;

    public int largestBSTSubtree(TreeNode root) {
        isBST(root);
        return max;
    }

    value isBST(TreeNode root) {
        if(root == null) return new value(true);

        value l = isBST(root.left);
        value r = isBST(root.right);
        if(!l.isBST || !r.isBST) return new value(false);
        if( (root.left != null && root.val < l.max) || (root.right != null && root.val > r.min))
            return new value(false);
        int size = 1 + l.size + r.size;
        max = Math.max(size, max);
        int newMin = root.left == null ? root.val : l.min;
        int newMax = root.right == null ? root.val : r.max;
        return new value(newMin, newMax, size, true);
    }
    class value{
        int min, max, size;
        boolean isBST = true;
        public value(boolean isBST) { this.isBST = isBST; };
        public value(int min, int max, int size, boolean isBST) {
            this.min = min;
            this.max = max;
            this.size = size;
            this.isBST = isBST;
        }
    }
    public static void main(String[] args) {
        /* Let us construct the following Tree
                50
             /      \
            10        60
           /  \       /  \
          5   20    55    70
                   /     /  \
                  45   65    80
         */

        TreeNode root = new TreeNode(50);
        root.left = new TreeNode(10);
        root.right = new TreeNode(60);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(20);
        root.right.left = new TreeNode(55);
        root.right.left.left = new TreeNode(45);
        root.right.right = new TreeNode(70);
        root.right.right.left = new TreeNode(65);
        root.right.right.right = new TreeNode(80);

        /* The complete tree is not BST as 45 is in right subtree of 50.
         The following subtree is the largest BST
             60
            /  \
          55    70
          /     /  \
        45     65   80
        */
        LargestBSTSubtree sol = new LargestBSTSubtree();
        System.out.println("Size of largest BST is " + sol.largestBSTSubtree(root));
    }
}
