import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeqing on 8/10/2017.
 */
public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num==null || num.length()==0) return res;
        helper(res, num, "", 0, target, 0, 0);
        return res;
    }

    void helper(List<String> res, String num, String path, int pos, int target, long val, long prv) {
        if (pos == num.length()) {
            if (target == val)
                res.add(path);
            return;
        }

        for (int i = pos; i < num.length(); i++) {
            if (num.charAt(pos) == '0' && i != pos) break; // leading 0 for int with more than 1 digit
            long currVal = Long.parseLong(num.substring(pos, i+1));
            if (pos == 0) {
                helper(res, num, path+currVal, i+1, target, currVal, currVal);
            } else {
                helper(res, num, path+"+"+currVal, i+1, target, val+currVal, currVal);
                helper(res, num, path+"-"+currVal, i+1, target, val-currVal, -currVal);
                helper(res, num, path+"*"+currVal, i+1, target, val-prv+prv*currVal, prv*currVal);
            }
        }
    }
    public static void main(String[] args) {
        ExpressionAddOperators sol = new ExpressionAddOperators();
        String num = "3456237490";
        System.out.println(sol.addOperators(num, 9191).toString());
    }
}
