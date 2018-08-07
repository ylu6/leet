package com.company;

import java.util.*;

/**
 * Created by yeqing on 7/12/2017.
 */
public class SlidingWindowMaximum {
    // best version, time complexity O(n)
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> q = new ArrayDeque<Integer>(); // store index instead of value
        int idx= 0;
        for (int i = 0; i < nums.length; i++) {
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            q.addLast(i);
            while (!q.isEmpty() && i-q.peekFirst()+1 > k) // number of element in q more than k
                q.pollFirst(); // remove the first one in the deque
            if (i >= k-1) // start to save results when i >= k
                res[idx++] = nums[q.peekFirst()];
        }
        return res;
    }

    // v1: use priority queue, time complexity O(n*k)
    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        int[] res = new int[nums.length-k+1];
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(
                (a, b)-> a < b ? 1 : (a > b ? -1 : 0)
        );
        for(int i = 0; i < k-1; i++) {
            maxPQ.add(nums[i]);
        }
        for (int i = k-1, idx=0; i < nums.length; i++, idx++) {
            maxPQ.add(nums[i]);
            res[idx] = maxPQ.peek();
            maxPQ.remove(nums[i-k+1]);
        }
        return res;
    }
    // v2: use TreeMap, time complexity O(n*logk)
    // TreeSet or TreeMap doesn't allow duplicates,
    // therefore use TreeMap<Integer, Integer>, The integer value stores frequency of the key
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        int[] res = new int[nums.length - k + 1];

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i = 0; i < k-1; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (int i = k-1, idx=0; i < nums.length; i++, idx++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            for (int n : map.keySet())
                System.out.print(n + ": " + map.get(n) + " ");
            System.out.println();
            res[idx] = map.lastKey();
            int count = map.get(nums[i-k+1]);
            if (count == 1) map.remove(nums[i-k+1]);
            else map.put(nums[i-k+1], count-1);
        }
        return res;
    }
    public static void main(String[] args) {
        SlidingWindowMaximum sol = new SlidingWindowMaximum();
        int[] nums = {9,10,9,-7,-4,-8,2,-6};
//        for (int n : sol.maxSlidingWindow(nums, 3))
//            System.out.print(n + " ");
        System.out.println();
        for (int n : sol.maxSlidingWindow(nums, 5))
            System.out.print(n + " ");
    }
}
