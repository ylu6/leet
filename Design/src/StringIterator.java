import java.util.Iterator;

public class StringIterator implements Iterator<Character> {

    String str;
    int idx, count;
    char c;

    public StringIterator(String compressedString) {
        str = compressedString;
        idx = 0;
        count = 0;
        char c = ' ';
    }

    @Override
    public boolean hasNext() {
        return count > 0 || idx < str.length();
    }

    @Override
    public Character next() {
        if(count == 0) {
            c = str.charAt(idx);
            idx++;
            while(idx < str.length() && Character.isDigit(str.charAt(idx))) {
                count = count*10 + str.charAt(idx) - '0';
                idx++;
            }
        }
        count--;
        return c;
    }

    public static void main(String[] args) {
        StringIterator iterator = new StringIterator("L1e2t1C1o1d1e3");
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
