public class SwapAdjacentInLRString {
    // can replace "XL" with "LX", and "RX" with "XR"
    // so L can only go left and R can only go right, L and R cannot cross each other
    // if we remove all X from start and end, the remain string should equal
    // since L can only go left, scan from left to right, we cannot have more L in start than end at any position
    // same for R if we scan from right to left
    public boolean canTransform(String start, String end) {
        if(start.length() != end.length()) return false;
        if(!start.replace("X","").equals(end.replace("X","")))
            return false;

        int count = 0;
        // scan from left to right
        for(int i = 0; i < start.length(); i++) {
            if(start.charAt(i)=='L') count--;
            if(end.charAt(i)=='L') count++;
            if(count < 0) return false;
        }
        count = 0;
        for(int i = start.length()-1; i >= 0; i--) {
            if(start.charAt(i) == 'R') count--;
            if(end.charAt(i) == 'R') count++;
            if(count < 0) return false;
        }
        return true;
    }
}
