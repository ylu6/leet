package com.company;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;

/**
 * Created by yeqing on 5/24/2017.
 */
public class BinarySearchTree {
    private int sz;
    private TreeNode root;

    class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    BinarySearchTree() {
        sz = 0;
        root = null;
    }
    BinarySearchTree(String data) {
        int[] treeData = Stream.of(data.split(" ")).mapToInt(Integer::parseInt).toArray();
        sz = treeData.length;
//        root = deserialize(treeData, 0, sz);
        root = deserializeDFS(data);
    }

    // Encodes a tree to a single string.
    // recursive and iterative
//    public String serialize(TreeNode root) {
//        StringBuilder sb = new StringBuilder();
//        serialize(root, sb);
//        return sb.toString();
//    }
//    private void serialize(TreeNode root, StringBuilder sb) {
//        if (root == null) return;
//        sb.append(root.val).append(" ");
//        serialize(root.left, sb);
//        serialize(root.right, sb);
//    }
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        stack.addFirst(root);
        TreeNode curr;
        while (!stack.isEmpty()) {
            curr = stack.pollFirst();
            sb.append(curr.val).append(" ");
            if (curr.right != null) stack.addFirst(curr.right);
            if (curr.left != null) stack.addFirst(curr.left);
        }
        return sb.toString();
    }
    // Decodes your encoded data to tree.
    // worst case time complexity O(N^2)
    public TreeNode deserialize(String data) {
        int[] treeData = Stream.of(data.split(" ")).mapToInt(Integer::parseInt).toArray();
        return deserialize(treeData, 0, treeData.length);
    }
    private TreeNode deserialize(int[] data, int lo, int hi) {
        if (lo >= hi) return null;
        TreeNode head = new TreeNode(data[lo]);
        int i;
//        for (i = lo+1; i < hi; i++) { // if the tree is like a linked list leaning left, time is O(N^2)
//            if (data[i] > data[lo]) break;
//        }
        i = search(data, lo+1, hi, data[lo]);
        head.left = deserialize(data, lo+1, i);
        head.right = deserialize(data, i, hi);
        return head;
    }
    // binary search in the sub-array for the index of first element which is larger than key
    // return hi if not found
    // the search is log(k) of the sub-array size k, but for one tree level, the total time still can be O(N)
    // for tree height of N, worst case time is still O(N^2)

    int search (int[] data, int lo, int hi, int key) { // [lo, hi)
        if (lo >= hi) return hi;
        int mid = lo + (hi-1-lo)/2;
        if (data[mid] >  key) {
            if (mid == lo || data[mid-1] < key) return mid;
            return search(data, lo, mid-1, key);
        }
        else {
            return search(data, mid+1, hi, key);
        }
    }

    // use depth first approach, a pos variable keep track of current decoding number
    // Time Complexity O(N)
    public TreeNode deserializeDFS (String data) {
        // split with regex is very slow, should directly parse string instead
        int[] treeData = Stream.of(data.split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] pos = new int[1];
        //return deserializeDFS(treeData, Integer.MIN_VALUE, Integer.MAX_VALUE, pos);
        return deserializeDFS(data, Integer.MIN_VALUE, Integer.MAX_VALUE, pos);
    }
    // pass lower and upper bound of next child node as reference
    public TreeNode deserializeDFS (int[] treeData, int lo, int hi, int[] pos) {
        System.out.println("current pos: " + pos[0]);
        if (pos[0] >= treeData.length) return null;
        if (treeData[pos[0]] < lo || treeData[pos[0]] > hi) return null;
        TreeNode currNode = new TreeNode(treeData[pos[0]]);
        pos[0]++;
        currNode.left = deserializeDFS(treeData, lo, currNode.val, pos);
        currNode.right = deserializeDFS(treeData, currNode.val, hi, pos);
        return currNode;
    }
    public TreeNode deserializeDFS (String data, int lo, int hi, int[] pos) {
//        System.out.println("current pos: " + pos[0]);
        if (pos[0] >= data.length()) return null;
        int currPos = pos[0], num = 0;
        while (currPos < data.length() && data.charAt(currPos) != ' ') {
            num = num*10 + (data.charAt(currPos) - '0');
            currPos++;
        }
        ++currPos; // move to next null space char
        if (num < lo || num > hi) return null;
        TreeNode currNode = new TreeNode(num);
        pos[0] = currPos;
        currNode.left = deserializeDFS(data, lo, currNode.val, pos);
        currNode.right = deserializeDFS(data, currNode.val, hi, pos);
        return currNode;
    }
    public String toString() {
        return serialize(root);
    }
    public static void main(String[] args) {
        String s = "5 2 1 4 7 8";
        BinarySearchTree bst = new BinarySearchTree(s);
        System.out.println(bst.toString());
    }
}
