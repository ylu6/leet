package com.company;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * Created by yeqing on 4/14/2017.
 */
public class Calculator {
    //implement shunting yard algorithm
    //parsing an input string, produce a postfix notation string
    public static Queue<String> shuntingYard(String s) {
        Map<Character, Integer> prec = new HashMap<Character, Integer>();
        prec.put('+',1);
        prec.put('-',1);
        prec.put('*', 2);
        prec.put('/', 2);
        Queue<String> output = new ArrayDeque<String>(); //output queue
        Deque<Character> op = new ArrayDeque<Character>(); //stack to store operators
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                output.add(sb.toString());
                while (!op.isEmpty() && prec.get(c) <= prec.get(op.peekLast()) ) {
                    output.add(String.valueOf(op.pollLast()));
                }
                op.add(c);
                sb = new StringBuilder();
            } else if (Character.isDigit(c)) {
                sb.append(c);
            } else continue;
        }
        output.add(sb.toString()); // add last number
        while (!op.isEmpty()) output.add(String.valueOf(op.pollLast()));
        return output;
    }
    public static int calculateRPN(Queue<String> q) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        Map<String, BinaryOperator<Integer>> ops = new HashMap<String, BinaryOperator<Integer>>();
        ops.put("+", (a, b)->a+b); //store operators into a HashMap : ops<op, BinaryOperator>
        ops.put("-", (a, b)->a-b);
        ops.put("*", (a, b)->a*b);
        ops.put("/", (a, b)->a/b);
        while (!q.isEmpty()) {
            String s = q.poll();
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                int a2 = stack.pollFirst();
                int a1 = stack.pollFirst();
                stack.addFirst(ops.get(s).apply(a1, a2)); //using hashmap is slower than manual coding
//                if (s.equals("-")) stack.addFirst(a1-a2);
//                if (s.equals("*")) stack.addFirst(a1*a2);
//                if (s.equals("/")) stack.addFirst(a1/a2);
            } else {
                stack.addFirst(Integer.parseInt(s));
            }
        }
        return stack.pollFirst();
    }

    public static void main(String[] args) {
        String s = " 1+ 3 -2 * 3 + 10 / 5*2";
        for (String str : shuntingYard(s))
            System.out.print(str + " ");
        System.out.print("\n" + calculateRPN(shuntingYard(s)));
//        calculate(s);
//        System.out.println(calculate(s));
    }
}
