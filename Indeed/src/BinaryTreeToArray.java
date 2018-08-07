import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeToArray {

    int getTreeHeight(BTreeNode root) {
        if(root == null) return 0;
        return 1 + Math.max(getTreeHeight(root.left), getTreeHeight(root.right));
    }

    int[] DenseTreeToArray(BTreeNode root) {
        int len = (int) Math.pow(2, getTreeHeight(root));
        int[] arr = new int[len];
        Queue<BTreeNode> queue = new ArrayDeque<>();
        Queue<Integer> idxQueue = new ArrayDeque<>();
        queue.add(root);
        idxQueue.add(1);
        while(!queue.isEmpty()) {
            BTreeNode cur = queue.poll();
            int idx = idxQueue.poll();
            arr[idx] = cur.val;
            if(cur.left != null) {
                queue.offer(cur.left);
                idxQueue.offer(idx*2);
            }
            if(cur.right != null) {
                queue.offer(cur.right);
                idxQueue.offer(idx*2+1);
            }
        }
        return arr;
    }

    int[] sparseTreeToArray(BTreeNode root){
        List<Integer> list = new ArrayList<>();
        dfs(list, root);
        return list.stream().mapToInt(i->i).toArray();
    }

    void dfs(List<Integer> res, BTreeNode root) {
        if(root == null) {
            res.add(0);
            return;
        }
        res.add(root.val);
        dfs(res, root.left);
        dfs(res, root.right);
    }
    void printArray(int[] arr) {
        for(int n : arr) System.out.print(n + " ");
        System.out.println();
    }
    public static void main(String[] args) {
        BinaryTreeToArray sol = new BinaryTreeToArray();
        BTreeNode root = new BTreeNode(1);
        root.left = new BTreeNode(2);
        root.right = new BTreeNode(3);
        root.right.left = new BTreeNode(4);
        root.right.right = new BTreeNode(5);
//        root.right.right.left = new BTreeNode(6);
        sol.printArray(sol.DenseTreeToArray(root));
        sol.printArray(sol.sparseTreeToArray(root));
    }
}

class BTreeNode {
    BTreeNode left, right;
    int val;
    public BTreeNode(int x) {val = x;}
}