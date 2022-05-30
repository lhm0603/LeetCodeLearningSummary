package com.lhm.study.code.叠积木;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        String[] split = s.split(" ");
        LinkedList<Integer> list = new LinkedList<>();
        for (String ss : split) {
            list.add(Integer.parseInt(ss));
        }
        // 降序排列
        list.sort((o1, o2) -> o2 - o1);
        int deal = deal(list);
        System.out.println(deal);
    }

    private static int deal(LinkedList<Integer> list) {
        if (list.size() == 0) {// 没有积木，0层
            return 0;
        }
        int count = 1;
        if (list.size() == 1) {// 只有1块积木，只有1层
            return count;
        }
        LinkedList<Integer> temp = new LinkedList<>(list);// 拷贝一份数据备用
        int result = recur(temp, temp.removeFirst(), count);// 处理最长那一块单独作为1层的情况
        if (result > 0) {
            return result;// 若搭建成功，肯定比下面那种情况层数要多
        }
        // 若上面情况搭建不成功，则将最长那一块与最短那一块作为一层，继续分析处理
        int base = list.removeFirst() + list.removeLast();
        return recur(list, base, count);
    }

    /**
     * @param list  所有积木
     * @param base  地基长度
     * @param count 层数
     */
    private static int recur(LinkedList<Integer> list, int base, int count) {
        if (list.size() == 0) {
            return count;// 且整个递归过程没有执行最后的return -1，且积木刚好用完。则返回层数
        }
        Integer value = list.removeFirst();//移除剩下最长那一块去跟地基比较
        if (value == base) {// 表示当前这块与地基长度也相同
            return recur(list, base, count + 1);// 匹配成功，层级+1
        }
        if (list.size() > 0) {// 若还有积木，则取出最小的那一块加上上面所剩最长那一块去与地基比较
            Integer integer = list.removeLast();
            if (value + integer == base) {
                return recur(list, base, count + 1);// 匹配成功，层级+1
            }
        }
        return -1;// 若上诉情况都不符合，则表示积木已经无法满足题目要求，返回-1
    }
}
