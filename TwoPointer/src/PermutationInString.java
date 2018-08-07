/**
 * Created by yeqing on 8/3/2017.
 */
public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int N1 = s1.length(), N2 = s2.length();
        if (N1 > N2) return false;
        int[] s1Map = new int[26], s2Map = new int[26];
        for (int i = 0; i < N1; i++) { s1Map[s1.charAt(i)-'a']++; }
        printMap(s1Map);

        for (int i = 0; i < N2; i++) {
            s2Map[s2.charAt(i)-'a']++;
//            printMap(s2Map);
            if (i >= N1-1) { // start to collect result
                if (isSame(s1Map, s2Map)) return true;
                System.out.println(i-N1+1);
                s2Map[s2.charAt(i-N1+1)-'a']--;
            }
        }
        return false;
    }
    boolean isSame(int[] map1, int[] map2) {
        for (int i = 0; i < map1.length; i++)
            if (map1[i] != map2[i]) return false;
        return true;
    }
    void printMap (int[] map) {
        for (int i = 0; i < map.length; i++)
            if (map[i] != 0) System.out.print((char)('a'+i) + ":" + map[i] + " ");
        System.out.println();
    }
    public static void main(String[] args) {
        String s1 = "ab", s2 = "eidbaooo";
        PermutationInString sol =  new PermutationInString();
        System.out.println(sol.checkInclusion(s1, s2));
    }
}
