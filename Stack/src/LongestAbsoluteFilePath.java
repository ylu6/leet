import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by yeqing on 11/22/2017.
 */
public class LongestAbsoluteFilePath {
    public int lengthLongestPath(String input) {
        int maxLen = 0, lenOnStack = 0;
        Deque<Integer> stack = new ArrayDeque<>();

        for (String str : input.split("\n")) {
            int level = str.lastIndexOf("\t")+1;
            while (level < stack.size()) {
                lenOnStack -= stack.pollFirst();    // if a aa aaa is on current path, value on stack is 1,2 3
                // and lenOnStack is 1+2+3 = 6
            }
            if (str.indexOf(".") != -1) { // find a file, len of path: lenOnStack + number of '\' needed + length of the filename
                maxLen = Math.max(maxLen, lenOnStack + stack.size() + str.length() - level);
            } else {
                stack.addFirst(str.length()- level);
                lenOnStack += str.length()-level;
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String input = "ab\nabc";
        System.out.println(input.lastIndexOf("\t"));
        System.out.println("a\t");
        System.out.println(input);
        for (String s : input.split("\n"))
            System.out.println(s);
    }
}
