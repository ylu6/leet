import java.util.Arrays;

public class SplitArrayWithSameAverage {

    public boolean splitArraySameAverage(int[] A) {
        if(A.length==1) return false;

        int sum = 0;
        for(int n : A) sum += n;

        Arrays.sort(A);
        // pick the 1st number from A, then recurse starting from the 2nd number
        return helper(sum, A[0], 1, 0, A);
    }

    private boolean helper(int sumA, int sum, int n, int pos, int[] A) {
        if(n < A.length && (sum*(A.length-n) == (sumA-sum)*n))
            return true;

        for(int idx = pos+1; idx < A.length; idx++) {
            if(helper(sumA, sum+A[idx], n+1, idx, A))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] A = {12, 1, 17, 8, 2};
        SplitArrayWithSameAverage sol = new SplitArrayWithSameAverage();
        System.out.println(sol.splitArraySameAverage(A));
    }
}
