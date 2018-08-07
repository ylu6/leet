import java.util.*;

/**
 * Created by yeqing on 8/29/2017.
 */
public class FindDuplicateSubtrees {

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, TreeNode> map = new HashMap<>();
        Set<String> duplicates = new HashSet<>();
        List<TreeNode> res = new ArrayList<>();

        dfs(root, map, duplicates);
        for (String s : duplicates) res.add(map.get(s));
        return res;
    }
    // post order traversal
    String dfs(TreeNode root, Map<String, TreeNode> map, Set<String> duplicates) {
        if (root == null) return "";

        String sLeft = dfs(root.left, map, duplicates);
        String sRight = dfs(root.right, map, duplicates);
        String str = (sLeft=="" ? "L" : sLeft) + root.val +
                " " + (sRight=="" ? "R" : sRight);
        if (map.containsKey(str)) duplicates.add(str);
        else map.put(str, root);
        return str;
    }
}
