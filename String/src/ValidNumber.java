/**
 * Created by yeqing on 12/13/2017.
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * 02e02 => true
 * 0002 => true
 */
public class ValidNumber {
    public boolean isNumber(String s) {
        s = s.trim();
        if(s.length()==0) return false;
        return s.matches("[+-]?(([0-9]?\\.?[0-9]+)|([0-9]+\\.?[0-9]*))([eE][+-]?[0-9]+)?");
    }

    public static void main(String[] args) {
        ValidNumber sol = new ValidNumber();
        System.out.println(sol.isNumber("2.00e11"));
        System.out.println(sol.isNumber("2."));
        System.out.println(sol.isNumber("2.1"));
        System.out.println(sol.isNumber("0.1"));
        System.out.println(sol.isNumber(".1"));
        System.out.println(sol.isNumber("00.1"));
        System.out.println(sol.isNumber("0002.0"));
    }
}
