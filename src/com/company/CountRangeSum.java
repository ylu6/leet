package com.company;

/**
 * Created by yeqing on 5/17/2017.
 * LeetCode
 */
public class CountRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] sums  = new long[nums.length+1];
        long[] aux   = new long[nums.length+1];
        for (int i = 1; i < sums.length; i++)
            sums[i] = sums[i-1] + nums[i-1];
        return mergeSortCount(sums, aux, 0, sums.length-1, lower, upper);
    }
    private int mergeSortCount(long[] sums, long[] aux, int lo, int hi, int lower, int upper) {
        if (lo >= hi) return 0;
        int count = 0;
        int mid = lo + (hi-lo)/2;
        count += mergeSortCount(sums, aux, lo, mid, lower, upper);
        count += mergeSortCount(sums, aux, mid+1, hi, lower, upper);
        count += merge(sums, aux, lo, mid, hi, lower, upper);
        return count;
    }
    private int merge(long[] sums, long[] aux, int lo, int mid, int hi, int lower, int upper) {
        int count = 0;
        for (int k = lo; k <= hi; k++)
            aux[k] = sums[k];
        // index i and j for the regular merge, idxLower and idxUpper keep a window in the right sub-array,
        // for idx in that window, lower <= sums[idx]-sums[i] <= upper
        int k = lo, i = lo, j = mid+1, idxLower = mid+1, idxUpper = mid+1;
        while (i <= mid) {
            while (idxLower <= hi && aux[i]+lower > aux[idxLower]) idxLower++; // when stopped, aux[idxLower] >= aux[i]+lower
            // when stopped, aux[idxUpper] > aux[i] + upper, previous numbers (if any) <= aux[i]
            while (idxUpper <= hi && aux[i]+upper >= aux[idxUpper]) idxUpper++;
            count += idxUpper - idxLower;   // the ranger is [idxLower, idxUpper), therefore count is idxUpper - idxLower

            while (j <= hi && aux[j] < aux[i]) sums[k++] = aux[j++];
            sums[k++] = aux[i++];
        }
        while (j <= hi) sums[k++] = aux[j++];
        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 5, -1};
        CountRangeSum solution = new CountRangeSum();
        System.out.println(solution.countRangeSum(nums, -2, 2));
    }
}
