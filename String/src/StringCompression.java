/**
 * Created by yeqing on 11/1/2017.
 */
public class StringCompression {
    public int compress(char[] chars) {
        int fast = 0, slow = 0;
        while (fast < chars.length) {
            int count = 1;
            while (fast+1 < chars.length && chars[fast]==chars[fast+1]) {
                fast++;
                count++;
            }
            chars[slow++] = chars[fast++];
            if (count > 1) {
                String countString = Integer.toString(count);
                for (int i = 0; i < countString.length(); i++) {
                    chars[slow++] = countString.charAt(i);
                }
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        char[] chars = {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        StringCompression sol = new StringCompression();
        System.out.println(sol.compress(chars));
        for (char c : chars)
            System.out.print(c + " ");
    }
}
