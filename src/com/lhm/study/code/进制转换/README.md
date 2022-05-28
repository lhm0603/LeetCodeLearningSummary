# 进制转换

写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。

数据范围：保证结果在
$$
1 ≤ n ≤ 2^{31} - 1
$$


## 输入描述

输入一个十六进制的数值字符串。

## 输出描述

输出该数值的十进制字符串。

## 示例一

- 输入

  ```bash
  0xAA
  ```
  
- 输出

  ```bash
  170
  ```

## 解题

1. 题目已经将值结果保证在Integer取值范围，所以可以直接使用Integer
2. 注意用例，16进制开头是包含`0x`的，而Java的`Integer.parseInt` 是不包含`0x`的

## java代码

```java
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String rex = input.nextLine();
        // substring(2) ，这里需要去掉前面两位 0x
        System.out.println(Integer.parseInt(rex.substring(2), 16));
    }
}
```