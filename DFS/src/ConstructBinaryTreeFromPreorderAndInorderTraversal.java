import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
//    preorder = [3,9,20,15,7]
//    inorder = [9,3,15,20,7]
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i = 0; i < inorder.length; i++) map.put(inorder[i], i);
        return buildTree(preorder, inorder, 0, 0, inorder.length-1);
    }

    TreeNode buildTree(int[] preorder, int[] inorder, int preIdx, int inStart, int inEnd) {
        if(preIdx >= preorder.length || inStart > inEnd) return null;
        TreeNode root = new TreeNode(preorder[preIdx]);
        int inIdx = map.get(preorder[preIdx]);
        root.left = buildTree(preorder, inorder, preIdx+1, inStart, inIdx-1);
        root.right = buildTree(preorder, inorder, preIdx+ (inIdx - inStart) + 1, inIdx+1, inEnd);

        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) {
        val = x;
    }
}
