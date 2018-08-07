import java.util.*;
/**
 * Created by yeqing on 5/26/2017.
 */
public class LetterCombinations {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        String[] map = {"" , "", "abc", "edf", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        helper(digits, "", res, 0, map);
        return res;
    }
    private void helper(String digits, String str, List<String> res, int idx, String[] map) {
        if (idx == digits.length()) {
            res.add(str);
            return;
        }
        int num = digits.charAt(idx)-'0';
        for (int i = 0; i < map[num].length(); i++) {
            helper(digits, str+map[num].charAt(i), res, idx+1, map);
        }
    }

    public static void main(String[] args) {
        String digits = "7";
        LetterCombinations solution = new LetterCombinations();
        for (String s : solution.letterCombinations(digits))
            System.out.print(s + " ");
    }
}
