package com.company;
import java.util.*;

class KSum {
    public static int[] twoSum(int[] numbers, int target) {
        for (int i=0, j=numbers.length-1; i < j; ) {
            if (numbers[i]+numbers[j] < target) ++i;
            else if (numbers[i]+numbers[j] > target) --j;
            else return new int[]{i+1, j+1};
        }
        return null;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len-2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) { //avoid duplicates
                for (int j = i + 1, k = len - 1; j < k; ) {
                    if (nums[j] + nums[k] + nums[i] < 0) ++j;
                    else if (nums[j] + nums[k] + nums[i] > 0) --k;
                    else {
                        res.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[j], nums[k])));
                        while (j < k && nums[j]==nums[j+1]) ++j; //avoid duplicates
                        while (j < k && nums[k]==nums[k-1]) --k; //avoid duplicates
                        ++j;
                        --k;
                    }
                }
            }
        }
        return res;
    }

    //use HashSet to avoid duplicates
    public static List<List<Integer>> threeSum2(int[] nums) {
        Set<List<Integer>> res = new HashSet<List<Integer>>();
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len-2; i++) {
            for (int j=i+1, k=len-1; j < k; ) {
                if (nums[j]+nums[k]+nums[i]<0) ++j;
                else if (nums[j]+nums[k]+nums[i]>0) --k;
                else {
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[i],nums[j],nums[k])));
                    ++j;
                    --k;
                }
            }
        }
        return new ArrayList<List<Integer>>(res);
    }


    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        int len = nums.length;
        if (nums==null || len<4 || 4*nums[0]>target||4*nums[len-1]<target) return res;
        for(int i = 0; i < len-3; i++) {
            if (i>0 && nums[i-1]==nums[i]) continue; //avoid duplicates
            if (nums[i]+3*nums[i+1] > target) return res;
            if (nums[i]+3*nums[len-1] < target) continue;
            for (int j = i+1; j < len-2; j++) {
                if (j>i+1 && nums[j-1]==nums[j]) continue; //avoid duplicates
                for (int lo=j+1, hi=len-1; lo<hi; ) {
                    int sum = nums[i]+nums[j]+nums[lo]+nums[hi];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i],nums[j],nums[lo],nums[hi]));
                        while (lo<hi && nums[lo]==nums[lo+1]) lo++; //avoid duplicates
                        while (lo<hi && nums[hi]==nums[hi-1]) hi--; //avoid duplicates
                        lo++; hi--;
                    } else if (sum > target) hi--;
                    else lo++;
                }
            }
        }
        return res;
    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int res = 0;
        Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
        Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
        int len = A.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int val1 = map1.containsKey(A[i]+B[j]) ? map1.get(A[i]+B[j]) : 0;
                int val2 = map2.containsKey(C[i]+D[j]) ? map2.get(C[i]+D[j]) : 0;
                map1.put(A[i]+B[j], val1+1);
                map2.put(C[i]+D[j], val2+1);
            }
        }
        for(Map.Entry<Integer, Integer> entry : map1.entrySet()) {
            if (map2.containsKey(0-entry.getKey()))
                res += entry.getValue() * map2.get(0 - entry.getKey());
        }
        return res;
    }
}