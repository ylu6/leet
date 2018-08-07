import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueWordAbbreviation {
    /*
     * @param dictionary: a list of words
     */
    Map<String, Set<String>> abbToStrings;
    public UniqueWordAbbreviation(String[] dictionary) {
        abbToStrings = new HashMap<>();
        for(String s : dictionary) {
            String abb = getAbb(s);
            if(!abbToStrings.containsKey(abb))
                abbToStrings.put(abb, new HashSet<>());
            abbToStrings.get(abb).add(s);
        }
    }

    /*
     * @param word: a string
     * @return: true if its abbreviation is unique or false
     */
    public boolean isUnique(String word) {
        String abb = getAbb(word);
        return (!abbToStrings.containsKey(abb) ||
                (abbToStrings.get(abb).size()==1 &&abbToStrings.get(abb).contains(word)));
    }

    String getAbb(String word) {
        if(word.length() < 2) return word;
        return "" + word.charAt(0) + (word.length()-2) + word.charAt(word.length()-1);
    }
}
