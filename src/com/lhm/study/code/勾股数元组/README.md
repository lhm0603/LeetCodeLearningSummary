# 勾股数元组

如果三个正整数A、B、C ,A²+B²=C²则为勾股数
如果ABC之间两两互质，即A与B，A与C，B与C均互质没有公约数，
则称其为勾股数元组。
请求出给定n~m范围内所有的勾股数元组

## 输入描述

起始范围
`1 < n < 10000`
`n < m < 10000`

## 输出描述

ABC保证`A<B<C`
输出格式`A B C`
多组勾股数元组，按照A B C升序的排序方式输出。
若给定范围内，找不到勾股数元组时，输出`Na`。

## 示例一

- 输入

  ```bash
  1
  20
  ```

- 输出

  ```bash
  3 4 5
  5 12 13
  8 15 17
  ```

## 示例二

- 输入

  ```bash
  5
  10
  ```

- 输出

  ```bash
  Na
  ```

## 解题

1. 首先，写一个函数判断两个数之间互为质数。可以用最大公约数为1的方式解决
2. 然后根据已知条件，`a<b<c` ，可以使用三层循环，分别根据条件判断，符合条件则输出，并记录符合条件次数，若没有符合条件的结果，则循环结束后输出`Na`

## java代码

```java
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int count = 0;
        for (int a = n; a < m; a++) {
            for (int b = a + 1; b < m; b++) {
                for (int c = b + 1; c < m; c++) {
                    if (hz(a, b) && hz(a, c) && hz(b, c) && a * a + b * b == c * c) {
                        System.out.println(a + " " + b + " " + c);
                        count++;
                    }
                }
            }
        }
        if (count == 0) {
            System.out.println("Na");
        }
    }

    /**
     * 判断n、m是否互质。思想，找不到比1大的公约数，则互为质数
     */
    private static boolean hz(int n, int m) {
        int t;
        while (m > 0) {
            t = n % m;
            n = m;
            m = t;
        }
        return n == 1;
    }
}
```