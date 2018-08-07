import java.util.ArrayDeque;
import java.util.Deque;

// supports "+ / ( )"
public class BasicCalculator {
    public int calculate(String s) {
        int sign = 1, num = 0, res = 0;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                num = num*10 + c - '0';
            } else if (c == '+') {
                res += sign*num;
                num = 0;
                sign = 1;
            } else if (c == '-') {
                res += sign*num;
                num = 0;
                sign = -1;
            } else if (c == '(') {
                stack.addFirst(res);
                stack.addFirst(sign);
                res = 0;
                num = 0;// not required, because there will be =/- in front of '('
                sign = 1;
            } else if (c == ')'){ // ')'
                res += sign*num;
                res = stack.pollFirst()*res + stack.pollFirst();
                num = 0;
            }
//            System.out.println("res="+res+", num="+num+",sign=" + sign);
        }
        if(num != 0) res += sign*num;
        return res;
    }

    public static void main(String[] args) {
        String s = " 2-1 + 2 ";
        BasicCalculator sol = new BasicCalculator();
        System.out.println(sol.calculate(s));
    }
}
