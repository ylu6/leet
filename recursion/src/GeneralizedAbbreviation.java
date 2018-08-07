import java.util.ArrayList;
import java.util.List;

public class GeneralizedAbbreviation {
    /**
     * @param word: the given word
     * @return: the generalized abbreviations of a word
     */
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<String>();
        helper(word, 0, res, new ArrayList<String>());
        return res;
    }

    void helper(String word, int pos, List<String> res, List<String> tempList) {
        if(pos == word.length()) {
            res.add(String.join("", tempList));
            return;
        }
        helper(word, pos+1, res, nextList(tempList, word, pos, true));
        helper(word, pos+1, res, nextList(tempList, word, pos, false));
    }

    List<String> nextList(List<String> list, String word, int pos, boolean keep) {
        List<String> newList = new ArrayList<>(list);
        if(keep)
            newList.add(""+word.charAt(pos));
        else if (list.size()==0 || Character.isLetter(list.get(list.size()-1).charAt(0))) {
            newList.add("1");
        } else {
            newList.remove(newList.size() - 1);
            newList.add("" + (Integer.valueOf(list.get(list.size() - 1)) + 1));
        }
        return newList;
    }

    public static void main(String[] args) {
        GeneralizedAbbreviation sol = new GeneralizedAbbreviation();
        System.out.println(sol.generateAbbreviations("word").toString());
    }
}
