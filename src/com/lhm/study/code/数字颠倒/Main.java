package com.lhm.study.code.数字颠倒;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String num = input.next();
        for (int i = 0; i < num.length(); i++) {
            System.out.print(num.charAt(num.length() - 1 - i));
        }
    }
}