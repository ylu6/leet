package com.company;

import java.util.*;

/**
 * Created by yeqing on 4/13/2017.
 */

public class LambdaTest {
    public static boolean isSubset(String s, String sub) {
        int j = 0;
        for (int i = 0; i < sub.length(); i++) {
            if (j==s.length()) return false;
            while (s.charAt(j++) != sub.charAt(i))
                if (j == s.length()) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        String[] d = new String[]{"ale", "apple", "abcde", "monkey", "plea"};
        List<String> l = Arrays.asList(d);
//        Arrays.sort(d, (String s1, String s2)-> s1.length()==s2.length()?s1.compareTo(s2):s2.length()-s1.length());
        l.sort((String s1, String s2)->
                s1.length()==s2.length() ? s1.compareTo(s2) : s2.length()-s1.length());
        for (String s : l) {
            if (isSubset("ab", s)) System.out.println(s);
        }
    }
}
