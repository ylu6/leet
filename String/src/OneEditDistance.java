public class OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        int len1 = s.length(), len2 = t.length();
        if(Math.abs(len1 -len2) > 1) return false;

        for(int i = 0; i < Math.min(len1, len2); i++) {
            if(s.charAt(i) != t.charAt(i)) {
                if(len1 == len2) {
                    if(i == len1 - 1) return true;
                    return s.substring(i+1).equals(t.substring(i+1));
                }
                else if(len1 > len2)
                    return s.substring(i+1).equals(t.substring(i));
                else
                    return s.substring(i).equals(t.substring(i+1));
            }
        }
        return Math.abs(len1-len2)==1;
    }

    public static void main(String[] args) {
        String s = "", t = "a";
        OneEditDistance sol = new OneEditDistance();
        System.out.println(sol.isOneEditDistance(s, t));
    }
}
