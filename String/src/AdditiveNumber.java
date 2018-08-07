/**
 * Created by yeqing on 12/16/2017.
 */
public class AdditiveNumber {
    public boolean isAdditiveNumber(String num) {
        if(num==null || num.length()<3) return false;
        String num1, num2;
        // once 1st and 2nd number were selected, the whole additive sequence was determined, we just check whether it is valie
        for(int i = 1; i <= (num.length()-1)/2; i++) { // length of first number
            if(i>1 && num.startsWith("0")) return false;
            num1 = num.substring(0,i);
            for (int j = 1; Math.max(i,j) <= num.length()-i-j; j++) { // length of 2nd number
                if(j>1 && num.charAt(i)=='0') continue; // num2 has more than 1 digits and start with '0', invalid
                num2 = num.substring(i,i+j);
                System.out.println(num1 + " " + num2);
                if (check(Long.parseLong(num1), Long.parseLong(num2), num.substring(i+j)))
                    return true; // if find one valid sequence, return true
            }
        }
        return false; // all possible selections failed, return false
    }
    boolean check(long num1, long num2, String num) {
        if(num.length()==0) return true; // the whole num string was depleted, return true
        long sum = num1+num2;
        String sumStr = String.valueOf(sum);
        int len = sumStr.length();
        return num.startsWith(sumStr) && check(num2, sum, num.substring(len));
    }
    public static void main(String[] args) {
        String num = "000";
        AdditiveNumber sol = new AdditiveNumber();
        System.out.println(sol.isAdditiveNumber(num));
    }
}
