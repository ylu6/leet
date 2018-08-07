import java.util.ArrayDeque;
import java.util.Deque;

// assume we only use # for comment, python doesn't support block comment like /* */
public class ValidPythonCode {
    boolean isValid(String[] input) {
        // initial check
        if(input == null || input.length == 0) return true;
        int start = findFirstLine(input);
        if(start == input.length) return true; // the whole input lines are either empty or comments
        if(input[start].charAt(0) == ' ') return false; // return false if the first line

        // initialize vars for the first valid code line
        Deque<Integer> stack = new ArrayDeque<>(); // stack store indentation
        stack.addFirst(0);  // indentation of first valid line is 0
        String line = removeComment(input[start]).trim();
        char prvTail = line.charAt(line.length()-1);

        for(int i = start+1; i < input.length; i++) {
            line = input[i];
            if(isEmptyLine(line)) continue; // this line is empty, continue
            int spaces = countSpace(line); // number of leading zero
            if(prvTail == ':') {
                if(stack.peekFirst() >= spaces) return false; // same block
                stack.addFirst(spaces);
            } else {
                while(!stack.isEmpty() && stack.peekFirst() > spaces) { // indeed no need to check isEmpty, because spaces cannot be less than 0
                    stack.pollFirst();
                }
                if(stack.peekFirst() != spaces) return false;
            }
            line = removeComment(line).trim();
            prvTail = line.charAt(line.length()-1);
        }

        return prvTail != ':'; // the last char of the python code (without comments) is ':', therefore invalid
    }

    // return true if a line contains only comment or white spaces
    boolean isEmptyLine(String line) {
        if(removeComment(line).trim().isEmpty()) return true;
        return false;
    }
    // remove comment from a line of code
    String removeComment(String line) {
        int idx = line.indexOf('#');
        if(idx != -1) return line.substring(0, idx);
        return line;
    }
    // preprocess the input, skip beginning comment lines and empty lines
    // return the index of the first line which contains code
    int findFirstLine(String[] input) {
        for(int i = 0; i < input.length; i++) {
            if(!isEmptyLine(input[i])) return i;
        }
        return input.length;
    }
    // return number of leading spaces
    int countSpace(String s) {
        int i = 0;
        while(i < s.length() && s.charAt(i) == ' ') {
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        ValidPythonCode sol = new ValidPythonCode();
        System.out.println(sol.countSpace("a"));
        System.out.println("abc".substring(0,0));

        String[] input = {
                "   ",
                "  #comment",
                "x = 0 # define x",
                "if x == 1:",
                "  print x",
                "  if 1==1:",
                "    y=1:",
                "      z=1",
                "#print x:"
        };
        System.out.println(sol.isValid(input));
    }
}
