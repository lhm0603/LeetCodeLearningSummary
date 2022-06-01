package com.lhm.study.code.提取不重复的整数;

import java.util.LinkedHashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        LinkedHashSet<Integer> result = new LinkedHashSet<>();
        while (num > 0) {
            result.add(num % 10);// 取出个位
            num /= 10;// 去掉个位，让十位变成个位，继续循环
        }
        for (Integer n : result) {
            System.out.print(n);
        }
    }
}