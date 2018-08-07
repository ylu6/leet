public class GlobalAndLocalInversions {
    public boolean isIdealPermutation(int[] A) {
        int prvMax = -1, curMaxIdx = 0;

        for (int i = 0; i < A.length; i++) {
            if(A[i] > A[curMaxIdx]) {
                prvMax = A[curMaxIdx];
                curMaxIdx = i;
            } else {
                if(i-curMaxIdx > 1 || A[i] < prvMax) return false;
            }
        }
        return true;
    }
}
