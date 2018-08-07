import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpecialBinaryString {
    // a special String is like a valid parentheses
    // e.g. 1 10 1100 0 is like ( () (()) ), so special string always starts with 1 and ends with 0
    // so Stirng S can be broke into several parts 1M0, 1M0, 1M0....
    // just sort those parts by reverse lexi order
    // recursively call every middle substring M
    public String makeLargestSpecial(String S) {

        List<String> toks = new ArrayList<>();
        int count = 0, start = 0;
        for(int i = 0; i < S.length(); i++) {
            if(S.charAt(i)=='1') count++;
            else count--;
            if(count==0) { // found one valid sub SpecialBinaryString
                if(start+1 == i) toks.add("10"); // the subString is "10"
                else toks.add("1" + makeLargestSpecial(S.substring(start+1, i)) + "0");
                start = i+1; // reset start index
                count = 0; // reset count;
            }
        }
        toks.sort(Collections.reverseOrder());
        return String.join("", toks);
    }
}
