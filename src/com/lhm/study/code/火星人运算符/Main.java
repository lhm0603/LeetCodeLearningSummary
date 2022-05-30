package com.lhm.study.code.火星人运算符;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String content = input.nextLine();
        String[] split = content.split("#");// 先根据优先级较低符号拆分
        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (!s.contains("$")) {// 拆分部分包不含$，则表示一个单独的数字
                nums[i] = Integer.parseInt(s);
                continue;
            }
            nums[i] = deal$(s);// 拆分部分包含 $ 运算，则先将 $的运算表达式结果计算出来
        }

        // 以下部分是计算只有 # 参与的表达式，并最终输出结果
        int x = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int y = nums[i];
            x = calculate2(x, y);
        }
        System.out.println(x);
    }

    private static int deal$(String str) { // 计算只有 $ 参与的表达式，并返回计算结果
        String[] split = str.split("\\$");
        int x = Integer.parseInt(split[0]);
        for (int i = 1; i < split.length; i++) {
            int y = Integer.parseInt(split[i]);
            x = calculate1(x, y);
        }
        return x;
    }

    /**
     * 火星文 $ 运算
     */
    private static int calculate1(int x, int y) {
        return 3 * x + y + 2;
    }

    /**
     * 火星文 # 运算
     */
    private static int calculate2(int x, int y) {
        return 2 * x + 3 * y + 4;
    }
}