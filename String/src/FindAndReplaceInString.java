import java.util.ArrayList;
import java.util.List;

public class FindAndReplaceInString {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        List<Integer> indexList = new ArrayList<>();
        for(int i = 0; i < indexes.length; i++) indexList.add(i);
        indexList.sort((a,b)->indexes[a]-indexes[b]);

        StringBuilder sb = new StringBuilder();
        int idx0 = 0;
        for(int i : indexList) {
            sb.append(S.substring(idx0, indexes[i]));
            String original = S.substring(indexes[i], indexes[i] + sources[i].length());
            if(original.equals(sources[i])) {
                sb.append(targets[i]);
            } else {
                sb.append(original);
            }
            idx0 = indexes[i] + sources[i].length();
        }
        int lastIdx = indexList.get(indexList.size()-1);
        idx0 = indexes[lastIdx] + sources[lastIdx].length();
        sb.append(S.substring(idx0));
        return sb.toString();
    }

    public static void main(String[] args) {
        FindAndReplaceInString sol = new FindAndReplaceInString();
        String S = "abcd";
        int[] indexes = {0, 2};
        String[] sources = {"ab", "ec"};
        String[] targets = {"eee", "ffff"};

        System.out.println(sol.findReplaceString(S, indexes, sources, targets));
    }
}
