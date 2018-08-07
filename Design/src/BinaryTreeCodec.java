// leetcode: 297. Serialize and Deserialize Binary Tree

import java.util.ArrayDeque;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class BinaryTreeCodec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    // preorder traversal of tree, separate node with ' ', use 'x' for null
    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("x").append(" ");
            return;
        }
        sb.append(root.val).append(" ");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    public TreeNode deserialize(String data) {
        Queue<String> queue = new ArrayDeque<>();
        for(String s : data.split(" ")) queue.add(s);
        return buildTree(queue);
    }

    TreeNode buildTree(Queue<String> queue) {
        String front = queue.poll();
        if(front.equals("x")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(front));
        root.left = buildTree(queue);
        root.right = buildTree(queue);
        return root;
    }

    public TreeNode deserialize(String data, int[] pos) {
        if (data == null || data.length() == 0) return null;
        if (pos[0] >= data.length()) return null;
        if (data.charAt(pos[0]) == 'x') {
            pos[0] += 2;
            return null;
        }
        int num = 0, sign = 1;
        if (data.charAt(pos[0]) == '-') {
            sign = -1;
            pos[0]++;
        }
        while (pos[0] < data.length() && Character.isDigit(data.charAt(pos[0]))) {
            num = num*10 + (data.charAt(pos[0]) - '0');
            pos[0]++;
        }
        pos[0]++; // move to next none space char

        TreeNode currNode = new TreeNode(sign*num);
        currNode.left = deserialize(data, pos);
        currNode.right = deserialize(data, pos);
        return currNode;
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        BinaryTreeCodec codec = new BinaryTreeCodec();
        System.out.println(codec.serialize(root));

        TreeNode head = codec.deserialize(codec.serialize(root));
        System.out.println(codec.serialize(head));

        String input = "1 2 x x 3 4 x x 5 x x ";
        Queue<String> queue = new ArrayDeque<String>();
        for(String s : input.split(" ")) queue.add(s);

        TreeNode root1 = codec.buildTree(queue);
        System.out.println(codec.serialize(root1));
    }
}
