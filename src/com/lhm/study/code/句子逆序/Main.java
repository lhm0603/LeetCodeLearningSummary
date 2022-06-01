package com.lhm.study.code.句子逆序;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String content = input.nextLine();
        String[] split = content.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = split.length - 1; i >= 0; i--) {
            sb.append(split[i]);
            if (i != 0) {
                sb.append(' ');
            }
        }
        System.out.println(sb);
    }
}
