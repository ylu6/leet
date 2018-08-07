package com.company;

import java.util.*;
/**
 * Created by yeqing on 4/15/2017.
 */
public class NextGreater {
    public static int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < 2*len-1; i++) {
            int idx = i%len;
            while (!stack.isEmpty() && nums[idx] > stack.peekFirst())
                map.put(stack.pollFirst(), nums[idx]);
            stack.addFirst(nums[idx]);
        }
        int[] res = new int[len];
        for (int i = 0; i < len; i++)
            res[i] = map.getOrDefault(nums[i], -1);
        return res;
    }

    public static int nextGreaterElement3(int n) {
        char[] digits = String.valueOf(n).toCharArray();
        Deque<Integer> stack = new ArrayDeque<Integer>(); //store index
        int i, idx = -1, len = digits.length;
        for (i = len-1; i >= 0; --i) {
            while (!stack.isEmpty() && digits[i] < digits[stack.peekFirst()]) {
                idx = stack.pollFirst();
            }
            stack.addFirst(i);
            if (idx >= 0) break;
        }
        //swap digits[i] and digits[idx]
        char tmp = digits[i];
        digits[i] = digits[idx];
        digits[idx] = tmp;
        return Integer.parseInt(String.valueOf(digits));
    }
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,1};
        for (int n: nextGreaterElements(nums))
            System.out.println(n);
    }
}
