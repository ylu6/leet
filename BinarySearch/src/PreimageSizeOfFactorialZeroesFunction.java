public class PreimageSizeOfFactorialZeroesFunction {
    // trailingZeros(x) = K, find the number of x
    // x can be in the range [0, Integer.MAX_VALUE]
    // for a given K number, there are a segment of integer values which satisfy trailingZeros(x) == k
    // do two binary search to find the upper and lower bound of this segment
    // right - left + 1 is the answer
    // in case there is no such x,
    // e.g. 21, 22, 23, 24 has 4 zeros, 25, 26, 27, 28 has 6 zeros
    // using the following algm, right will be at index 24, and left at 25, therefore 24-25+1 => 0
    // which gives the correct result
    public int preimageSizeFZF(int K) {
        long left, right;
        long lo = 0, hi = Integer.MAX_VALUE, mid;

        while(lo < hi) {
            mid = lo + (hi-lo+1)/2;
            long zeros = trailingZeros(mid);
            if(zeros <= K) lo = mid;
            if(zeros > K) hi = mid-1;
        }
        right = hi;

        lo = 0; hi = Integer.MAX_VALUE;
        while(lo < hi) {
            mid = lo + (hi-lo)/2;
            long zeros = trailingZeros(mid);
            if(zeros >= K) hi = mid;
            if(zeros < K) lo = mid + 1;
        }
        left = lo;
        System.out.println(left + ": " + right);
        return (int) (right - left + 1);
    }

    long trailingZeros(long N) {
        if(N < 5) return 0;
        return N/5 + trailingZeros(N/5);
    }

    public static void main(String[] args) {
        PreimageSizeOfFactorialZeroesFunction sol = new PreimageSizeOfFactorialZeroesFunction();
        System.out.println(sol.trailingZeros(0));
        System.out.println(sol.preimageSizeFZF(0));
//        for(int i = 0; i < 30; i++) {
//            System.out.println(sol.trailingZeros(i));
//        }
        System.out.println(sol.preimageSizeFZF(5));
        System.out.println(sol.preimageSizeFZF(1000000000));
    }
}
