/**
 * Created by yeqing on 12/15/2017.
 */
public class WaterAndJugProblem {
    /*
    This is a Math problem indeed, every time, we can either empty a filled Jug or fill a empty Jug
    why not empty a partially filled jug or fill a partially filled jug?
    1. if we empty a partially filled jug, 0 water left, no new number was created
    2. if we fill a partially filled jug, we can only empty this jug, or move the water in this jug into another jug
        again this doesn't create a new number

    now the problem is converted into whether there exist some number m and n, such that
    x*m - y*n = z or -x*m + y*n = z
    According to Bezout's lemma, there exist integers a and b, such that x*a+y*b = gcd(x,y)
    and for every a and b, x*a + y*b is a multiply of gcd(x,y)
    therefore we just need to check whether z%gcd(x,y) == 0
    e.g. x=5, y=7, z = 4, gcd(5,7) = 1
    we can have:
    1. 5m - 7n = 4  , the smallest m and n is m=5 and n=3
    2. -5m + 7n= 4  , the smallest m and n is m=2 and n=2
    therefore add 7L of water twice and remove 5L of water twice if the optimal solution
    in case z > max(x,y), e.g z=11
    we consider first generate 4L of water then fill y (7L), or first generate 6L of water then fill x (5L)
    generate 4L of water, optimal solution is m=2, n=2 (discussed above)
    generate 6L of water, we consider 5m - 7n = 6 and -5m + 7n = 6
    the optimal solution of (m,n) is m=4,n=2 and m=3,n=3,
    Therefore the bese solution if first generate 4L of water with m=2,n=2, move this into jug x (5L), then fill jug y (7L)
     */
    public boolean canMeasureWater(int x, int y, int z) {
        if(z > x+y) return false;
        if (z==x || z==y || z==x+y) return true;
        return z%gcd(x,y) == 0;
    }
    private int gcd(int x, int y) { // assume x > y
        if (x%y==0) return y;
        return gcd(y, x%y); // if x < y, will call gcd(y,x)
    }
}
