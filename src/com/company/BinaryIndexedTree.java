package com.company;

/**
 * Created by yeqing on 5/18/2017.
 */
public class BinaryIndexedTree {
    int[] BIT;
    int[] a;
    public BinaryIndexedTree(int[] nums) {
        a = new int[nums.length];
        BIT = new int[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            update(i, nums[i]);
            a[i] = nums[i];
        }
        for (int i : BIT)
            System.out.print(i + ", ");
        System.out.println();
    }
    public BinaryIndexedTree(int size) {
        BIT = new int[size+1];
        a = new int[size+1];
    }
    private int getParent(int idx) {
        idx = idx - (idx & -idx);
        return idx;
    }
    private int getNext(int idx) {
        idx = idx + (idx & -idx);
        return idx;
    }

    // element in array nums at i is updated with new value val
    // update the BIT correspondingly
    public void update(int i, int val) {
        int delta = val - a[i];
        a[i] = val;
        i = i + 1;
        while (i < BIT.length) {
            BIT[i] += delta;
            i = getNext(i);
        }
    }

    // find the prefix sum of numbers in array from 0 to index idx, inclusive
    private int prefixSum(int idx) {
        idx = idx + 1;
        int sum = 0;
        while (idx > 0) {
            sum += BIT[idx];
            idx = getParent(idx);
        }
        return sum;
    }
    // find the sum of numbers in array nums between indices i and j, inclusive.
    public int sumRange(int i, int j) {
        return prefixSum(j) - prefixSum(i-1);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5};
        BinaryIndexedTree bit = new BinaryIndexedTree(nums);
        System.out.println(bit.sumRange(0, 2));
        bit.update(1, 2);
        System.out.println(bit.sumRange(0, 2));
    }
}
