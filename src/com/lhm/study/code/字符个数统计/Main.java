package com.lhm.study.code.字符个数统计;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String content = input.next();
        HashSet<Character> result = new HashSet<>();
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c <= 127) {
                result.add(c);
            }
        }
        System.out.println(result.size());
    }
}
