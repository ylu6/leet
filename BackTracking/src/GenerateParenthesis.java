import java.util.*;

/**
 * Created by yeqing on 5/26/2017.
 */

public class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        addOne("", 0, 0, n, res);
        return res;
    }
    private void addOne(String str, int open, int close, int n, List<String> res) {
        if (str.length() == 2*n) res.add(str);
        // every time we add one parenthese, it must be either ( or )
        // ( is always possible as long as open is less than n
        // ) is possible only when close is less than open
        if (open < n)
            addOne(str+"(", open+1, close, n, res);
        if (close < open)
            addOne(str+")", open, close+1, n, res);
    }

    public static void main(String[] args) {
        GenerateParenthesis g = new GenerateParenthesis();
        System.out.println(g.generateParenthesis(3));
    }
}
