import java.util.HashSet;
import java.util.Set;

public class CardFlippingGame {
    // description of the original problem is very confusing
    // The underlying problem is indeed very simple
    // if a card's front and back number is the same, this number is definitely not good
    // use a hashset to store all those numbers
    // for any number not in this hashset, if there is another card has the same number on front side, we just flip it
    // so all of them are good
    // we just need to return the smallest number among them, which is easy
    public int flipgame(int[] fronts, int[] backs) {
        Set<Integer> bad = new HashSet<>();
        for(int i = 0; i < fronts.length; i++) {
            if(fronts[i] == backs[i]) bad.add(fronts[i]); // this number cannot be good
        }

        // all other numbers not in bad set is good
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < fronts.length; i++) {
            if(!bad.contains(fronts[i])) res = Math.min(res, fronts[i]);
            if(!bad.contains(backs[i])) res = Math.min(res, backs[i]);
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
