import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yeqing on 7/7/2017.
 * 609. Find Duplicate File in System
 */
public class FindDuplicateFile {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String path : paths) {
            String[] files = path.split(" ");
            String prefix = files[0];
            for (int i = 1; i < files.length; i++) {
                // find the index of '(' and ')' and call substring is much faster than
                // loop through every char of the string and call charAt
                int idx0 = files[i].indexOf('(');
                int idx1 = files[i].indexOf(')');
                String fileName = files[i].substring(0, idx0);
                String content = files[i].substring(idx0+1, idx1);

                map.putIfAbsent(content, new ArrayList<String>());
                map.get(content).add(prefix + "/" + fileName);
            }
        }

        List<List<String>> res = new ArrayList<>();
        for(List<String> p : map.values()) {
            if (p.size()>1) res.add(p);
        }
        return res;
    }
}
