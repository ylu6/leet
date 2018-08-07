import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if(root == null) return res;
        List<String> list = new ArrayList<>();
        dfs(root, res, list);
        return res;
    }

    void dfs(TreeNode root, List<String> res, List<String> list) {
        list.add(String.valueOf(root.val));
        if(root.left == null && root.right == null) {
            res.add(String.join("->", list));
        }
        if(root.left != null)  dfs(root.left, res, list);
        if(root.right != null) dfs(root.right, res, list);
        if(list.size() > 0) list.remove(list.size()-1);
    }
}
