public class RotatedDigits {
    // 0-9: 4
    // 10 -99 : 60
    // 100-999 : 700
    // for any number, e.g. 2846/3846, there are two cases,
    // if the first digit is in/not_in set 2,5,6,9
    // if in set, rotateDigits(1999) + 846 + 1
    // if not in set, rotateDigits(2999) + rotateDigits(846)
    int[] map = {0, 4, 60, 700};
    int[] count = {0, 0, 1, 1, 1, 2, 3, 3, 3, 4};

    public int rotatedDigits(int N) {
        if(N < 10) return count[N];
        int n = Integer.toString(N).length(); // numOfDigits
        int val = (int) Math.pow(10, n-1); // if 3 digits, val = 100, e.g. 256
        int digit = N/val; // 256/100 is 2
        if(digit == 2 || digit == 5 || digit == 6 || digit == 9) {
            return count[digit]*val + (digit - count[digit])*map[n-1] + N - digit*val + 1;
        } else {
            return count[digit]*val + (digit - count[digit])*map[n-1] + rotatedDigits(N-digit*val);
        }
    }

    public static void main(String[] args) {
        int N = 857;
        RotatedDigits sol = new RotatedDigits();
        System.out.println(sol.rotatedDigits(N));
    }
}
