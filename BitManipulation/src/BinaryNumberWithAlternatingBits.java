/**
 * Created by yeqing on 10/20/2017.
 */
public class BinaryNumberWithAlternatingBits {
    public boolean hasAlternatingBits(int n) {
        int mask = 1;
        int prv = n & mask;
        n = n >> 1;
        while (n != 0) {
            int cur = n & mask;
            if (prv == cur) return false;
            n = n >> 1;
            prv = cur;
        }
        return true;
    }
}
