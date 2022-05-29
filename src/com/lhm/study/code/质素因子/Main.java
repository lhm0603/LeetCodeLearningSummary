package com.lhm.study.code.质素因子;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 质素因子
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long num = input.nextLong();
        deal(num);
    }

    private static void deal(long num) {
        long realNum = num;
        double sqrt = Math.sqrt(num);
        long prime = 2;// 当前可疑质素因子
        ArrayList<Long> list = new ArrayList<>();
        while (true) {
            if (num % prime == 0) {
                list.add(prime);//找到质数因子
                num = num / prime;//更新num，对新的num循环查找下一个质数因子
                continue;
            }
            prime = nextPrime(prime);
            if (num < prime || prime > sqrt) {
                // 若可以质数因子超过num本身，或者质数因子超过原始num的平方根，则无需继续查找下去。但有一种特殊情况
                break;
            }
        }
        // 特殊情况，存在质数因子比原始正整数num的平方根还大的情况，但这种情况下，只会存在1个比num的平方根大的质数因子
        if (num > prime && realNum % num == 0) {
            list.add(num);
        }
        if (list.size() == 0) {
            list.add(num);// 若没有找到质数因子，则输出其本身
        }

        // 拼接输出，虽然最后多一个空格也可以通过，强逼症去掉最后空格
        StringBuilder sb = new StringBuilder();
        for (Long l : list) {
            sb.append(l).append(' ');
        }
        System.out.println(sb.delete(sb.length() - 1, sb.length()));
    }

    /**
     * 查找n的下一个质素
     */
    private static long nextPrime(long n) {
        long m = n + 1;
        if (isPrime(m)) {
            return m;
        }
        return nextPrime(m);
    }

    /**
     * 判断一个数是否是质数
     */
    private static boolean isPrime(long n) {
        if (n < 2) {
            return false;
        }
        double sqrt = Math.sqrt(n);
        for (long i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
