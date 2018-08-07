public class ANDAndOR {
    // 或操作的结果只会增大或者不变;
    // 与操作的结果只会减小或者不变;
    // 最大或和，即求所有数字的或操作；
    // 最小或和，即求哪个数最小；
    // 最大与和，即求哪个数字最大；
    // 最小与和，即求所有数字的与操作
    public long getSum(int n, int[] nums) {
        long maxOrSum = nums[0];
        long minOrSum = nums[0];
        long maxAndSum = nums[0];
        long minAndSum = nums[0];

        for(int i = 1; i < n; i++) {
            maxOrSum |= nums[i];
            minOrSum = Math.min(minOrSum, nums[i]);
            maxAndSum = Math.max(maxAndSum, nums[i]);
            minAndSum &= nums[i];
        }

        return maxOrSum + minOrSum + maxAndSum + minAndSum;
    }
}
