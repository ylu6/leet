public class DecodeWays {
    public int numDecodings(String s) {
        char c1 = '0', c2;
        int prv1 = 0, prv2 = 1, cur = 0;

        for(int i = 0; i < s.length(); i++) {
            c2 = s.charAt(i);

            int code = 10*(c1 - '0') + (c2 - '0');
            cur = 0;
            if(c2 > '0' && c2 <= '9') cur += prv2;
            if(code >= 10 && code <= 26) cur += prv1;

            prv1 = prv2;
            prv2 = cur;
            c1 = c2;
        }
        return cur;
    }
}
