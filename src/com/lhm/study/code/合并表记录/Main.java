package com.lhm.study.code.合并表记录;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    private static final TreeMap<Integer, Integer> results = new TreeMap<>();
    public static final String SPACE = " ";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        for (int i = 0; i < n; i++) {
            String kv = input.nextLine();
            String[] split = kv.split(SPACE);
            if (split.length == 2) {
                deal(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            }
        }
        for (Map.Entry<Integer, Integer> entry : results.entrySet()) {
            System.out.println(entry.getKey() + SPACE + entry.getValue());
        }
    }

    private static void deal(int index, int value) {
        Integer integer = results.get(index);
        int add = integer == null ? 0 : integer;
        results.put(index, value + add);
    }
}
