package com.lhm.study.code.明明的随机数;

import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int len = input.nextInt();
        TreeSet<Integer> result = new TreeSet<>();
        for (int i = 0; i < len; i++) {
            result.add(input.nextInt());
        }
        for (Integer num : result) {
            System.out.println(num);
        }
    }
}
