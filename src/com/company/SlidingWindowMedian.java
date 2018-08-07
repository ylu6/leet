package com.company;

import java.util.TreeMap;

/**
 * Created by yeqing on 7/13/2017.
 */
class DynamicMedian {
    int sizeOfSmall, sizeOfLarge;
    TreeMap<Integer, Integer> small;
    TreeMap<Integer, Integer> large;

    public DynamicMedian() {
        sizeOfSmall = 0;
        sizeOfLarge = 0;
        small = new TreeMap<>();
        large = new TreeMap<>();
    }

    public void add(int n) {
        large.put(n, large.getOrDefault(n, 0)+1); // add to large
        int minOfLarge = large.firstKey();
        removeFromMap(large, minOfLarge);
        addToMap(small, minOfLarge); //add it to small
        sizeOfSmall++;
        rebalance();
    }

    public void remove(int n) {
        if (small.containsKey(n)) {
            removeFromMap(small, n);
            sizeOfSmall--;
        }
        else {
            removeFromMap(large, n);
            sizeOfLarge--;
        }
        rebalance();
    }

    private void rebalance(){
        if (sizeOfLarge -sizeOfSmall > 1) { // move from large to small
            addToMap(small, large.firstKey());
            removeFromMap(large, large.firstKey());
            sizeOfSmall++;
            sizeOfLarge--;
        }
        else if (sizeOfLarge-sizeOfSmall < -1) { // move from small to large
            addToMap(large, small.lastKey());
            removeFromMap(small, small.lastKey());
            sizeOfSmall--;
            sizeOfLarge++;
        }
    }
    public double median() {
        if (sizeOfLarge == sizeOfSmall) {
            return ((double) small.lastKey() + large.firstKey()) / 2.0;
        }
        else {
            return sizeOfSmall > sizeOfLarge ? small.lastKey() : large.firstKey();
        }
    }
    private void removeFromMap(TreeMap<Integer, Integer> map, int n) {
        int count = map.get(n);
        if (count == 1)
            map.remove(n);
        else
            map.put(n, count-1);
    }
    private void addToMap(TreeMap<Integer, Integer> map, int n) {
        map.put(n, map.getOrDefault(n, 0)+1);
    }
    private void printMapKey(TreeMap<Integer, Integer> map) {
        for (int n : map.keySet())
            System.out.print(n + " ");
        System.out.println();
    }
}
public class SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new double[0];
        DynamicMedian dm = new DynamicMedian();
        int idx = 0;
        double[] res = new double[nums.length-k+1];

        for (int i = 0; i < nums.length; i++) {
            dm.add(nums[i]);
            if (i >= k-1) { // start to save results
                res[idx++] = dm.median();
                dm.remove(nums[i-k+1]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SlidingWindowMedian sol = new SlidingWindowMedian();
        int[] nums = {5,5,8,1,4,7,1,3,8,4};
        for (double n : sol.medianSlidingWindow(nums, 8))
            System.out.print(n + " ");
    }
}
