import java.util.HashMap;
import java.util.Map;

public class ImageOverlap {
    // The key idea: how we can encode the 2d delta, such that the delta is unique for any translation
    // e.g. translate down 2, right 3, delta is "2,3"
    // We loop through all the point pairs {Ai, Bj}, if both Ai and Bj is 1,
    // then increase the count for translation (Bj[0] - Ai[0], Bj[1] - Ai[1])
    // The count for each translation can be stored in a HashMap<String, Integer>
    public int largestOverlap(int[][] A, int[][] B) {
        // use Map<Integer, Integer> is much faster than Map<String, Integer>,
        // probably because HashCode of String runs slower
        Map<String, Integer> countMap = new HashMap<>();
        int N = A.length, res = 0;
        for(int rA = 0; rA < N; rA++) {
            for(int cA = 0; cA < N; cA++) {
                if(A[rA][cA] == 0) continue;
                for(int rB = 0; rB < N; rB++) {
                    for(int cB = 0; cB < N; cB++) {
                        if(B[rB][cB] == 0) continue;
                        // N is in the range of 1-30, therefore the delta is in the range of -29 to 29
                        // we can encode the delta by an integer using the following equation
                        //
                        // String deltaKey = (rB-rA)*100 + (cB-cA) // if use Map<Integer, Integer>
                        String deltaKey = (rB-rA) + "," + (cB-cA);
                        int count = countMap.getOrDefault(deltaKey,0) + 1;
                        res = Math.max(res, count);
                        countMap.put(deltaKey, count);
                    }
                }
            }
        }
        return res;
    }
}
