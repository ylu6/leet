public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int lo = 0, hi = nums.length-1;

        while(lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if(nums[mid] == target) return mid;
            if(nums[mid] < nums[hi]) { // right sub-array is sorted
                if(target > nums[mid] && target <= nums[hi]) lo = mid + 1;
                else hi = mid-1;

            } else { // left sub-array is sorted
                if(target >= nums[lo] && target < nums[mid]) hi = mid-1;
                else lo = mid+1;
            }
        }
        return -1;
    }
}
