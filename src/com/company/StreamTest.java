package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

/**
 * Created by yeqing on 4/13/2017.
 */
public class StreamTest {
    public static void main(String[] args) {
        String s = "123 456 789";
        List<String> l = Arrays.asList(s.split(" "));
        l.replaceAll(x->new StringBuilder(x).reverse().toString());
        for (String str : l) System.out.print(str + ", ");
        System.out.println(String.join(" ", l));

        //Stream
        System.out.println(
                Stream.of(s.split(" "))
                        .map(w->new StringBuilder(w).reverse().toString())
                        .collect(Collectors.joining(" "))
        );
    }
}
