package com.data_structure.array.hsl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestHarmoniousSubsequence {
    public static void main(String[] args) {
        int[] case1 = new int[]{1,3,2,2,5,2,3,7};
        int[] case2 = new int[]{1,2,3,4};
        int[] case3 = new int[]{1,1,1,1};
        int[] case4 = new int[]{1,3,2,2,5,2,5,7};


//        System.out.println("Case 1:" + findLHS(case1));
//        System.out.println("Case 2:" + findLHS(case2));
//        System.out.println("Case 3:" + findLHS(case3));
//        System.out.println("Case 3:" + findLHS(case4));

        System.out.println(findLHS2(case1));
    }

    public static int findLHS2(int[] nums){
        Arrays.sort(nums);
        int result = 0;
        for (int minI = 0; minI < nums.length; minI++) {
            int count = 0;
            for (int maxI = nums.length-1; maxI > minI; maxI--) {
                if (nums[maxI] - nums[minI] == 1) {
                    count++;
                }
            }
            result = Math.max(result, count);
        }

        return result;
    }

    public static int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int key : map.keySet()) {
            if (map.containsKey(key + 1)) {
                result = Math.max(result, map.get(key) + map.get(key + 1));
            }
            System.out.println("Result: " + map);
        }

        return result;
    }
}

