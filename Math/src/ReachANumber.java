public class ReachANumber {
    // denote sum of 1 to n as sum(n)
    // first we find the minimum n such that sum(n) >= target, if sum(n) == target, n is the result
    // if sum(n) > target and sum(n) - target is even, x = [sum(n)-target] / 2, we use -x instead of x, which helps reach the target
    // if sum(n) - target is even, there are two cases, n+1 is odd or n+1 is even
    // if n+1 is odd, sum(n) + n - target is now even, again we can flip x, which is [sum(n+1) - target] / 2, results is n+1
    // if n+1 is even, sum(n)-(n+1)+n+2 - target is even, now we can flip [sum(n)+1-target]/2, results is n+2
    // we still need to prove the number x is in range [1, n]
    // we have sum(n-1) < target => sum(n) - n < target => sum(n) - target < n => 2*x < n
    public int reachNumber(int target) {
        target = Math.abs(target);
        long n = (long) Math.ceil((-1+Math.sqrt(8.0*target))/2);
        long sum = n*(n+1)/2;
//        if(sum == target) return (int) n; // included in the statement below
        if((sum-target)%2==0) return (int)n;
        return (n+1)%2==0 ? (int)n+2 : (int)n+1;
    }
}
