package com.lhm.study.code.字符串最后一个单词的长度;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String words = input.nextLine();
        int index = words.lastIndexOf(" ");
        if (index < 0) {
            System.out.println(words.length());
            return;
        }
        String word = words.substring(index + 1);
        System.out.println(word.length());
    }
}
