# 字符串最后一个单词的长度

写出一个程序，接受一个由字母、数字和空格组成的字符串，和一个字符，然后输出输入字符串中该字符的出现次数。（不区分大小写字母）

数据范围： `1 ≤ n ≤ 1000` 

## 输入描述

第一行输入一个由字母和数字以及空格组成的字符串，第二行输入一个字符。

## 输出描述

输出输入字符串中含有该字符的个数。（不区分大小写字母）

## 示例一

- 输入

  ```bash
  ABCabc
  A
  ```
  
- 输出

  ```bash
  2
  ```

## 解题

1. 循环查找字符串进行比较即可，注意不区分大小写！

## java代码

```java
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String content = input.nextLine().toLowerCase(Locale.ENGLISH);
        char tag = input.nextLine().toLowerCase(Locale.ENGLISH).charAt(0);
        int count = 0;
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == tag) {
                count++;
            }
        }
        System.out.println(count);
    }
}
```