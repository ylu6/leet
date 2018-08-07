import java.util.ArrayDeque;
import java.util.Deque;

public class VerifyPreorderSequenceInBinarySearchTree {
    /**
     * @param preorder: List[int]
     * @return: return a boolean
     */

    // v1: recursive approach, time complexity O(N^2)
    public boolean verifyPreorder(int[] preorder) {
        return verifyPreorder(preorder, 0, preorder.length-1);
    }

    boolean verifyPreorder(int[] preorder, int lo, int hi) {
        if(hi-lo<=1) return true;
        int idx = lo+1, mid;
        while(idx <= hi && preorder[idx] <= preorder[lo]) idx++;
        mid = idx;
        while(idx <= hi) {
            if(preorder[idx] < preorder[lo]) return false;
            idx++;
        }
        return verifyPreorder(preorder, lo+1, mid) && verifyPreorder(preorder, mid, hi);
    }

    // v2: iterative approach, use stack and variable low, which is the root value of a subtree
    // update low when entering the right branch of the sub-tree
    boolean verifyPreorder2(int[] preorder) {
        Deque<Integer> stack = new ArrayDeque<>();
        int low = Integer.MIN_VALUE;
        for(int n : preorder) {
            if(n < low) return false;
            while(!stack.isEmpty() && n > stack.peekFirst()) {
                low = stack.pollFirst();
            }
            stack.addFirst(n);
        }
        return true;
    }
}
