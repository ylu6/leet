import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeqing on 12/27/2017.
 */
public class IPToCIDR {
    public List<String> ipToCIDR(String ip, int n) {
        List<String> res = new ArrayList<>();
        int[] map = new int[32];
        String[] ips = ip.split("\\.");
        int ipVal = 0;
        for(String str : ips) ipVal = (ipVal<<8) |  Integer.parseInt(str);
        helper(res, ipVal, n);
        return res;
    }

    private void helper(List<String> res, int ip, int n) {
        if (n == 0) return;
        int numOfZeros = Integer.numberOfTrailingZeros(ip);
        int count = Integer.lowestOneBit(ip);
        if (count > n) {
            count = Integer.highestOneBit(n);
            numOfZeros = Integer.numberOfTrailingZeros(count);
        }
        res.add(getCIDR(ip,32-numOfZeros));
        helper(res, ip+count, n - count);
    }

    private String getCIDR(int ip, int lenOfPrefix) {
        int mask = 0xFF;
        String ipString = String.valueOf(ip&mask);
        ip = ip >>> 8;
        for (int i = 0; i < 3; i++) {
            ipString = String.valueOf(ip&mask) + "." + ipString;
            ip = ip >>> 8;
        }
        return ipString + "/" + String.valueOf(lenOfPrefix);
    }
    public static void main(String[] args) {
        IPToCIDR sol = new IPToCIDR();
        int n = 10;
        String ip = "255.0.0.7";
        for (String str : sol.ipToCIDR(ip, n))
            System.out.println(str);
    }
}
