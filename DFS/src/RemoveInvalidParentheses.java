import java.util.List;
// Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
// ()()), use a counter, if '(', +1, if ')', -1. once we saw counter < 0, we need to remove one ')'
// which one to remove? anyone before current position (includes current position)
// removing consecutive ')' will cause duplicates, e.g. remove idx 3 and 4 will leads to same "()()"

//public class RemoveInvalidParentheses {
//    public List<String> removeInvalidParentheses(String s) {
//
//    }
//}
