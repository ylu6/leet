public class MaxChunksToMakeSortedII {

    // search from left to right, once the max of left_subarray <= min of right_subarray
    // the left subarray is a valid chunk
    // to know the min of right subarray for every index, just preprocess the array and store them in an aux array
    public int maxChunksToSorted(int[] arr) {
        int[] mins = new int[arr.length];
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, cnt = 0;
        // preprocess array from right to left
        for(int i = arr.length-1; i >= 0; i--) {
            min = Math.min(min, arr[i]);
            mins[i] = min;
        }
        // scan array from left to right
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]); // search local max in current window
            if (i+1 == arr.length || max <= mins[i+1]) {
                cnt++;
                max = Integer.MIN_VALUE;
            }
        }
        return cnt;
    }
}
