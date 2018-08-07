import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yeqing on 1/15/2018.
 */
public class PrimeNumberOfSetBitsInBinaryRepresentation {
    public int countPrimeSetBits(int L, int R) {
        Set<Integer> primeSet = new HashSet<>(Arrays.asList(2,3,5,7,11,13,17,19));
        int res = 0;
        for (int n = L; n <= R; n++) {
            if(primeSet.contains(Integer.bitCount(n))) res++;
        }
        return res;
    }
}
