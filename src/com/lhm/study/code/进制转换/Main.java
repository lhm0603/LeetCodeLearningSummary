package com.lhm.study.code.进制转换;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String rex = input.nextLine();
        System.out.println(Integer.parseInt(rex.substring(2), 16));
    }
}
