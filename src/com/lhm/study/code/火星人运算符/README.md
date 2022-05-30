# 火星人运算符

已知火星人使用的运算符为#、$，其与地球人的等价公式如下：

`x#y = 2x+3y+4`

`x$y = 3*x+y+2`

其中x、y是无符号整数
地球人公式按C语言规则计算
火星人公式中，$的优先级高于#，相同的运算符，按从左到右的顺序计算 现有一段火星人的字符串报文，请你来翻译并计算结果。

## 输入描述

火星人字符串表达式（结尾不带回车换行）

* 输入说明：

  ```
  字符串为仅由无符号整数和操作符（#、$）
  组成的计算表达式。例如：123#45#6778
  用例保证字符串中，操作数与操作符之间没有任何分隔符。
  用例保证操作数取值范围为32位无符号整数。
  保证输入以及计算结果不会出现整型溢出。
  保证输入的字符串为合法的求值报文，例如：123#45#6778
  保证不会出现非法的求值报文，例如类似这样字符串：
  #4$5 //缺少操作数
  4$5# //缺少操作数
  4#$5 //缺少操作数
  4 $5 //有空格
  3+4-5*6/7 //有其它操作符
  12345678987654321$54321 //32位整数计算溢出
  ```

## 输出描述

根据输入的火星人字符串输出计算结果（结尾不带回车换行）

## 示例一

* 输入

  ```
  7#6$5#12
  ```

* 输出

  ```
  226
  ```

* 说明：

  ```
  7#6$5#12
  = 7#(3*6+5+2)#12
  =7#25#12
  =(2*7+3*25+4)#12
  =93#12
  =2*93+3*12+4
  226
  ```

## 解题

1. 先将两个公式抽出一个方法，方便使用
2. 按符号优先级，先根据优先级较低的符号 `#` 进行拆分
3. 若拆分后不包含其他运算符，则直接转换为整型
4. 若拆分后的字符串中包含优先级较高的符号`$`。分别计算带有符号`$`的表达式的结果
5. 之后组成一个只有 `#`符号参与运算的列表，从左到右依次循环运算



## java代码

```java
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
```