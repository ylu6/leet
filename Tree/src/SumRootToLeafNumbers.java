import java.util.ArrayDeque;
import java.util.Deque;

public class SumRootToLeafNumbers {
    // v1, recursive
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        return dfs(root, 0);
    }
    int dfs(TreeNode root, int num) {
        num = num*10 + root.val;
        if(root.left == null && root.right == null) return num;

        int sum = 0;
        if(root.left != null) sum += dfs(root.left, num);
        if(root.right != null) sum += dfs(root.right, num);
        return sum;
    }

    // v2, stack version
    public int sumNumbers2(TreeNode root) {
        if(root == null) return 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<Integer> vStack = new ArrayDeque<>();
        TreeNode cur = root;
        int num = 0, sum = 0;

        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                num = num*10 + cur.val;
                if(cur.left == null && cur.right == null) sum += num;
                stack.addFirst(cur);
                vStack.addFirst(num);
                cur = cur.left;
            } else {
                cur = stack.pollFirst();
                num = vStack.pollFirst();
                cur = cur.right;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        SumRootToLeafNumbers sol = new SumRootToLeafNumbers();
        System.out.println(sol.sumNumbers2(root));
    }
}
