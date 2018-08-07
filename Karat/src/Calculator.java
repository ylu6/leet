import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Result {
    int num;
    Map<String, Integer> map;
    public Result(){
        num = 0;
        map = new HashMap<>();
    }
    public Result(int num, String var, Map<String, Integer> dict){
        this.num = num;
        map = new HashMap<>();
        if(!var.isEmpty()) {
            if(dict.containsKey(var)) this.num += dict.get(var);
            else map.put(var, 1);
        }
    }
    void combine(Result other, int sign) {
        num += sign*other.num;
        for(String var : other.map.keySet()) {
            map.put(var, map.getOrDefault(var, 0) + sign*other.map.get(var));
        }
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(num);
        for(String key : map.keySet()) {
            sb.append(map.get(key) > 0 ? '+' : '-').append(key);
        }
        return sb.toString();
    }
}

public class Calculator {
    int nextIdx(String s, int idx) {
        int cnt = 1, i = idx+1;
        for( ; i < s.length(); i++) {
            if(s.charAt(i)=='(') cnt++;
            if(s.charAt(i)==')') cnt--;
            if(cnt == 0) return i;
        }
        return i;
    }
    // Q1, string only contains "+-"
    public int calculate1(String s) {
        int res = 0, num = 0, sign = 1;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) num = num*10 + c-'0';
            if(c == '+' || c == '-' || i == s.length()-1) {
                res += num*sign;
                if(c == '+') sign = 1;
                if(c == '-') sign = -1;
                num = 0;
            }
        }
        return res;
    }

    // Q2, string contains "+-()"
    public int calculate2(String s) {
        int res = 0, num = 0, sign = 1;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) num = num*10 + c-'0';
            if(c == '(') {
                int j = nextIdx(s, i);
                num = calculate2(s.substring(i+1, j));
                i = j;
            }
            if(c == '+' || c =='-' || i == s.length()-1) {
                res += sign*num;
                if(c == '+') sign = 1;
                if(c == '-') sign = -1;
                num = 0;
            }
        }
        return res;
    }


    public String calculate3(String s, Map<String, Integer> map) {
        return cal(s, map).toString();
    }
    // return res as array String[2], arr[0] is the number part, arr[1] is the string part
    public Result cal(String s, Map<String, Integer> map) {
        Result res = new Result(), cur = null;
        int num = 0, sign = 1;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) num = num*10 + c-'0';
            if(Character.isLetter(c)) sb.append(c);
            if(c == '(') {
                int next = nextIdx(s, i);
                cur = cal(s.substring(i+1, next), map);
                i = next;
            }
            if(c=='+' || c=='-' || i==s.length()-1) {
                if(cur == null) {
                    cur = new Result(num, sb.toString(), map);
                }
                res.combine(cur, sign);
                if(c == '+') sign = 1;
                if(c == '-') sign = -1;
                num = 0;
                sb = new StringBuilder();
                cur = null;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Calculator sol = new Calculator();
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> map = new HashMap<>();

//        Result r1 = new Result(1, "abc",-1);
//        System.out.println(r1.toString());
//        Result r2 = new Result(0, "a", 1);
//        Result r3 = new Result(5, "", -1);
//        System.out.println(r1.toString());
//
//        r1.add(r2);
//        r1.add(r3);
//        System.out.println(r1.toString());
                map.put("a", 1);
        map.put("b", 2);
//        while(true) {
//            String s = scanner.next();
//            System.out.println(sol.calculate3(s, map));
//        }
        while(true) {
            String s = scanner.next();
            System.out.println(sol.calculate2(s));
        }
    }
}




