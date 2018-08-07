/**
 * Created by yeqing on 11/16/2017.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingOffers {
    Map<List<Integer>, Integer> memo = new HashMap<>(); // hashCode of list is a function of list's elements
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return dfs(price, special, needs);
    }

    int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        if (memo.containsKey(needs)) return memo.get(needs); // value in cache
        if (allZero(needs)) return 0;

        int minCost = Integer.MAX_VALUE;
        for (int idx = 0; idx < special.size(); idx++) { // loop through all specials, search for min cost for current needs
            List<Integer> newNeeds = remaining(needs, special.get(idx));
            if (newNeeds==null) continue; // invalid special for current needs

            int remainingCost =dfs(price, special, newNeeds);
            int specialCost = special.get(idx).get(special.get(idx).size()-1);
            minCost = Math.min(minCost, specialCost + remainingCost);
        }

        // in case none of the special can be purchased or buying single item directly is cheaper
        int costWithoutSpecial = 0;
        for (int i = 0; i < needs.size(); i++) {
            costWithoutSpecial += needs.get(i)*price.get(i);
        }
        minCost = Math.min(minCost, costWithoutSpecial);
        memo.put(needs, minCost); // save the min cost for current needs into memo
        return minCost;
    }
    List<Integer> remaining(List<Integer> needs, List<Integer> special) {
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < needs.size(); i++) {
            if (needs.get(i)<special.get(i)) return null;
            res.add(needs.get(i) - special.get(i));
        }
        return res;
    }
    boolean allZero(List<Integer> needs) {
        for (int n : needs) if (n != 0) return false;
        return true;
    }
}
