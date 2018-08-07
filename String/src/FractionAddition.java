/**
 * Created by yeqing on 7/11/2017.
 */
public class FractionAddition {
    public String fractionAddition(String expression) {
        String[] tokens = expression.split("(?=[+-])");
        int nu = 0, de = 1;

        for (String token : tokens) {
            if (token.length() == 0) continue;
            String[] nums = token.split("/");
            int n = Integer.parseInt(nums[0]);
            int d = Integer.parseInt(nums[1]);
            nu = nu*d + de*n;
            de = de*d;
        }
        int GCD = Math.abs(gcd(nu, de));
        // nu can be both positive and negative, but de has to be positive
        // transfer the sign of denominator to numerator
        int sign = de > 0 ? 1 : -1;
        return (sign*nu/GCD) + "/" + (sign*de/GCD);
    }

    private int gcd(int x, int y) {
        if (x == 0 || y == 0) return x+y;
        return gcd(y, x%y);
    }

    public static void main(String[] args) {
        FractionAddition sol = new FractionAddition();
//        System.out.println(sol.gcd(4,0));
        System.out.println(sol.fractionAddition("1/3-1/2"));
    }
}
