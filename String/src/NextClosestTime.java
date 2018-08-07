import java.util.HashSet;
import java.util.Set;

/**
 * Created by yeqing on 11/2/2017.
 */
public class NextClosestTime {
    // solution 1, start from original time, add 1 min every step, until find the valid time
    public String nextClosestTime(String time) {
        int H = Integer.parseInt(time.substring(0,2));
        int M = Integer.parseInt(time.substring(3,5));
        String newTime;
        for (int h = H; ; h++) {
            for (int m = (h==H? M+1 : 0); m < 60; m++) {
                newTime = String.format("%02d:%02d", h%24, m);
                if (isValid(time, newTime))
                    return newTime;
            }
        }
    }

    boolean isValid(String time, String newTime) {
        for (int i = 0; i < newTime.length(); i++) {
            if (time.indexOf(newTime.charAt(i)) == -1) return false;
        }
        return true;
    }

    // solution 2, assume 4 digits are all different (worst case)
    // number of possible newtimes: 4*4*4*4, some of them are invalid, loop through all possible times
    public String nextClosestTime2(String time) {
        Set<Integer> digits = new HashSet<>();
        int timeInMinutes = Integer.parseInt(time.substring(0,2))*60 + Integer.parseInt(time.substring(3,5));
        int minDiff = Integer.MAX_VALUE;
        int resHour = 0, resMin = 0;

        for (int i : new int[]{0,1,3,4}) {
            digits.add(time.charAt(i)-'0');
        }

        for (int i : digits) { // 1st digit
            if (i > 2) continue;
            for (int j : digits) { // 2nd digit
                if (i == 2 && j > 4) continue;
                for (int m : digits) { // 3rd digit
                    if (m > 5) continue;
                    for (int n : digits) { // 4th digit
                        int hour = i*10+j;
                        int min = m*10+n;
                        int diff = timeDiff(hour, min, timeInMinutes);
                        if (diff < minDiff) {
                            resHour = hour;
                            resMin = min;
                            minDiff = diff;
                        }
                    }
                }
            }
        }
        return String.format("%02d:%02d", resHour%24, resMin);
    }

    int timeDiff(int hour, int min, int timeInMinutes) {
        int newTime = hour*60 + min;
        return newTime > timeInMinutes ? newTime-timeInMinutes : newTime+1440-timeInMinutes;
    }

    public static void main(String[] args) {
        NextClosestTime sol = new NextClosestTime();
        System.out.println(sol.nextClosestTime2("23:59"));
    }
}
