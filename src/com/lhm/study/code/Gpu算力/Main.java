package com.lhm.study.code.Gpu算力;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * GPU算力
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int taskCunt = input.nextInt();
        int taskArrLen = input.nextInt();
        int[] taskArr = new int[taskArrLen];
        for (int i = 0; i < taskArrLen; i++) {
            taskArr[i] = input.nextInt();
        }
        int count = deal(taskArr, taskCunt);
        System.out.println(count);
    }

    private static int deal(int[] taskArr, int taskCunt) {
        ArrayList<Integer> tasks = new ArrayList<>();
        for (int i : taskArr) {
            tasks.add(i);
        }
        sortDesc(tasks);
        int sCount = 0;
        while (tasks.size() > 0) {
            for (int i = 0; i < taskCunt; i++) {// 每次取出3个
                if (i < tasks.size()) {
                    int num = tasks.get(i) - 1;
                    if (num == 0) {// 若无任务，则移除
                        tasks.remove(i);
                    } else {
                        tasks.set(i, num);
                    }
                }
            }
            sCount++;// 每循环一次，计数一次
            sortDesc(tasks);// 需要重新降序排列
        }
        return sCount;
    }

    private static void sortDesc(ArrayList<Integer> tasks) {
        // 降序排列
        tasks.sort((o1, o2) -> o2 - o1);
    }
}
