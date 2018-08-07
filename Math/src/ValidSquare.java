import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yeqing on 8/31/2017.
 */
public class ValidSquare {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        TreeMap<Long, Integer> map = new TreeMap<>();
        long[] dists =  {dist(p1,p2), dist(p1,p3), dist(p1,p4), dist(p2,p3), dist(p2,p4), dist(p3, p4)};
        for (long d : dists) map.put(d, map.getOrDefault(d, 0)+1);
        if (map.size()!=2) return false;
        long edgeL = Long.MAX_VALUE;
        for(long l : map.keySet()) edgeL = Math.min(edgeL, l);
        return map.get(edgeL) == 4;
    }
    long dist(int[] p1, int[] p2) {
        return (long) (Math.pow(p2[0]-p1[0], 2) + Math.pow(p2[1]-p1[1], 2));
    }
}
