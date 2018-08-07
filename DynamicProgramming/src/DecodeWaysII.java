import java.util.Stack;

public class DecodeWaysII {
    // number of ways to decode input, like "102*"
    // use four int variable, prv, prv1, prv2, cur
    // cur: num of decoding after processing current char
    // prv: num of decoding after processing previous char
    // prv1: num of decoding after processing previous char and prv char happens to be '1'
    // prv2: num of decoding after processing previous char and prv char happens to be '2'
    // so, prv1 = prv if prv_char=='1'/'*', prv2 = prv if prv_char=='2'/'*', otherwise prv1 = prv2 = 0
    public int numDecodings(String s) {
        int M = (int) 1e9 + 7;
        int cur = 1, prv = 1, prv1 = 0, prv2 = 0;
        for(char c : s.toCharArray()) {
            if(c == '*') {
                cur = 9*prv + 9*prv1 + 6*prv2;
                prv1 = prv;
                prv2 = prv;
                prv = cur;
            } else {
                cur = (c > '0' ? 1 : 0)*prv + (c=='1' ? 1 : 0)*prv1 + (c=='2' ? 1 : 0)*prv2;
                prv = cur;
                prv1 = c == '1' ? cur : 0;
                prv2 = c == '2' ? cur : 0;
            }
            cur = cur%M;
            if(cur == 0) return cur; // impossible to decode ths string, which happens when c=='0' and prv_char != '1'/'2'/'*'
        }
        return cur;
    }

    public int numDecodings2(String s){
        int M = (int) (1e9 + 7);
        long[] dp = new long[s.length()+1]; // use long to avoid integer overflow, because of 9*dp or 6*dp
        dp[0] = 1;
        if(s.charAt(0) == '0') return 0;
        dp[1] = s.charAt(0)=='*' ? 9 : 1;
        for(int i = 2; i < dp.length; i++) {
            char c = s.charAt(i-1), cPrv = s.charAt(i-2);
            if(c == '*') {
                dp[i] += 9*dp[i-1]; // decode c as 1-9
                dp[i] += (cPrv=='1'||cPrv=='*' ? 9 : 0)*dp[i-2]; // decode c with previous 1 or *, as 11-19
                dp[i] += (cPrv=='2'||cPrv=='*' ? 6 : 0)*dp[i-2]; // decode c with previous 2 or *, as 21-26
            } else {
                dp[i] += c > '0' ? dp[i-1] : 0; // decode c alone
                dp[i] += cPrv=='1'||cPrv=='*' ? dp[i-2] : 0; // decode c with previous 1 or '*'
                dp[i] += (cPrv=='2'||cPrv=='*') && c<='6' ? dp[i-2] : 0; // decode c with previous 2 or '*'
            }
            dp[i] %= M;
        }
        return (int) dp[s.length()];
    }

    public static void main(String[] args) {
        DecodeWaysII sol = new DecodeWaysII();
        System.out.println(sol.numDecodings2("1*"));
        System.out.println(sol.numDecodings2("**"));
        System.out.println(sol.numDecodings2("**********"));
        System.out.println(sol.numDecodings2("**********1111111111")); //133236775
    }
}
