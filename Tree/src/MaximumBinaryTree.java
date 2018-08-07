import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by yeqing on 8/28/2017.
 */

public class MaximumBinaryTree {

    // version 1, naive recursive, O(N^2) worst case
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return helper(nums, 0, nums.length-1);
    }

    TreeNode helper(int[] nums, int lo, int hi) {
        if (lo == hi) return new TreeNode(nums[lo]);
        if (lo > hi) return null;

        int maxIdx = lo;
        for (int i = lo+1; i <= hi; i++) {
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }
        TreeNode root = new TreeNode(nums[maxIdx]);
        root.left = helper(nums, lo, maxIdx-1);
        root.right = helper(nums, maxIdx+1, hi);
        return root;
    }

    // version 2, use stack to store TreeNode, O(N) time, O(h) space
    // e.g. 4,2,1,3, draw the flow and stack, solution will become obvious
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        // use stack to store TreeNode,
        // from bottom to top, value is in non-increasing order
        // therefore, in the end, TreeNode at the bottom of the stack has the largest value
        Deque<TreeNode> stack = new ArrayDeque<>();
        for (int n : nums) {
            TreeNode curr = new TreeNode(n);
            while (!stack.isEmpty() && stack.peekFirst().val < n) {
                // search the larget TreeNode which is smaller than curr
                // connect that node to left of curr. In the mean time, pop out all smaller nodes
                curr.left = stack.pollFirst();
            }
            if (!stack.isEmpty()) {
                stack.peekFirst().right = curr; // if stack not empty, connect curr to right of top TreeNode
            }
            stack.addFirst(curr); // add current TreeNode into stack
        }
        return stack.peekLast();
    }
}
