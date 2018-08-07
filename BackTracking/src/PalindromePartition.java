import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeqing on 6/1/2017.
 */
public class PalindromePartition {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backtrack(s, res, new ArrayList<String>());
        return res;
    }

    // [startIdx, endIdx] inclusive
    private void backtrack(String s, List<List<String>> res, List<String> path) {
        if (s.length() == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int len = 1; len <= s.length(); len++) {
            if (isPalindrome(s, 0 , len-1)) {
                path.add(s.substring(0, len));
                backtrack(s.substring(len, s.length()), res, path);
                if (path.size() > 0) path.remove(path.size() - 1);
            }
        }

    }

    private boolean isPalindrome(String s, int startIdx, int endIdx) {
        int mid = (endIdx - startIdx)/2;
        for (int i = 0; i <= mid; i++) {
            if (s.charAt(startIdx + i) != s.charAt(endIdx - i)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "abbab";
        PalindromePartition p = new PalindromePartition();
        for (List<String> list : p.partition(s))
            System.out.println(list.toString());
    }
}
