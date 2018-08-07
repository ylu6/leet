import java.util.ArrayDeque;
import java.util.Deque;

// supports "+-*/()"
public class BasicCalculatorIII {
    public int calculate(String s) {
        int num = 0, res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        char sign = '+';

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) num = num*10 + c - '0';
            System.out.println("num: " + num);
            if (c == '(') {
                int cnt = 1, j = i+1;
                for( ; j < s.length(); j++) {
                    if(s.charAt(j) == '(') cnt++;
                    if(s.charAt(j) == ')') cnt--;
                    if(cnt == 0) break;
                }
                String substr = s.substring(i+1,j);
                num = calculate(s.substring(i+1, j));
                i = j;
            }
            if (c=='+' || c=='-' || c=='*' || c=='/' || i == s.length()-1) {
                if(sign == '+') stack.addFirst(num);
                if(sign == '-') stack.addFirst(-1*num);
                if(sign == '*') stack.addFirst(stack.pollFirst()*num);
                if(sign == '/') stack.addFirst(stack.pollFirst()/num);
                sign = c;
                num = 0;
            }
        }
        while(!stack.isEmpty()) res += stack.pollFirst();
        return res;
    }

    public static void main(String[] args) {
        String s1 = "2*(5+5*2)/3+(6/2+8)";
        String s2 = "5+5*2";
        BasicCalculatorIII sol = new BasicCalculatorIII();
        System.out.println(sol.calculate(s1));
        System.out.println(sol.calculate(s2));

    }
}
