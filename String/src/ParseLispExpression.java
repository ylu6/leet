import java.util.*;

public class ParseLispExpression {
    public int evaluate(String expression) {
        // use Map<String, Deque<Integer>> instead of Map<String, Integer>
        // when pass map from outer expression to inner expression, the inner let may override existing key-value pair
        // one solution is copy the map every time "let" occurs, this will slow down the code
        // instead, use a Deque<Integer> in the map to store values
        Map<String, Deque<Integer>> map = new HashMap<>();
        return helper(expression, map);
    }

    // the expression can be a integer, a variable(its value stored in map) or a () enclosed expression
    // (let x 2 x)
    int helper(String expression, Map<String, Deque<Integer>> map) {
        char head = expression.charAt(0);
        if(head == '+' || head == '-' || Character.isDigit(head))
            return Integer.parseInt(expression);
        else if (head != '(')
            return map.get(expression).peekFirst();

        String s = expression.substring(1, expression.length()-1); // remove '(' and ')' from head and tail
        int start = 1+nextIndex(s, 0); // point start to head of next sub-expression, eg. "add xx yy", point to 'x'
        if(s.startsWith("let")) {
            int end;
            List<String> variables = new ArrayList<>();
            while(true) { // generate var:val pairs, and return value of last expression
                end = nextIndex(s, start);
                String var = s.substring(start, end);
                start = end + 1;
                if(start >= s.length()) {
                    int res = helper(var, map);
                    for(String v : variables)
                        map.get(v).pollFirst();
                    return res;
                }
                variables.add(var);
                // read next sub-expression
                end = nextIndex(s, start);
                String val = s.substring(start, end);
                map.putIfAbsent(var, new ArrayDeque<>());
                map.get(var).addFirst(helper(val, map));
                start = end + 1;
            }
        }
        else if (s.startsWith("add")) {
            int end = nextIndex(s, start);
            return helper(s.substring(start, end), map) + helper(s.substring(end+1, s.length()), map);
        }
        else if (s.startsWith("mult")) {
            int end = nextIndex(s, start);
            return helper(s.substring(start, end), map) * helper(s.substring(end+1, s.length()), map);
        }
        return 0; // undefined, should not happen
    }

    int nextIndex(String expression, int start) {
        int end = start+1;
        if(expression.charAt(start)=='(') { // expression starts with '(' and end with ')'
            int count=1;
            while(count>0) {
                if(expression.charAt(end) == '(') count++;
                if(expression.charAt(end) == ')') count--;
                end++;
            }
        } else {
            while(end < expression.length() && expression.charAt(end)!=' ')
                end++;
        }
        return end; // end is out of bound or point to the space after current expression
    }

    public static void main(String[] args) {
        ParseLispExpression sol = new ParseLispExpression();
        System.out.println(sol.evaluate("(let x 2 (mult x 5))"));
//        String exp = "let x 2 (mult x 5)";
//        System.out.println(sol.nextIndex(exp, 8));
//        System.out.println(exp.substring(8, exp.length()));
    }
}
