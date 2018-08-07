import java.util.Map;

public class FlipGameII {
    /**
     * @param s: the given string
     * @return: if the starting player can guarantee a win
     */
    public boolean canWin(String s) {
        char[] arr = s.toCharArray();
        return canWin(arr);
    }

    boolean canWin(char[] arr) {
        boolean win;

        for(int i = 1; i < arr.length; i++) {
            if(arr[i] == '-') continue;
            if(arr[i] == arr[i-1]) {
                arr[i] = arr[i-1] = '-'; // flip '++' to '--'
                win = canWin(arr);
                arr[i] = arr[i-1] = '+'; // flip back before return !!!
                if(!win) return true; // with this flip, next player cannot win, this user wins
            }
        }
        return false;
    }

    boolean canWin(char[] arr, Map<String, Boolean> cache) {
        String s = new String(arr);
        if(cache.containsKey(s))
            return cache.get(s);

        boolean win;

        for(int i = 1; i < arr.length; i++) {
            if(arr[i] == '+' && arr[i]==arr[i-1]) {
                arr[i] = arr[i-1] = '-';
                win = canWin(arr, cache);
                arr[i] = arr[i-1] = '+';
                if(!win) {
                    cache.put(s, true);
                    return true;
                }
            }
        }
        cache.put(s, false);
        return false;
    }
}
