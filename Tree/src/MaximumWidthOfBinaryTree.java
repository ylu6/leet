import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeqing on 8/29/2017.
 */
public class MaximumWidthOfBinaryTree {

    public int widthOfBinaryTree(TreeNode root) {
        // idxLo stores left coordinate for each tree elvel
        // idxHi store right coordinate for each tree level
        List<Integer> idxLo = new ArrayList<>();
        List<Integer> idxHi = new ArrayList<>();
        dfs(root, idxLo, idxHi, 0, 0);

        int maxW = 0;
        for (int i = 0; i < idxLo.size(); i++) {
            int width = idxHi.get(i) - idxLo.get(i) + 1;
            maxW = Math.max(maxW, width);
        }

        return maxW;
    }

    // for TreeNode with rowID = i and colID = j, its two child node's rowID is i+1
    // left child colID = 2*j, right colID = 2*j + 1
    void dfs(TreeNode root, List<Integer> idxLo, List<Integer> idxHi, int rowID, int colID) {
        if (root == null) return;

        if (rowID > idxLo.size()-1) { // current level hasn't been visited before
            idxLo.add(colID);
            idxHi.add(colID);
        }
        else {
//            if (colID < idxLo.get(rowID)) idxLo.set(rowID, colID); // find new left bound for current level
//            if (colID > idxHi.get(rowID)) idxHi.set(rowID, colID); // find new right bound for current level
            // above process is indeed not required, because we are doing an inorder traversal
            // TreeNodes on the same level is always visited from left to right
            // while the level is visited first time, we add idxLo, which must be the left bound
            // while we see a new TreeNode on a level, this new node must has larger colID than the one in list
            idxHi.set(rowID, colID); // just update right bound
        }

        dfs(root.left, idxLo, idxHi, rowID+1, colID*2); // left sub-tree
        dfs(root.right, idxLo, idxHi, rowID+1, colID*2+1); // right sub-tree
    }
}
