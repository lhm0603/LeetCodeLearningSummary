package com.lhm.study.code.勾股数元组;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int count = 0;
        for (int a = n; a < m; a++) {
            for (int b = a + 1; b < m; b++) {
                for (int c = b + 1; c < m; c++) {
                    if (hz(a, b) && hz(a, c) && hz(b, c) && a * a + b * b == c * c) {
                        System.out.println(a + " " + b + " " + c);
                        count++;
                    }
                }
            }
        }
        if (count == 0) {
            System.out.println("Na");
        }
    }

    /**
     * 判断n、m是否互质。思想，找不到比1大的公约数，则互为质数
     */
    private static boolean hz(int n, int m) {
        int t;
        while (m > 0) {
            t = n % m;
            n = m;
            m = t;
        }
        return n == 1;
    }
}
