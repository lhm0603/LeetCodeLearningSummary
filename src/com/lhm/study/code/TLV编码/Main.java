package com.lhm.study.code.TLV编码;

import java.util.Scanner;

/**
 * TLV编码
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String tag = input.nextLine();
        String content = input.nextLine();
        String[] split = content.split(" ");
        for (int i = 0; i < split.length; i++) {
            String curTag = split[i];
            String len1 = split[++i];
            String len2 = split[++i];
            int length = formatRadix16To10(len2 + len1);// 注意小端序要反过来相加
            // 若tag不是目标，则跳过
            if (!tag.equalsIgnoreCase(curTag)) {
                i += length;// 跳过无用的value值，查找下一个tag
                continue;
            }
            System.out.println(takeString(split, i + 1, length));
            break;
        }
    }

    private static String takeString(String[] strings, int start, int len) {
        StringBuilder result = new StringBuilder();
        for (int i = start; i < strings.length && i < start + len; i++) {
            result.append(strings[i]).append(" ");
        }
        if (result.length() > 0) {
            result.delete(result.length() - 1, result.length());
        }
        return result.toString();
    }

    // 字符串16进制值转10进制
    private static int formatRadix16To10(String str) {
        return Integer.parseInt(str, 16);
    }
}
