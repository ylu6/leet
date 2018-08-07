/**
 * Created by yeqing on 8/30/2017.
 */
public class LongestRepeatingCharacterReplacement {

    // use a sliding window, and a global maxCount to store maximum counts of same char in the sliding window
    // to satisfy the requirement, k >= winSize - maxCount
    // starts from index 0 and window size of 1, use array count[26] to store char counts in the window
    public int characterReplacement(String s, int k) {
        int maxCount = 0, start = 0, end = 0;
        int[] count = new int[26];

        while(end < s.length()) {
            int charIdx = s.charAt(end) - 'A';
            count[charIdx]++;
            if (count[charIdx] > maxCount) maxCount = count[charIdx]; // check maxCount when new char added
            if (k < end-start+1 - maxCount) { // current window doesn't meet the requirement
                count[s.charAt(start)-'A']--; // reduce the window size by 1, update the char count
                start++;
            }
            end++;
        }

        return end-start;
    }
}
