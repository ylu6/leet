import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by yeqing on 7/14/2017.
 */
public class ValidateIPAddress {
    final static String IPv4_PATTERN =
            "^((([1-9]?\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.){3})" +
            "([1-9]?\\d|1\\d{2}|2[0-4]\\d|25[0-5])$";

//    final static String IPv6_PATTERN = "^([0-9a-fA-F]{1,4})\\:" +
//                    "([0-9a-fA-F]{1,4})\\:" +
//                    "([0-9a-fA-F]{1,4})\\:" +
//                    "([0-9a-fA-F]{1,4})\\:" +
//                    "([0-9a-fA-F]{1,4})\\:" +
//                    "([0-9a-fA-F]{1,4})\\:" +
//                    "([0-9a-fA-F]{1,4})\\:" +
//                    "([0-9a-fA-F]{1,4})$";
    final static String IPv6_PATTERN =
            "^(([0-9a-fA-F]{1,4}:){7})" + "([0-9a-fA-F]{1,4})$";
    public String validIPAddress(String IP) {
        Pattern IPv4 = Pattern.compile(IPv4_PATTERN);
        Pattern IPv6 = Pattern.compile(IPv6_PATTERN);

        if (IPv4.matcher(IP).matches())
            return "IPv4";
        if (IPv6.matcher(IP).matches())
            return "IPv6";
        return "Neither";
    }

    public String validIPAddress2(String IP) {
        if (IP.matches(IPv4_PATTERN))
            return "IPv4";
        if (IP.matches(IPv6_PATTERN))
            return "IPv6";
        return "Neither";
    }



    public static void main(String[] args) {

        ValidateIPAddress sol = new ValidateIPAddress();
//        System.out.println(sol.validIPAddress2("1.11.111.99"));
//        System.out.println(sol.validIPAddress2("01.11.111.99"));
//        System.out.println(sol.validIPAddress2("2001:0db8:85a3:0000:0000:8a2e:0370:7334"));
//        System.out.println(sol.validIPAddress2("20EE:FGb8:85a3:0:0:8A2E:0370:7334"));
        List<String> str = Arrays.asList("a", "b", "c");
        System.out.println(String.join(".", str));
    }
}
