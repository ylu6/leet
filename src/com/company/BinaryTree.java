package com.company;

/**
 * Created by yeqing on 4/28/2017.
 */
public class BinaryTree {
    TreeNode head;

    class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    BinaryTree(String s){
        head = this.deserialize(s);
    }
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
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
        int[] pos = new int[1];
        return deserialize(data, pos);
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
        BinaryTree bt = new BinaryTree("-1 2 3 x x 4");
        String str = bt.serialize(bt.head);
        System.out.println(str);
    }
}
