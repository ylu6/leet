public class MaskingPersonalInformation {
    public String maskPII(String S) {
        int atPos = S.indexOf('@');
        if(atPos > -1) {
            S = S.toLowerCase();
            return S.charAt(0) + "*****" + S.substring(atPos-1);
        }
        S = S.replaceAll("[^0-9]","");
        String[] countryCode = {"", "+*-", "+**-", "+***-"};
        return countryCode[S.length()-10] + "***-***-" + S.substring(S.length()-4);
    }

    public static void main(String[] args) {
        String email1 = "LeetCode@LeetCode.com";
        String email2 = "AB@qq.com";
        MaskingPersonalInformation sol = new MaskingPersonalInformation();
        System.out.println(sol.maskPII(email1));
        System.out.println(sol.maskPII(email2));
        String phone1 = "+111 111 111 1111";
        System.out.println(sol.maskPII(phone1));
    }
}
