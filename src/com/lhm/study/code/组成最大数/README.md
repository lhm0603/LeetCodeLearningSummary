# 组成最大数

小组中每位都有一张卡片，卡片上是6位内的正整数，将卡片连起来可以组成多种数字，计算组成的最大数字。



## 输入描述

“,”号分割的多个正整数字符串，不需要考虑非数字异常情况，小组最多25个人



## 输出描述

最大的数字字符串



## 示例一

* 输入

  ```
  22,221  
  ```

* 输出

  ```
  22221
  ```

## 示例二

* 输入

  ```
  4589,101,41425,9999
  ```

* 输出

  ```
  9999458941425101
  ```



## 解题

1. 按字符串自然大小排序之后，数字字符从大到小依次为9>8>7...3>2>1。

2. 且 字符串“89”要大于“723”，因为字符串自然排序匹配规则是根据第一个字符比较大小，若相等，则比较下一个，超出部分摄取比对。

3. 所以只需要对输入的所有数字字符串进行排序，之后按顺序输出即可！



## java代码

```java
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String content = input.next();
        String[] split = content.split(",");
        Arrays.sort(split);
        for (int i = split.length - 1; i >= 0; i--) {
            System.out.print(split[i]);
        }
    }
}
```

