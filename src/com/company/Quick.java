package com.company;

import java.util.Random;

/**
 * Created by yeqing on 4/13/2017.
 */
public class Quick {
    public static void exch(int[] nums, int i, int j) {
        if (i==j) return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public static void exch(char[] nums, int i, int j) {
        if (i==j) return;
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    //shuffle an array
    public static void shuffle(int[] nums) {
        Random r = new Random();
        for (int i = nums.length-1; i > 0; i--) {
            int j = r.nextInt(i+1);
            exch(nums, i, j);
        }
    }

    public static int kthSmallest(int[] nums, int k) {
        if (k > nums.length) return -1;
        shuffle(nums);
        return kthSmallest(nums, k, 0, nums.length-1);
    }

    public static int kthSmallest(int[] nums, int k, int lo, int hi) {
        if (lo == hi) return nums[lo];
        int p = partition(nums, lo, hi);
        int local_order = p - lo + 1;
        if (k == local_order) return nums[p];
        else if (k < local_order) return kthSmallest(nums, k, lo, p-1);
        else return kthSmallest(nums, k-local_order, p+1, hi);
    }

    public static int partition(int[] nums, int lo, int hi) {
        int v = nums[lo], i = lo, j = hi+1;
        while (true) {
            while (nums[++i] < v) if (i==hi) break;
            while (nums[--j] > v) if (j==lo) break;
            if (i >= j) break;
            exch(nums, i, j);
        }
        exch(nums, lo, j);
        return j;
    }

    public static int partition(char[] nums, int lo, int hi) {
        char v = nums[lo];
        int i = lo, j = hi+1;
        while (true) {
            while (nums[++i] < v) if (i==hi) break;
            while (nums[--j] > v) if (j==lo) break;
            if (i >= j) break;
            exch(nums, i, j);
        }
        exch(nums, lo, j);
        return j;
    }

    public static void partition3Way(int[] nums, int v) {
        int lt = 0, gt = nums.length-1, i = 0;
        while (i <= gt) {
            if (nums[i] < v) exch(nums, lt++, i++);
            else if (nums[i] > v) exch(nums, i, gt--);
            else i++;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 1, 3, 6, 3, 1, 9, 10, 8};
//        char[] nums = {'E', 'E', 'A', 'S', 'E', 'Y', 'Q', 'U', 'E', 'S', 'T', 'I', 'O', 'N'};
//        int pos = partition(nums, 0, nums.length-1);
//        System.out.println(pos);
//        for (char c : nums) System.out.print(c + ", ");
        int mid = kthSmallest(nums, (nums.length+1)/2);
        System.out.println("median is: " + mid);
        partition3Way(nums, mid);
        for (int n : nums) System.out.print(n + ", ");
    }
}
