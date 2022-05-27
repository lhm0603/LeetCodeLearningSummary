package com.lhm.study.code.数组对元素求和最小值;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String arr1Str = input.nextLine();
        String arr2Str = input.nextLine();
        int count = input.nextInt();
        int[] arr1 = parseArr(arr1Str);
        int[] arr2 = parseArr(arr2Str);
        ArrayList<Integer> allSum = new ArrayList<>(arr1.length * arr2.length);
        for (int n : arr1) {
            for (int m : arr2) {
                allSum.add(n + m);
            }
        }
        Collections.sort(allSum);
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += allSum.get(i);
        }
        System.out.println(sum);
    }

    /**
     * 将输入格式转化为int数组
     */
    private static int[] parseArr(String arrStr) {
        String[] strings = arrStr.split(" ");
        int[] arr = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            arr[i] = Integer.parseInt(strings[i]);
        }
        return arr;
    }
}
