package com.company;
import java.util.*;
/**
 * Created by yeqing on 4/16/2017.
 */
public class DecodeString {
    public static  String decodeString(String s) {
        Deque<String> stack = new ArrayDeque<String>();
        Deque<Integer> countStack = new ArrayDeque<Integer>();
        int num = 0;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) num = num*10 + (c-'0');
            if (Character.isAlphabetic(c)) res.append(c);
            if (c == '[') {
                countStack.addLast(num);
                num = 0;
                stack.addLast(res.toString());
                res = new StringBuilder();
            }
            if (c == ']') {
                int count = countStack.pollLast();
                StringBuilder tmp = new StringBuilder(stack.pollLast());
                while (count-- > 0)
                    tmp.append(res);
                res = tmp;
            }
        }
        return res.toString();
    }

    public static void main (String[] args) {
        String s = "3[a]2[bc]";
        System.out.println(decodeString(s));
    }
}
