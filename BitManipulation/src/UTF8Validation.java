/**
 * Created by yeqing on 12/12/2017.
 * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:
 * For 1-byte character, the first bit is a 0, followed by its unicode code.
 * For n-bytes character, the first n-bits are all one's, the n+1 bit is 0,
 * followed by n-1 bytes with most significant 2 bits being 10.
 * The input is an array of integers. Only the least significant 8 bits of each integer is
 * used to store the data. This means each integer represents only 1 byte of data.
 */
public class UTF8Validation {
    // the data array can have more than more UTF8
    public boolean validUtf8(int[] data) {
        if (data == null || data.length == 0) return false;
        int count = 0;
        for (int c : data) {
            if(count == 0) {
                if (c >> 5 == 0b110) count = 1;
                else if (c >> 4 == 0b1110) count = 2;
                else if (c >> 3 == 0b11110) count = 3;
                else if (c >> 7 != 0)
                    return false; // in case there is only one byte, first binary digit should be 0
                System.out.println(count);
            } else { // not the count byte, check whether it starts with 10
                if (c >> 6 != 0b10) return false;
                count--;
            }
        }
        return count==0;
    }
    public static void main(String[] args) {
        int[] data = {197,130,1};
        UTF8Validation sol = new UTF8Validation();
        System.out.println(sol.validUtf8(data));
    }
}
