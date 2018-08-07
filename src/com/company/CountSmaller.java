package com.company;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yeqing on 5/17/2017.
 * LeetCode
 * creat a int array indices, instead of sorting nums directly, moving elements in indices during the
 * sorting process
 */

public class CountSmaller {
    public List<Integer> countSmaller(int[] nums) {
        int[] indices   = new int[nums.length];
        int[] counts    = new int[nums.length];
        int[] aux       = new int[nums.length];
        for (int i = 0; i < nums.length; i++) indices[i] = i;

        mergeSortCount(nums, indices, counts, aux, 0, nums.length-1);



        return Arrays.stream(counts).boxed().collect(Collectors.toList());
    }
    private void mergeSortCount(int[] nums, int[] indices, int[] counts, int[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi-lo)/2;
        mergeSortCount(nums, indices, counts, aux, lo, mid);
        mergeSortCount(nums, indices, counts, aux, mid+1, hi);
        merge(nums, indices, counts, aux, lo, mid, hi);

        for (int i : indices)
            System.out.print(nums[i] + ", ");
        System.out.println();
    }

    /**
     * for a number picked from left sub-array (ni), all numbers already scanned in the right sub-array is an inversion with ni
     * if right sub-array is depleted, the whole right sub-array forms inversion with ni
     */
    private void merge(int[] nums, int[] indices, int[] counts, int[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++)  aux[k] = indices[k];
        int i = lo, j = mid+1, k = lo;
        while (i <= mid) {
            while (j <= hi && nums[aux[j]] < nums[aux[i]]) indices[k++] = aux[j++];
            counts[aux[i]] += j - (mid+1);
            indices[k++] = aux[i++];
        }
        while (j <= hi)
            indices[k++] = aux[j++];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 2, 6, 1};
        CountSmaller solution = new CountSmaller();
//        solution.countSmaller(nums);
        for (int i : solution.countSmaller(nums))
            System.out.print(i + ", ");

    }
}
