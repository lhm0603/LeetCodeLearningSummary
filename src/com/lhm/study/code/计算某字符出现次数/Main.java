package com.lhm.study.code.计算某字符出现次数;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String content = input.nextLine().toLowerCase(Locale.ENGLISH);
        char tag = input.nextLine().toLowerCase(Locale.ENGLISH).charAt(0);
        int count = 0;
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == tag) {
                count++;
            }
        }
        System.out.println(count);
    }
}
