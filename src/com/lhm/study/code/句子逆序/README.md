
## 【简单】句子逆序

将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”

所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符

数据范围：输入的字符串长度满足 `1 ≤ n ≤ 1000` 

**【输入描述】**

输入一个英文语句，每个单词用空格隔开。保证输入只包含空格和字母。

**【输出描述】**

得到逆序的句子

**【示例一】**

- 输入

  ```bash
  I am a boy
  ```
  
- 输出

  ```bash
  boy a am I
  ```

**【示例二】**

- 输入

  ```bash
  nowcoder
  ```
  
- 输出

  ```bash
  nowcoder
  ```

**【解题思路】**

1. 空格拆分字符串，倒序拼接字符串即可

**【代码】**

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String content = input.nextLine();
        String[] split = content.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = split.length - 1; i >= 0; i--) {
            sb.append(split[i]);
            if (i != 0) {
                sb.append(' ');
            }
        }
        System.out.println(sb);
    }
}

```