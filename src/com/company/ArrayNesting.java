package com.company;

/**
 * Created by yeqing on 6/28/2017.
 */
public class ArrayNesting {
    public int arrayNesting(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max_len = 0;
        boolean[] visited = new boolean[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            int len = 0;
            while (!visited[i]) {
                visited[i] = true;
                i = nums[i];
                len++;
            }
            if (len > max_len) max_len = len;
        }
        return max_len;
    }

    public static void main(String[] args) {
        int[] nums = {5,4,0,3,1,6,2};
        ArrayNesting sol = new ArrayNesting();
        System.out.println(sol.arrayNesting(nums));
    }
}
