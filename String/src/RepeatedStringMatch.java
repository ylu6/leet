/**
 * Created by yeqing on 11/1/2017.
 */
public class RepeatedStringMatch {
    public int repeatedStringMatch(String A, String B) {
        int aLen = A.length(), bLen = B.length(), count = 0;
        // length of formed string should be >= B.length()
        int minCount = bLen%aLen == 0 ? bLen/aLen : bLen/aLen+1;

        StringBuilder sb = new StringBuilder();
        while (count < minCount) {
            sb.append(A); // build the initial SB
            count++;
        }

        while(sb.indexOf(B) == -1) {
            if (sb.length() - bLen > aLen) return -1;
            sb.append(A);
            count++;
        }
        return count;
    }
}
