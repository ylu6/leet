package com.company;

import java.util.Arrays;

/**
 * Created by yeqing on 4/20/2017.
 */
public class BinarySearch {
    public static boolean isPerfectSquare(int num) {
        if (num == 0 || num == 1) return true;
        int lo = 0, hi = num;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (mid * mid == num) return true;
            else if (mid * mid < num) lo = mid + 1;
            else hi = mid - 1;
        }
        return false;
    }

    /**
     * return the kth element from two sorted arrays
     * @param nums1 1st sorted array
     * @param nums2 2nd sorted array
     * @return      the k-th element of the two sorted array
     */
    public static int kthElement(int[] nums1, int[] nums2) {
        return 0;
    }

    static int[] test () {
        return new int[]{1, 2};
    }
    public static void main(String[] args) {
//        System.out.print(isPerfectSquare(2147483647));
    // test Arrays.binarySearch
        int[] nums = new int[]{1, 2, 4, 7, 10};
        int idx = Arrays.binarySearch(nums, 0, 3, 3);
        System.out.println("idx: " + idx);
        System.out.println("idx: " + -1*(idx+1));

    }



}
