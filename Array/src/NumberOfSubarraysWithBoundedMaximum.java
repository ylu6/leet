public class NumberOfSubarraysWithBoundedMaximum {
    //iterate through the array, find the number of valid sub-arrays ended at each index

    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int left = -1, right = -1, count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > R) left = i;
            if (A[i] >= L) right = i;
            count += right - left;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] A = {2,9,2,5,6};
        NumberOfSubarraysWithBoundedMaximum sol = new NumberOfSubarraysWithBoundedMaximum();
        System.out.println(sol.numSubarrayBoundedMax(A, 2, 8));
    }
}
