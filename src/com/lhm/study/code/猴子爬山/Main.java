package com.lhm.study.code.猴子爬山;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        int count = cal(num);
        System.out.println(count);
    }

    private static int cal(int num) {
        if (num < 3) {
            return 1;
        }
        return cal(num - 1) + cal(num - 3);
    }

    //0  1  2  3  4  5  6  7  8  9
    //1  1  1  2  3  4  6  9  13 19
}
