import java.util.ArrayList;
import java.util.List;

public class AmbiguousCoordinates {
    public List<String> ambiguousCoordinates(String S) {
        int n = S.length();
        List<String> res = new ArrayList<>();
        for(int i = 2; i < n-1; i++) {
            List<String> X = helper(S.substring(1, i));
            List<String> Y = helper(S.substring(i, n-1));
            for(String x : X) {
                for(String y : Y) {
                    res.add("(" + x + " ," + y + ")");
                }
            }
        }
        return res;
    }

    // return all a list of all possible numbers represented by S
    // 1234: 1234, 1.234, 12.34, 123.4
    // there are 5 cases:
    // S == "" || "0xxx0", return {}
    // S == "0xxxx" return 0.xxxx
    // S == "xxx0" || S.length() == 1  return S
    // otherwise, return xxxx, x.xxx, xx.xx, xxx.x
    List<String> helper(String S) {
        int n = S.length();
        List<String> res = new ArrayList<>();
        if(n==1 || (n > 1 && S.charAt(n-1)=='0' && S.charAt(0)!='0')) {
            res.add(S);
            return res;
        }
        if(n==0 || (S.charAt(0)=='0' && S.charAt(n-1)=='0'))
            return res;
        if(n > 1 && S.charAt(0) == '0') {
            res.add("0." + S.substring(1));
            return res;
        }

        res.add(S);
        for(int i = 1; i < n; i++) {
            res.add(S.substring(0, i) + "." + S.substring(i));
        }
        return res;
    }

    public static void main(String[] args) {
        String S1 = "(0123)";
        String S2 = "(00011)";
        AmbiguousCoordinates sol = new AmbiguousCoordinates();
        System.out.println(sol.ambiguousCoordinates(S1).toString());
        System.out.println(sol.ambiguousCoordinates(S2).toString());

    }
}
