package com.company;

/**
 * Created by yeqing on 5/1/2017.
 */
class TreeNode {
    int val;
    TreeNode next;
    TreeNode(int x) { val = x; }
}

public class MergeSort {
    /**
     * merge two sorted subarray, array 1 in the range of [lo, mid], array2 [mid+1, hi]
     */
    public static void merge(int[] nums, int[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++)
            aux[i] = nums[i];
        int j = lo, k = mid + 1;
        for ( int i = lo; i <= hi; i++) {
            if (j > mid)                    nums[i] = aux[k++];
            else if (k > hi)                nums[i] = aux[j++];
            else if (aux[j] > aux[k])       nums[i] = aux[k++];
            else                            nums[i] = aux[j++];
        }
    }

    /**
     * merge two sorted sub-linkedlist, 1st list in range of [lo, mid], 2nd in range of [mid.next, hi]
     * @param lo TreeNode lo
     * @param mid TreeNode mid
     * @param hi TreeNode hi
     */
    public static void merge(TreeNode lo, TreeNode mid, TreeNode hi) {
        TreeNode cur1 = lo, cur2 = mid.next;

    }

    public static void sort(int[] nums, int[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi-lo)/2;
        sort(nums, aux, lo, mid);
        sort(nums, aux, mid+1, hi);
        merge(nums, aux, lo, mid, hi);
    }
    public static void sort(int[] nums) {
        int[] aux = new int[nums.length];
        sort(nums, aux, 0, nums.length-1);
    }

    public static void sortBU(int[] nums) {
        int N = nums.length;
        int[] aux = new int[N];
        for (int sz = 1; sz < N; sz += sz)
            for (int lo = 0; lo < N-sz; lo += sz+sz)
                merge(nums, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
    }

    /**
     * natrual merge sort. algm4 homework 2.2.16
     * @param nums
     */
    public static void sortBUNatural(int[] nums) {
        int N = nums.length;
        int[] aux = new int[N];
        while (true) {
            int lo = 0, mid = 0, hi = 0; // rescan the array from beginning
            while (lo < N) {
                for (mid = lo; mid < N; mid++)  // find first subarray for merge
                    if (mid+1==N || nums[mid] > nums[mid+1]) break;
                if (mid == N-1) break;          // if mid reached end of array, 2nd subarray doesn't exist, break
                for (hi = mid+1; hi < N; hi++)  // find second subarray for merge
                    if (hi+1==N || nums[hi] > nums[hi+1]) break;
                merge(nums, aux, lo, mid, hi);  // merge 1st and 2nd subarray
                lo = hi+1;                      // update lo and start searching for next subarray for merge
//                for (int n : nums) System.out.print(n + ", ");
//                System.out.println();
            }
            if (lo == 0 && mid == N-1) break; // break when the whole array is sorted
        }
    }

    public static void sortBUNatural(TreeNode head) {
        while (true) {
            TreeNode lo = head, mid = head, hi = head;
            while (lo != null) {
                for (mid = lo; mid != null; mid = mid.next)
                    if (mid.next == null || mid.val > mid.next.val) break;
                if (mid.next == null) break;
                for (hi = mid.next; hi != null; hi = hi.next)
                    if (hi.next == null || hi.val > hi.next.val)    break;
                merge(lo, mid, hi);
                lo = hi.next;
            }
            if (lo == head && mid == null) break;
        }
    }

    public static TreeNode arrayToList(int[] a) {
        TreeNode dummy = new TreeNode(-1), cur = dummy;
        for (int n : a) {
            cur.next = new TreeNode(n);
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{9, 0 , 8, 1, 7, 2, 6, 3, 5, 4};
        TreeNode head = arrayToList(nums);
        for (TreeNode cur = head; cur != null; cur = cur.next)
            System.out.print(cur.val + ", ");
        System.out.println();
        sortBUNatural(nums);
//        for (int n : nums) System.out.print(n + ", ");
    }
}
