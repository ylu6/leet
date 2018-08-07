import java.util.*;
/**
 * Created by yeqing on 5/26/2017.
 */
public class RestoreIpAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        int len = s.length();
        if (len > 12 || len < 4) return res;
        restoreIP(s, 0, 1, "", res);
        return res;
    }

    private void restoreIP(String s, int idx, int count, String restored, List<String> res) {
        if (count > 4) {
            if (idx == s.length()) res.add(restored); // process end
            return;
        }

        for (int i = 1; i <= 3; i++) { //length of current ip section, from 1 to 3
            if (idx+i > s.length()) break;
            String sub = s.substring(idx, idx+i);
            if ((i>1 && sub.startsWith("0")) || (i==3 && Integer.parseInt(sub) > 255))
                break; // break is better than continue here
            restoreIP(s, idx+i, count+1, restored + sub + (count==4?"":"."), res);
        }
    }
    public static void main(String[] args) {
        String s = "010010";
        RestoreIpAddresses ip = new RestoreIpAddresses();
        for (String str : ip.restoreIpAddresses(s))
            System.out.println(str);
    }
}
