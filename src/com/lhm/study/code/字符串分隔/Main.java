package com.lhm.study.code.字符串分隔;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        deal(input.nextLine());
    }

    private static void deal(String s) {
        int index = 0;
        int length = s.length();
        while (index < length) {
            StringBuilder sb = new StringBuilder(8);
            for (int i = 0; i < 8; i++) {
                if (index < length) {
                    sb.append(s.charAt(index));
                } else {
                    sb.append('0');
                }
                index++;
            }
            System.out.println(sb);
        }
    }
}
