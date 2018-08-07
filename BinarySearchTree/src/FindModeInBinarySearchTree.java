import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindModeInBinarySearchTree {
    // v1, hsahmap based
    int maxF = 0;
    int maxCount = 0;
    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        inorder(root, map);
        int[] res = new int[maxCount];
        int i = 0;
        for(int n : map.keySet()) {
            if(map.get(n)==maxF)
                res[i++] = n;
        }
        return res;
    }

    void inorder(TreeNode root, Map<Integer, Integer> map) {
        if(root==null) return;
        inorder(root.left, map);
        int f = map.getOrDefault(root.val, 0) + 1;
        if(f == maxF) maxCount++;
        if(f > maxF) {
            maxF = f;
            maxCount=1;
        }
        map.put(root.val, f);
        inorder(root.right, map);
    }

    // v2, inorder traversal, check prv and cur
    int count = 0;
    TreeNode prv = null;
    List<Integer> res = new ArrayList<>();
    public int[] findMode2(TreeNode root) {
        inorder2(root);
        int[] arr = new int[res.size()];
        for(int i = 0; i < res.size(); i++)
            arr[i] = res.get(i);

        return arr;
    }

    void inorder2(TreeNode root) {
        if(root == null) return;
        inorder2(root.left);
        if(prv == null || prv.val != root.val) {
            count = 1;
        } else {
             count++;
        }
        if(count == maxF) res.add(root.val);
        if(count > maxF) {
            maxF = count;
            res = new ArrayList<>();
            res.add(root.val);
        }
        prv = root;
        inorder2(root.right);
    }
}
