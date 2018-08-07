import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CustomSortString {
    public String customSortString(String S, String T) {
        if(S.length() <= 1) return T;
        int[] map = new int[26];
        Arrays.fill(map, 26);
        for(int i = 0, val = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            map[c-'a'] = val;
            val++;
            while(i < S.length()-1 && c == S.charAt(i+1)) i++;
        }
        List<Character> chars = T.chars().mapToObj(c->new Character((char) c)).collect(Collectors.toList());
        Collections.sort(chars, (a, b)->map[a-'a'] - map[b-'a']);
        return chars.stream().map(String::valueOf).collect(Collectors.joining());
    }

    public static void main(String[] args) {
        String S = "cba";
        String T = "abcd";
        CustomSortString sol = new CustomSortString();
        System.out.println(sol.customSortString(S,T));
    }
}
