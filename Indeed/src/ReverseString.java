import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

/*
Reverse an URL string while keep special characters, such as &, non-tackled.
 */
public class ReverseString {
    String reverse(String url) {
        char[] chars = url.toCharArray();
        int i = 0, j = chars.length-1;
        while(i < j) {
            while(i < j && !Character.isLetterOrDigit(chars[i])) i++; // find next non-special char

            while(i < j && !Character.isLetterOrDigit(chars[j])) j--; // find next non-special char
            if(i == j) break;
            swap(chars, i++, j--);
        }
        return new String(chars);
    }

    void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        String url = "http://google.com&test";
        ReverseString sol = new ReverseString();
        System.out.println(url);
        System.out.println(sol.reverse(url));
        Deque<Integer> q = new LinkedBlockingDeque<>();
    }
}
