public class ReverseWordsInAStringII {
    /*
    Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
    The input string does not contain leading or trailing spaces and the words are always separated by a single space.
    For example,
    Given s = "the sky is blue",
    return "blue is sky the".
    Could you do it in-place without allocating extra space?
    */
    public void reverseWords(char[] s) {
        int i = 0;
        for(int j = 0; j < s.length; j++) {
            if(s[j] == ' ') {
                reverse(s, i, j-1);
                i = j+1;
            } else if(j == s.length-1) {
                reverse(s, i, j);
            }

        }

        reverse(s, 0, s.length-1);
    }

    void reverse(char[] s, int i, int j) {
        char temp;
        while(i < j) {
            temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        String s = "the sky is a blue";
        char[] arr = s.toCharArray();
        ReverseWordsInAStringII sol = new ReverseWordsInAStringII();
        sol.reverseWords(arr);
        System.out.println(new String(arr));
    }
}
