
## 【简单】提取不重复的整数

输入一个 int 型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。

保证输入的整数最后一位不是 0 。

数据范围： 
$$
1≤n≤108
$$
**【输入描述】**

输入一个int型整数

**【输出描述】**

按照从右向左的阅读顺序，返回一个不含重复数字的新的整数

**【示例一】**

- 输入

  ```bash
  9876673
  ```
  
- 输出

  ```bash
  37689
  ```


**【解思路】**

1. 题目不是很清晰，看用例才明白，需要从个位数开始，一次往左读取。
2. 注意输出，是要反过来输出。这也能理解题目为啥说保证输入的最后一位不是0。若是0则反过来数字首位是0就没意义了。
3. 所以题目即要去重，又要保证顺序。可以使用 LinkedHashSet 来处理即可。

**【代码】**

```java
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        LinkedHashSet<Integer> result = new LinkedHashSet<>();
        while (num > 0) {
            result.add(num % 10);// 取出各位
            num /= 10;// 去掉各位，让十位变成各位，继续循环
        }
        for (Integer n : result) {
            System.out.print(n);
        }
    }
}
```