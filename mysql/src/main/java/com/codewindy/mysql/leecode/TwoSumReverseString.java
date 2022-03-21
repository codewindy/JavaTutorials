package com.codewindy.mysql.leecode;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSumReverseString {

    public static void main(String[] args) {

            int[] arr = {100,4, 200,5,3 ,2};
            Arrays.sort(arr);
            System.out.println("arr = " + arr);

        }


       /*
       int[] nums ={2,7,11,15};
        System.out.println("twosum(nums) = " + twoSum(nums, 17));*/

    //字符串反转
    public void reverseString(char[] s) {
        if (s == null) {
            throw new IllegalArgumentException("param is null");
        }
        int length = s.length;
        for (int left = 0 , right = length -1 ; left < right; left++, right --) {
            //元素交换
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

        }
    }

    public static int[] twoSum(int[] nums, int target) {
        int total = nums.length;
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>(total-1);
        map.put(nums[0], 0);
        for(int i = 1 ; i < total ; i ++ ){
            if(map.containsKey(target-nums[i])){
                return new int[]{i, map.get(target -nums[i])};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("two sum not exsits.");
    }
}
