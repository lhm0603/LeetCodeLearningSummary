package com.lhm.study.code.快递运输;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String content = input.nextLine();
        int max = input.nextInt();
        String[] split = content.split(",");
        if (split.length == 0) {
            System.out.println(0);
            return;
        }
        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        Arrays.sort(nums);// 升序排列
        int count = deal(nums, max);
        System.out.println(count);
    }

    private static int deal(int[] nums, int max) {
        int sum = 0;
        int count = 0;
        for (int num : nums) {
            if (num + sum < max) { // 依次叠加判断
                count++;// 统计个数
                sum += num;
            }
        }
        return count;
    }
}