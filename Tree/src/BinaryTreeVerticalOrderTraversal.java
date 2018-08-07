import java.util.*;

public class BinaryTreeVerticalOrderTraversal {
    /**
     * @param root: the root of tree
     * @return: the vertical order traversal
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> q = new ArrayDeque<>();
        Queue<Integer> indices = new ArrayDeque<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int minIdx = 0, maxIdx = 0;

        q.add(root);
        indices.add(0);
        while(!q.isEmpty()) {
            int N = q.size();
            while(N-- > 0) {
                TreeNode front = q.poll();
                int pos = indices.poll();
                map.putIfAbsent(pos, new ArrayList<Integer>());
                map.get(pos).add(front.val);
                if(front.left != null) {
                    q.add(front.left);
                    indices.add(pos-1);
                    minIdx = Math.min(minIdx, pos-1);
                }
                if(front.right != null) {
                    q.add(front.right);
                    indices.add(pos+1);
                    maxIdx = Math.max(maxIdx, pos+1);
                }
            }
        }

        for(int i = minIdx; i <= maxIdx; i++) {
            res.add(map.get(i));
        }
        return res;
    }
}
