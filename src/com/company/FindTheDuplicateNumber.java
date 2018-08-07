package com.company;

/**
 * Created by yeqing on 6/29/2017.
 */
public class FindTheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        int slow= 0, fast = 0;
        while (true) { // find first meet point
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) break;
        }
        System.out.println("slow: " + slow + " fast: " + fast);
        slow = 0; // move slow back to 0, fast continue at meeting point
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast]; //this time fast also moves 1 step a time
        } // now slow and fast meet at the entry point of the circle
        return slow;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2};
        FindTheDuplicateNumber sol = new FindTheDuplicateNumber();
        System.out.println(sol.findDuplicate(nums));
    }
}
