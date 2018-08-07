import java.util.Arrays;

public class ThreeSumSmaller {
    public int threeSumSmaller(int[] nums, int target) {
        int res = 0;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-2; i++) {
            int t = target - nums[i];
            for(int j = i+1, k = nums.length-1; j < k; ) {
                int sum = nums[i] + nums[j] + nums[k];
                if(sum < target) {
                    res += k-j;
                    j++;
                } else k--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 1, 3};
        ThreeSumSmaller sol = new ThreeSumSmaller();
        System.out.println(sol.threeSumSmaller(nums, 2));
    }
}
