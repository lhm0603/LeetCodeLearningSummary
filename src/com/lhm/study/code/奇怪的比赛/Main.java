package com.lhm.study.code.奇怪的比赛;

import java.util.Collection;
import java.util.TreeMap;

public class Main {
    private static final TreeMap<Integer, String> result = new TreeMap<>();

    public static void main(String[] args) {
        // 初始值定义
        int score = 10;
        int index = 1;
        int[] answer = new int[10];
        // 开始递归计算
        deal(answer, index, score);
        Collection<String> values = result.values();
        for (String value : values) {
            System.out.println(value);// 输出结果
        }
    }

    private static void deal(int[] answer, int index, int score) {
        if (index > 10) {// 这是结束条件，10次递归后，会将计算结果传入第11次递归。当次情况计算结束
            if (score == 100) {// 判断10次递归的总分数是否为100，是，则记录统计结果
                StringBuilder sb = new StringBuilder(answer.length);
                for (int i : answer) {
                    sb.append(i);
                }
                String value = sb.toString();
                int key = Integer.parseInt(value, 2);// 二进制转10进制
                result.put(key, value);// 存入treeMap，将自动根据key升序排列
            }
            return;
        }
        answer[index - 1] = 0; // 注意下标需要-1，因为题号从1开始（因为答错需要减去题号，所以题号需要按要求从1开始）
        deal(answer, index + 1, score - index);// 计算答错情况
        answer[index - 1] = 1;
        deal(answer, index + 1, score * 2);// 计算答对情况
    }
}
