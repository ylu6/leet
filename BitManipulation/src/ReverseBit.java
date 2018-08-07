public class ReverseBit {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        n = ((0xffff0000&n) >>> 16) | ((0x0000ffff&n) << 16);
        n = ((0xff00ff00&n) >>> 8)  | ((0x00ff00ff&n) << 8);
        n = ((0xf0f0f0f0&n) >>> 4)  | ((0x0f0f0f0f&n) << 4);
        n = ((0xcccccccc&n) >>> 2)  | ((0x33333333&n) << 2);
        n = ((0xaaaaaaaa&n) >>> 1)  | ((0x55555555&n) << 1);

        return n;
    }
}
