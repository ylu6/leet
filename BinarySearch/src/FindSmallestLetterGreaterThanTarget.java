/**
 * Created by yeqing on 12/11/2017.
 */
public class FindSmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        int lo = 0, hi = letters.length-1;
        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if (letters[mid] <= target) lo = mid+1;
            else {
                if (mid == 0 || letters[mid-1] <= target ) {
                    System.out.println(letters[mid-1] + " " + letters[mid]);
                    return letters[mid];
                }
                else hi = mid-1;
            }
        }
        return letters[0];
    }

    public static void main(String[] args) {
        char[] letters = {'c', 'f', 'j'};
        FindSmallestLetterGreaterThanTarget sol = new FindSmallestLetterGreaterThanTarget();
        System.out.println(sol.nextGreatestLetter(letters, 'c'));
    }
}
