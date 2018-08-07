import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StringFollowsOrderOfCharactersInPattern {
    boolean CheckFollowPattern(String input, String pattern) {
        Map<Character, Integer> map = new HashMap<>();
        int index = 1;
        for(char c : pattern.toCharArray()){
            map.put(c, index);
            index++;
        }

        int prvIdx = -1, curIdx;
        for(char c : input.toCharArray()) {
            if(map.containsKey(c)) {
                curIdx = map.get(c);
                if (curIdx < prvIdx) return false;
                prvIdx = curIdx;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        StringFollowsOrderOfCharactersInPattern sol = new StringFollowsOrderOfCharactersInPattern();
        Scanner s = new Scanner(System.in);
        while(true) {
            String input = s.next();
            String pattern = s.next();
            System.out.println(sol.CheckFollowPattern(input, pattern));
        }
    }
}
