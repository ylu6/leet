import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    // v1, use HashSet to keep track of numbers seen, help detect cycle
    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        seen.add(n);

        while(n > 1) {
            int num = 0;
            while(n > 0) {
                num += (n%10)*(n%10);
                n /= 10;
            }
            if(seen.contains(num)) return false;
            seen.add(num);
            n = num;
        }
        return true;
    }

    // v2, use Floyd cycle detection algm
    public boolean isHappy2(int n) {
        int slow = n, fast = n;
        do {
            slow = digitSquareSum(slow);
            fast = digitSquareSum(digitSquareSum(fast));
        } while (slow != fast);
        if(slow == 1) return true;
        return false;
    }
    int digitSquareSum(int n) {
        int num = 0;
        while(n > 0) {
            num += (n%10)*(n%10);
            n /= 10;
        }
        return num;
    }
}
