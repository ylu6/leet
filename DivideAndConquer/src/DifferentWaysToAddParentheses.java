import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yeqing on 8/11/2017.
 */

// note: this code only works for binary operators, if the first char is '-', which is unary operator, results incorrect
public class DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String input) {
        Map<String, List<Integer>> cache = new HashMap<>();
        return diffWaysToCompute(input, cache);
    }
    // use memorized recursion
    public List<Integer> diffWaysToCompute(String input, Map<String, List<Integer>> cache) {
        if (cache.containsKey(input)) return cache.get(input);
        List<Integer> res = new ArrayList<>();
        if (input == null || input.length()==0) return res;
        List<Integer> leftList = new ArrayList<>(), rightList;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c =='*') {
                String left = input.substring(0, i);
                String right = input.substring(i+1);
                leftList = diffWaysToCompute(left, cache);
                rightList = diffWaysToCompute(right, cache);
                for (int l : leftList) {
                    for (int r : rightList) {
                        if (c == '+')
                            res.add(l+r);
                        if (c == '-')
                            res.add(l-r);
                        if (c == '*')
                            res.add(l*r);
                    }
                }
            }
        }
        if (leftList.isEmpty()) // the string input is purely number, no operator
            res.add(Integer.parseInt(input));

        cache.put(input, res);
        return res;
    }

    public static void main(String[] args) {
        String input = "-2-1-1";
        DifferentWaysToAddParentheses sol = new DifferentWaysToAddParentheses();
        System.out.println(sol.diffWaysToCompute(input).toString());
    }
}
