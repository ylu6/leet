import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LetterCasePermutation {
    public List<String> letterCasePermutation(String S) {
        Queue<String> queue = new LinkedList<>();
        queue.add("");
        for(int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            for(int sz = queue.size(); sz > 0; sz--) {
                String curr = queue.poll();
                if(Character.isDigit(c)) queue.add(curr+c);
                else {
                    queue.add(curr + Character.toLowerCase(c));
                    queue.add(curr + Character.toUpperCase(c));
                }
            }
        }
        List<String> res = new ArrayList<>(queue);
        return res;
    }

    public static void main(String[] args) {
        String S = "a1b1";
        LetterCasePermutation sol = new LetterCasePermutation();
        sol.letterCasePermutation(S);
    }
}
