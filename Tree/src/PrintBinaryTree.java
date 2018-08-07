import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yeqing on 8/29/2017.
 */
public class PrintBinaryTree {

    // print binary tree, such that there is only one number in a column for all columns
    // final 2D list dimension: m x n. m = h, n = 2^h-1
    // n is the possible number of TreeNode in a binary tree with height h.
    public List<List<String>> printTree(TreeNode root) {
        int m = treeHeight(root);
        int n = (int) Math.pow(2, m) - 1;

        String[] row = new String[n];
        Arrays.fill(row, "");
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) res.add(new ArrayList<>(Arrays.asList(row)));

        printTree(res, root, 1, 0, n-1);
        return res;
    }

    void printTree(List<List<String>> res, TreeNode root, int level, int lo, int hi) {
        if (root == null) return;

        int mid = (lo + hi) / 2;
        res.get(level-1).set(mid, ""+root.val);
        printTree(res, root.left, level+1, lo, mid-1);
        printTree(res, root.right, level+1, mid+1, hi);
    }
    int treeHeight(TreeNode root) {
        if (root == null) return 0;
        return 1+Math.max(treeHeight(root.left), treeHeight(root.right));
    }
}
