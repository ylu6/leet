package com.company;

/**
 * Created by yeqing on 7/27/2017.
 */
public class MagicalString {
    public int magicalString(int n) {
        if (n < 1) return 0;
        if (n <= 3) return 1;

        int result = 1, tail = 3, countIdx = 2, num = 1;
        int[] magic = new int[n+1];
        magic[0] = 1; magic[1] = 2; magic[2] = 2;

        while (tail < n) {
            for (int i = 0; i < magic[countIdx]; i++) {
                magic[tail] = num;
                if (num==1 && tail < n) result++;
                tail++;
            }
            num ^= 3;
            countIdx++;
        }
        for (int i : magic)
            System.out.print(i + " ");
        return result;
    }
    public static void main(String[] args) {
        MagicalString sol = new MagicalString();
        sol.magicalString(4);
    }
}
