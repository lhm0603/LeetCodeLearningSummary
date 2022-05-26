package com.lhm.study.code;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String content = input.next();
        String[] split = content.split(",");
        Arrays.sort(split);
        for (int i = split.length - 1; i >= 0; i--) {
            System.out.print(split[i]);
        }
    }
}
