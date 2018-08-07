public class ExpressiveWords {
    // heeeellooo
    // heello
    // two pointer, one for each array, scan from left to right
    // if c1 == c2, count number of same char
    // if numC1 == numC2 || numC1 - numC2 >= 2, continue
    // otherwise, return false
    // in the end, both pointer should point to the end of the string, otherwise return false
    public int expressiveWords(String S, String[] words) {
        int res = 0;
        for (String word : words) {
            int i, j;
            boolean flag = true;
            for (i = 0, j = 0; i < S.length() && j < word.length(); i++, j++) {
                if(S.charAt(i) != word.charAt(j)) {
                    flag = false;
                    break; // not matching, break
                }
                int numC1 = 0, numC2 = 0;
                while(i+1 < S.length() && S.charAt(i+1) == S.charAt(i)) {
                    i++;
                    numC1++;
                }
                while(j+1 < word.length() && word.charAt(j+1) == word.charAt(j)) {
                    j++;
                    numC2++;
                }
                if(numC1 != numC2 && numC1 - numC2 < 2) {
                    flag = false;
                    break; // impossible to match this segment
                }
            }
            if(flag && i == S.length() && j == word.length()) {
                System.out.println(word + " matches ");
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String S = "dddiiiinnssssssoooo";
        String[] words = {"dinnssoo","ddinso","ddiinnso","ddiinnssoo","ddiinso","dinsoo","ddiinsso","dinssoo","dinso"};
        ExpressiveWords sol = new ExpressiveWords();
        System.out.println(sol.expressiveWords(S, words));
    }
}
