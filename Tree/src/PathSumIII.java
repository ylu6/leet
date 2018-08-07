import java.util.HashMap;
import java.util.Map;

public class PathSumIII {
    // if it is a array, we can store {prefix_sum: freq} in a HashMap, then update the possible path count
    // similar for a binary tree, use preorder traversal
    // pathSum += root.val, check for count, add update pathSum in map
    // visit left, visit right; when return from current node, reduce freq of pathSum from map
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // no element selected, 1 possible selection
        return preorder(root, sum, map, 0);
    }

    int preorder(TreeNode root, int sum, Map<Integer, Integer> map, int pathSum) {
        if(root == null) return 0;
        pathSum += root.val;
        int count = map.getOrDefault(pathSum - sum, 0); // calculate count before add pathSum into HashMap!!
        map.put(pathSum, map.getOrDefault(pathSum, 0)+1);
        count += preorder(root.left, sum, map, pathSum);
        count += preorder(root.right, sum, map, pathSum);

        map.put(pathSum, map.get(pathSum)-1);
        return count;
    }
}
