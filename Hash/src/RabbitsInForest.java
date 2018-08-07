import java.util.HashMap;
import java.util.Map;

public class RabbitsInForest {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for(int a : answers) {
            res++;
            if(a!=0) {
                if(map.containsKey(a)) {
                    if(map.get(a)==1) map.remove(a);
                    else map.put(a, map.get(a)-1);
                } else
                    map.put(a, a);
            }
        }
        for(int n : map.values()) res += n;
        return res;
    }
}
