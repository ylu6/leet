public class ConsecutiveNumbersSum {
    // suppose N can be split into n numbers, and the first number is a
    // we have N == (a + a + n - 1)*n/2
    // a = (2*N-n*(n-1))/2n >= 1
    // therefore we know n*(n+1) <= 2N
    // we also know n == 1 is always good, therefore start from n = 2
    public int consecutiveNumbersSum(int N) {
        int count = 1;
        for(int n = 2; n*(n+1) <= 2*N; n++) {
            if( (N-n*(n-1)/2) % n == 0 ) count++;
        }
        return count;
    }
}
