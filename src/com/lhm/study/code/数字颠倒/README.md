
## 【简单】数字颠倒

输入一个整数，将这个整数以字符串的形式逆序输出

程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001

数据范围：
$$
0≤n≤2^{30}−1
$$
**【输入描述】**

输入一个int整数

**【输出描述】**

将这个整数以字符串的形式逆序输出

**【示例一】**

- 输入

  ```bash
  1516000
  ```
  
- 输出

  ```bash
  0006151
  ```


**【解思路】**

1. 直接将整数看成字符串，循环从后往前输出字符串即可

**【代码】**

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String num = input.next();
        for (int i = 0; i < num.length(); i++) {
            System.out.print(num.charAt(num.length() - 1 - i));
        }
    }
}
```