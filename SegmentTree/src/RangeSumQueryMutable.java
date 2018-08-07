import java.util.Arrays;

/**
 * Created by yeqing on 11/28/2017.
 */

class NumArray {
    int[] tree;
    int[] A;

    public NumArray(int[] nums) {
        int n = nums.length;
        int N = (int) (2*Math.pow(2,Math.ceil(Math.log(n)/Math.log(2))));
        tree = new int[N];
        A = Arrays.copyOf(nums, nums.length);
        if (nums.length > 0) build(1, 0, nums.length-1);
    }

    public void build(int node, int start, int end) {
        if (start==end)  tree[node] = A[start];
        else { // post-order traversal
            int mid = start + (end-start)/2;
            build(2*node, start, mid);
            build(2*node+1, mid+1, end);
            tree[node] = tree[2*node] + tree[2*node+1];
        }
    }
    public void update(int i, int val) {
        A[i] = val;
        update(1, 0, A.length-1, i, val);
    }
    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) tree[node] = val; // leaf node
        else { // post-order traversal
            int mid = start + (end-start)/2;
            if (idx <= mid) update(2*node, start, mid, idx, val);
            else    update(2*node+1, mid+1, end, idx, val);
            tree[node] = tree[2*node] + tree[2*node+1];
        }
    }

    public int sumRange(int i, int j) {
        return sumRange(1, 0, A.length-1, i, j);
    }
    // node: current node in the tree, start-end: range represented by current node
    // l, r : query range
    private int sumRange(int node, int start, int end, int l, int r) {
        // current node's range outside query range
        if (r < start || l > end) return 0;
        // current node's range inside query range
        if (l <= start && r >= end) return tree[node];
        // current node's range has overlap with query range, break down current node by visiting child node
        int mid = start + (end-start)/2;
        int left = sumRange(2*node, start, mid, l, r);
        int right = sumRange(2*node+1, mid+1, end, l, r);
        return left+right;
    }
}
public class RangeSumQueryMutable {
    static void printArray(int[] nums) {
        for (int n : nums) System.out.print(n + " ");
        System.out.println();
    }
    public static void main(String[] args) {
//        NumArray arr = new NumArray(new int[]{-28,-39,53,65,11,-56,-65,-39,-43,97});
        NumArray arr = new NumArray(new int[]{1,3,5});
        printArray(arr.tree);
        System.out.println(arr.sumRange(0,2));
        arr.update(1,2);
        printArray(arr.tree);
        System.out.println(arr.sumRange(0,2));
    }
}


