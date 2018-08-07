package com.company;

/**
 * Created by yeqing on 5/16/2017.
 * LeetCode
 */

/**
 * Count number of important inversions in an array
 * important inversion definition: i < j and nums[i]> 2*nums[j]
 */
public class CountInversions {
    public int numOfInversion(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] aux = new int[nums.length];
        return mergeSortCount(nums, aux, 0, nums.length-1);
    }

    public int mergeSortCount(int[] nums, int[] aux, int lo, int hi) {
        if (hi <= lo) return 0;
        int count = 0;
        int mid = lo + (hi-lo)/2;
        count = mergeSortCount(nums, aux, lo, mid) + mergeSortCount(nums, aux, mid+1, hi);
        for (int n : nums)
            System.out.print(n + ", ");
        System.out.println();
        System.out.println("Count before merge: " + count);
        count += merge(nums, aux, lo, mid, hi);
        System.out.println("Count after merge: " + count);
        return count;
    }

    /**
     * Merge is the tricky part of this algorithm, the original merge procedure in algs4 doesn't work
     * The idea is that for every new left-subarray number, search for all the important
     * inversion in the right sub-array
     * Therefore the merge part is modified as below:
     */
    public int merge(int[] nums, int[] aux, int lo, int mid, int hi) {
        int count = 0;
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++)
            aux[k] = nums[k];
        int k = lo, idx = mid+1;
        while (i <= mid) {
            while (idx <= hi && aux[i] > 2L*aux[idx]) idx++;
            // all numbers from mid+1 to idx form important inversion with aux[i]
            // every time i increases, all the already scanned numbers in right sub-array are still
            // valid important inversion, just continue to scan the left numbers in right sub-array
            // so the cost for merge is linear
            count += idx - (mid+1);

            while (j <= hi && aux[j] < aux[i]) nums[k++] = aux[j++];
            nums[k++] = aux[i++];
        }
        while (j <= hi) nums[k++] = aux[j++];
        return count;
    }

    public static void main(String[] args) {
        CountInversions solution = new CountInversions();
        int[] nums1 = new int[]{-5,-5};
        int[] nums2 = new int[]{2,4,3,5,1};
//        System.out.println(solution.numOfInversion(nums1));
        System.out.println(solution.numOfInversion(nums2));
        for (int i : nums2) System.out.print(i + ", ");
    }
}
