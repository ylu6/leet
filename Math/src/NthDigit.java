/**
 * Created by yeqing on 8/31/2017.
 */
public class NthDigit {

    public int findNthDigit(int n) {
        // divide number into different ranges: 1-9, 10-99, 100-999, 1000-9999, ...
        // for each range, there is a start number, length of every number and count of numbers in the range
        int start = 1, lenOfNum = 1;
        long countOfNumber = 9; // if n is very large, countOfNumber will be larger than Integer.MAX_VALUE
        while (n > lenOfNum*countOfNumber) {
            n -= lenOfNum*countOfNumber;
            start *= 10;
            lenOfNum++;
            countOfNumber *= 10;
        }

        int num = start + (n-1)/lenOfNum;
        System.out.println(num + " " + lenOfNum + " " + start);
        char c = String.valueOf(num).charAt((n-1)%lenOfNum);
        return c-'0';
    }

    public static void main(String[] args) {
        NthDigit sol = new NthDigit();
        System.out.println(sol.findNthDigit(2147483647));
    }
}
