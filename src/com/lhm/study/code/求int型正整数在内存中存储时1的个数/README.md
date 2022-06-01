
## 【简单】求int型正整数在内存中存储时1的个数

输入一个 int 型的正整数，计算出该 int 型数据在内存中存储时 1 的个数。

数据范围：保证在 32 位整型数字范围内

**【输入描述】**

 输入一个整数（int类型）

**【输出描述】**

 这个数转换成2进制后，输出1的个数

**【示例一】**

- 输入

  ```bash
  5
  ```
  
- 输出

  ```bash
  2
  ```


**【示例二】**

- 输入

  ```bash
  0
  ```
  
- 输出

  ```bash
  0
  ```

**【解题思路】**

1. 10进制转2进制，直接使用Java提供的Api：`Integer.toBinaryString(n)`
2. 统计二进制 1 的个数

**【代码】**

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        String string = Integer.toBinaryString(n);
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '1') {
                count++;
            }
        }
        System.out.println(count);
    }
}
```