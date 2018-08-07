public class RangeSumQueryMutable {
    int[] bit;
    int[] nums;
    public RangeSumQueryMutable(int[] nums) {
        bit = new int[nums.length+1];
        this.nums = nums;
        for(int i = 0; i < nums.length; i++) {
            updateDelta(i, nums[i]);
        }
        for(int i : bit) {
            System.out.println(i);
        }
    }

    public void update(int i, int val) {
        int delta = val - nums[i];
        nums[i] = val;
        updateDelta(i, delta);
    }

    public int sumRange(int i, int j) {
        return getSum(j) - getSum(i-1);
    }

    int getSum(int i) {
        int sum = 0;
        i = i+1;
        while(i > 0) {
            sum += bit[i];
            i -= i&(-i);
        }
        return sum;
    }

    void updateDelta(int i, int delta) {
        i = i+1;
        while(i < bit.length) {
            bit[i] += delta;
            i += i&(-i);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5};
        RangeSumQueryMutable sol = new RangeSumQueryMutable(nums);
        System.out.println(sol.getSum(2));
    }
}
