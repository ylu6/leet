import java.util.TreeSet;

public class KEmptySlots {
    /**
     * @param flowers: the place where the flower will open in that day
     * @param k:  an integer
     * @return: in which day meet the requirements
     */
    public int kEmptySlots(int[] flowers, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < flowers.length; i++) {
            int slot = flowers[i];
            set.add(slot);
            Integer lo = set.lower(slot);
            Integer hi = set.higher(slot);
            if((lo != null && slot-lo-1==k) || (hi != null && hi-slot-1==k)) {
                return i+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] flowers = {6,5,8,9,7,1,10,2,3,4};
        KEmptySlots sol = new KEmptySlots();
        System.out.println(sol.kEmptySlots(flowers,2));
    }

    /*
    这道题有一个很好的follow up，就是改为最后的有k盆连续开花的是哪一天，就是k个连续不空的槽
    use HashMap to store interval
     */

    /*
    another follow, m group of {si,sj} where sj-si-1==k and flowers between si and sj all not blooming
    Solution:
    On top of the original solution, use a HashMap to store valid interval, when adding a flower,
    we may invalid a previously valid section, check the lo key in hashmap, reduce segment count if key in map
    */
}
