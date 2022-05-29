package com.lhm.study.code;

public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 101; i++) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }
    }

    /**
     * 判断一个数是否是质数
     */
    private static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        double sqrt = Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断n、m是否互质。思想，找不到比1大的公约数，则互为质数
     */
    private static boolean isMutualPrime(int n, int m) {
        int t;
        while (m > 0) {
            t = n % m;
            n = m;
            m = t;
        }
        return n == 1;
    }
}
