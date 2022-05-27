package com.lhm.study.code;

public class Test {
    public static void main(String[] args) {
        System.out.println(hz(7, 17));
        System.out.println(hz(7, 5));
        System.out.println(hz(7, 14));
        System.out.println(hz(16, 5680));
        System.out.println(hz(19, 157));
        System.out.println(hz(16, 50));
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
