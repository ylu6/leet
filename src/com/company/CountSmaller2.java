package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.*;

/**
 * Created by yeqing on 5/17/2017.
 * LeetCode
 * creat a int array indices, instead of sorting nums directly, moving elements in indices during the
 * sorting process
 */

public class CountSmaller2 {
    public List<Integer> countSmaller(int[] nums) {
        int[] sorted   = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        // map the original nums array to array with element 0, 1, .... len-1
        // mapped array has same relative order as original nums array
        // therefore countSmaller can be done on the mapped array
        int[] mappedNums = Arrays.stream(nums).map(x->Arrays.binarySearch(sorted, x)).toArray();

        BinaryIndexedTree bit = new BinaryIndexedTree(nums.length);
        List<Integer> res = new LinkedList<Integer>();
        for (int i = nums.length-1; i >= 0; i--) {
            res.add(0, bit.sumRange(0, mappedNums[i]-1));
            bit.update(mappedNums[i], 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 2, 6, 1};
        CountSmaller2 solution = new CountSmaller2();
//        solution.countSmaller(nums);
        for (int i : solution.countSmaller(nums))
            System.out.print(i + ", ");

    }
}
