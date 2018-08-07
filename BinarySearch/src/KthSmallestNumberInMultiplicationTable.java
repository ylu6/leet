public class KthSmallestNumberInMultiplicationTable {
    // this is problem can be solved in two approaches
    // 1. use priority queue and m numbers into it, then poll from queue k times to get KthNumber
    // 2. use binary search. For binary search, typically there are two ways
    // one search using index, index range [0, len-1]
    // another search using values, values range min to max, this problem we should search by values
    public int findKthNumber(int m, int n, int k) {
        int lo = 1, hi = m*n;
        while(lo < hi) {
            int mid = lo + (hi-lo)/2;
            int count = 0;
            for(int r = 1; r <= m; r++) {
                count += Math.min(n, mid/r); // number of elements on row r which is <= mid
            }
            // if count==k, can we terminate the search? answer is no,
            // because the value mid may be not in the multiplication table, therefore we need to do search untill lo==hi
            // when count >= k, we cannot set hi=mid-1, because mid is a possible candidate, we cannot exclude it
            if(count >= k) hi = mid;
            else lo = mid+1;
        }
        return hi;
    }
}
