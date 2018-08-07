public class MaxChunksToMakeSorted {
    // the case with largest number of count: 0, 1, 2, 3, 4, 5
    // value of each element equals to its index
    // for typical cases, e.g. 1, 0, 4, 2, 3, 5
    // we search for max value from left to right, once the max value equals to current index
    // the left part is a valid chunk, because all numbers in left part smaller than right sub-array
    public int maxChunksToSorted(int[] arr) {
        int cnt = 0, maxVal = -1;

        for (int i = 0; i < arr.length; i++) {
            maxVal = Math.max(maxVal, arr[i]);
            if(maxVal == i) {
                cnt++;
                maxVal = -1;
            }
        }
        return cnt;
    }
}
