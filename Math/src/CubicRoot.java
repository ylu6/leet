public class CubicRoot {
    double cubicRoot(double a) {
        double err = 1e-5;
        double lo, hi, mid = 0.0;
        if(a < -1.0) {
            lo = a;
            hi = -1.0;
        }else if(a <= 1.0) {
            lo = -1.0;
            hi = 1.0;
        }
        else {
            lo = 1.0;
            hi = a;
        }
        int count = 0;
        while(true) {
            count++;
            mid = (lo + hi)/2.0;
            double cube = mid*mid*mid;
            if(Math.abs(cube - a) < err) break;
            if(cube > a) hi = mid;
            else lo = mid;
        }
        System.out.println("iterations: " + count);
        return mid;
    }

    // the problem is indeed find the root of equation x^3 - a = 0
    // at iteration N, x is at xN, the tangent line of x^3-a is y = f'(xN)*(x-xN) + f(xN)
    // the x-intercept (y=0) is picked as x_N+1
    // 3*xN^2*(x_(N+1) - xN) + xN^3-a = 0
    // therefore x_N+1 = (2*xN + a/xN^2) / 3
    double cubicRoot2(double a) {
        double err = 1e-5;
        double res = a > 1 ? a/2 : a*2;
        int count = 0;
        while(Math.abs(res*res*res - a) > err) {
            res = (2.0*res + a/(res*res))/3.0;
            count++;
        }
        System.out.println("newton iterations: " + count);
        return res;
    }

    public static void main(String[] args) {
        CubicRoot sol = new CubicRoot();
        System.out.println(sol.cubicRoot(1234567890.312));
        System.out.println(sol.cubicRoot2(1234567890.312));
        System.out.println(sol.cubicRoot(-0.027));
        System.out.println(sol.cubicRoot2(-0.027));
        System.out.println(sol.cubicRoot(0.001));
        System.out.println(sol.cubicRoot2(0.001));
    }
}
