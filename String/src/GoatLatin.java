import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class GoatLatin {
    public String toGoatLatin(String S) {
        String[] words = S.split(" ");
        Character[] vowels = {'a','e','i','o','u','A','E','I','O','U'};
        HashSet<Character> set = new HashSet<Character>(Arrays.asList(vowels));
        List<String> out = new LinkedList<>();
        for(int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder();
            if(set.contains(words[i].charAt(0)))
                sb.append(words[i]);
            else
                sb.append(words[i].substring(1)).append(words[i].charAt(0));
            sb.append("ma");
            for(int n = 0; n <= i; n++) sb.append('a');
            out.add(sb.toString());
        }
        return String.join(" " , out);
    }

    public static void main(String[] args) {
        String S = "I speak Goat Latin";
        GoatLatin sol = new GoatLatin();
        System.out.println(sol.toGoatLatin(S));
        String[] test = {"a", "b", "c"};
        System.out.println(String.join(" ", test));
    }
}
