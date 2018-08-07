import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

// supports "+-*/"
public class BasicCalculatorII {
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 0;
        char sign = '+';
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                num = num*10 + c-'0';
            }
            if(c=='+' || c=='-' || c=='*' || c=='/' || i==s.length()-1) {
                if(sign == '+') {
                    stack.addFirst(num);
                }
                if(sign == '-') {
                    stack.addFirst(-1*num);
                }
                if(sign=='*'){
                    stack.addFirst(stack.pollFirst()*num);
                }
                if(sign=='/') {
                    stack.addFirst(stack.pollFirst()/num);
                }
                sign = c;
                num = 0;
            }
        }
        int res = 0;
        while(!stack.isEmpty()) {
            res += stack.pollFirst();
        }
        return res;
    }

    public static void main(String[] args) {
        BasicCalculatorII sol = new BasicCalculatorII();
        Scanner in = new Scanner(System.in);
        while(true) {
            String str = in.next();
            System.out.println(sol.calculate(str));
        }
    }
}
