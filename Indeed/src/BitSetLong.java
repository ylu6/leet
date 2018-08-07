import java.util.BitSet;

public class BitSetLong {
    private static final int BITS_To_SHIFT = 6;
    private static final int BITS_MASK = (1 << BITS_To_SHIFT) - 1;
    long[] words;
    long size; // number of long numbers to be stored, [1, size)
    public BitSetLong(long size) {
        this.size = size;
        words = new long[(int)((size-1)/64)+1];
    }
    void set(long word) {
        if(word > size) return;
        long wordIdx = word >> 6;
        long bitIdx =  word & BITS_MASK;
        words[(int)wordIdx] |= 1L << bitIdx; // Note, must use 1L!!!! use 1 is WRONG
        // like 1<<32, sign will be negtive, if auto convert from int to long, will fill leading bits with the sign, which is 1
    }

    // return true if the word is in bitset
    boolean get(long word) {
        if(word >= size) return false;
        int wordIdx = (int) word >> 6;
        int bitIdx = (int) word & BITS_MASK;
        return (words[wordIdx] & (1L << bitIdx)) != 0;
    }

    public static void main(String[] args) {
        BitSetLong bs = new BitSetLong((long) 64);
//        for(int i = 0; i < 64; i++) {
//            System.out.println("processing number: " + i);
//            System.out.println(bs.get(i));
//            bs.set(i);
//            System.out.println(bs.get(i));
//        }
        bs.set(0);
        bs.set(63);
        System.out.println(Long.toBinaryString(bs.words[0]));
    }
}
