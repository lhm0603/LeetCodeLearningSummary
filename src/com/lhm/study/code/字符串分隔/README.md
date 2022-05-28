# 字符串分割

* 输入一个字符串，请按长度为8拆分每个输入字符串并进行输出；
* 长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。

## 输入描述

连续输入字符串(每个字符串长度小于等于100)

## 输出描述

依次输出所有分割后的长度为8的新字符串

## 示例一

- 输入

  ```bash
  abc
  ```
  
- 输出

  ```bash
  abc00000
  ```

## 解题

1. 这道题的解法应该有N多种，我的想法是每次循环8次，从输入的字符串s中依次往后取8个字符，若s字符串已经被使用完，则补0。
2. 每一轮8次循环结束后，判断字符串s是不是已经用完。则结束循环。
3. 注意空字符串不处理。

## java代码

```java

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        deal(input.nextLine());
    }

    private static void deal(String s) {
        int index = 0;
        int length = s.length();
        while (index < length) {// 结束条件，包含空字符串情况
            StringBuilder sb = new StringBuilder(8);
            for (int i = 0; i < 8; i++) {
                if (index < length) {
                    sb.append(s.charAt(index));
                } else {
                    sb.append('0');// 若字符已经用完，下标已越界，则追加0
                }
                index++;
            }
            System.out.println(sb);
        }
    }
}

```