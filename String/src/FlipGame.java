import java.util.ArrayList;
import java.util.List;

public class FlipGame {
    /**
     * @param s: the given string
     * @return: all the possible states of the string after one valid move
     */
    // flip '++' to '--'
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        char[] chars = s.toCharArray();

        for(int i = 1; i < s.length(); i++) {
            if(chars[i] == '-') continue;
            if(chars[i] == chars[i-1]) {
                chars[i-1] = chars[i] = '-';
                res.add(new String(chars));
                chars[i-1] = chars[i] = '+';
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "+----+-++-++--+++-+--+----+-+-+-+++--+++";
        FlipGame sol = new FlipGame();
        for(String str : sol.generatePossibleNextMoves(s)) {
            System.out.println(str);
        }
    }
}
