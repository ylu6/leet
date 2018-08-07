public class MinimumSwapsToMakeSequencesIncreasing {
    // keep[A.length], swap[A.length]
    // keep[i]: the min num of swaps at index i, if i is not swapped, 0 to i is in correct order
    // swap[i]: the min num of swaps at index i if i is swapped, subarray [0,i] is in ascending order
    // at index i, there are there possible cases
    // 1) cannot swap, A[i-1] >= B[i] || B[i-1] >= A[i],
    // 2) can swap, and have to swap, A[i] <= A[i-1] || B[i] <= B[i-1]
    // 3) can swap, but not have to swap,
    public int minSwap(int[] A, int[] B) {
        int[] keep = new int[A.length];
        int[] swap = new int[A.length];
        keep[0] = 0; // at index 0, if not swapped, the number of swaps is 0
        swap[0] = 1; // at index 0, if swapped, the number of swaps is 1

        for(int i = 1; i < A.length; i++) {
            if(A[i-1] >= B[i] || B[i-1] >= A[i]) { // swapping i will break the order
                keep[i] = keep[i-1]; // if i-1 not swapped, don't swap i
                swap[i] = swap[i-1] + 1; // if i-1 swapped, swap i also
            } else if (A[i-1] >= A[i] || B[i-1] >= B[i]) { // can swap, and have to swap
                keep[i] = swap[i-1]; // if i-1 swapped, don't swap i
                swap[i] = keep[i-1] + 1; // if i-1 not swapped, swap i
            } else { // can swap, but not necessary
                keep[i] = Math.min(keep[i-1], swap[i-1]);
                swap[i] = Math.min(keep[i-1], swap[i-1]) + 1;
            }
        }

        return Math.min(keep[A.length-1], swap[A.length-1]);
    }
}
