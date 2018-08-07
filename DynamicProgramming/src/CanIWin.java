import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yeqing on 8/20/2017.
 */
public class CanIWin {
    // the process is indeed back tracking, with memoization
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int totalSum = 0;
        for (int i = 1; i <= maxChoosableInteger; i++) totalSum += i;
        if (totalSum < desiredTotal) return false;
        if (desiredTotal <= 0) return true;
        Map<String, Boolean> cache = new HashMap<>(); // memoization of subproblem solutions
        boolean[] used = new boolean[maxChoosableInteger+1]; // record whether a number has been used

        return canIWin(cache, used, desiredTotal);
    }
    boolean canIWin(Map<String, Boolean> cache, boolean[] used, int desireTotal) {
        if (desireTotal <= 0) return false; // the previous guy won already
        String key = Arrays.toString(used);
        if (cache.containsKey(key)) return cache.get(key);

        for (int i = 1; i < used.length; i++) { // go through all numbers
            if (!used[i]) { // if number not used
                used[i] = true; // use this number
                // if next player cannot win, then I win
                if(!canIWin(cache, used, desireTotal-i)) {
                    cache.put(key, true);
                    used[i] = false;
                    return true; // I can win, terminate the game
                }
                // cannot win if pick i, reset status
                used[i] = false;
//                printMap(cache);
            }
        }
        // tried all possible numbers, none of them can win
        cache.put(key, false);
        return false;
    }
    void printMap(Map<String, Boolean> map) {
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            System.out.print(entry.getKey() + ":" + entry.getValue() + "  ");
        }
        System.out.println();
    }
    //version 2, convert boolean array to int, instead of use Arrays.toString
    public boolean canIWin2(int maxChoosableInteger, int desiredTotal) {
        int totalSum = 0;
        for (int i = 1; i <= maxChoosableInteger; i++) totalSum += i;
        if (totalSum < desiredTotal) return false;
        if (desiredTotal <= 0) return true;
        Map<Integer, Boolean> cache = new HashMap<>(); // memoization of subproblem solutions
        boolean[] used = new boolean[maxChoosableInteger+1]; // record whether a number has been used

        return canIWin2(cache, used, desiredTotal);
    }
    boolean canIWin2(Map<Integer, Boolean> cache, boolean[] used, int desireTotal) {
        if (desireTotal <= 0) return false; // the previous guy won already
        int key = format(used);
        if (cache.containsKey(key)) return cache.get(key);

        for (int i = 1; i < used.length; i++) { // go through all numbers
            if (!used[i]) { // if number not used
                used[i] = true; // use this number
                // if next player cannot win, then I win
                if(!canIWin2(cache, used, desireTotal-i)) {
                    cache.put(key, true);
                    used[i] = false;
                    return true; // I can win, terminate the game
                }
                // cannot win if pick i, reset status
                used[i] = false;
//                printMap(cache);
            }
        }
        // tried all possible numbers, none of them can win
        cache.put(key, false);
        return false;
    }

    public int format(boolean[] used){
        int num = 0;
        for(boolean b: used){
            num <<= 1;
            if(b) num |= 1;
        }
        return num;
    }
    public static void main(String[] args) {
        CanIWin sol = new CanIWin();
        System.out.println(sol.canIWin2(10,40));
    }
}
