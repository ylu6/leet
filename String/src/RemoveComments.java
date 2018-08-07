import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeqing on 12/16/2017.
 */
public class RemoveComments {
    // v1, loop through the whole string array and process char one by one
    public List<String> removeComments(String[] source) {
        List<String> result = new ArrayList<>();
        boolean isComment = false;
        for(String line : source) {
            if (line.isEmpty()) continue;
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (isComment) {
                    if(c=='*' && i < line.length()-1 && line.charAt(i+1)=='/') {
                        isComment = false;
                        i++;
                    }
                } else {
                    if(c=='/') {
                        if(i<line.length()-1 && line.charAt(i+1)=='/') {
                            if(sb.length()!=0) result.add(sb.toString());
                            break; // left part of this line is comment
                        }
                        if(i<line.length()-1 && line.charAt(i+1)=='*') {
                            i++; // skip the '*'
                            isComment = true;
                        }
                        else sb.append(c);
                    }
                    else sb.append(line.charAt(i));
                }
            }
            if(sb.length()!=0 && !isComment) result.add(sb.toString());
        }
        return result;
    }
    //v2, regex
    public List<String> removeComments2(String[] source) {
        String[] s = String.join("\n", source).replaceAll("//.*|(/\\*[.\n]*?\\*/)","").split("\n");
        List<String> res = new ArrayList<>();
        for(String line : s) {
            if (!line.isEmpty()) res.add(line);
        }
        return res;
    }
}
