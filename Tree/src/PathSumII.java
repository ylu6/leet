import java.util.ArrayList;
import java.util.List;

public class PathSumII {
    // backtrack
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        dfs(root, sum, res, new ArrayList<>());
        return res;
    }
    // use res, list as path variable, preorder traversal
    void dfs(TreeNode root, int sum, List<List<Integer>> res, List<Integer> list) {
        sum -= root.val;
        list.add(root.val);
        if(root.left == null && root.right == null && sum == 0) { // root is leaf && root to leaf pathSum equals to sum
            res.add(new ArrayList<>(list));
            list.remove(list.size()-1);
            return;
        }

        if(root.left != null) dfs(root.left, sum, res, list);
        if(root.right != null) dfs(root.right, sum, res, list);
        if(list.size()>0) list.remove(list.size()-1);
    }
}
