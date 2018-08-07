import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int res = 0;
        char cur, nxt=s.charAt(s.length()-1);
        for(int i = 0; i < s.length()-1; i++) {
            cur = s.charAt(i);
            nxt = s.charAt(i+1);
            if(map.get(cur) < map.get(nxt)) {
                res -= map.get(cur);
            } else {
                res += map.get(cur);
            }
        }
        res += map.get(nxt);
        return res;
    }
}
