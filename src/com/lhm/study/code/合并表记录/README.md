
## 【简单】合并表记录

数据表记录包含表索引index和数值value（int范围的正整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照index值升序进行输出。

提示:

`0 <= index <= 11111111`

`1 <= value <= 100000`

**【输入描述】**

先输入键值对的个数n`（1 <= n <= 500）`
接下来n行每行输入成对的index和value值，以空格隔开

**【输出描述】**

输出合并后的键值对（多行）

**【示例一】**

- 输入

  ```bash
  4
  0 1
  0 2
  1 2
  3 4
  ```
  
- 输出

  ```bash
  0 3
  1 2
  3 4
  ```

**【示例二】**

- 输入

  ```bash
  3
  0 1
  0 2
  8 9
  ```
  
- 输出

  ```bash
  0 3
  8 9
  ```

**【解题思路】**

1. index相同，则将value相加。可以使用map，若存在key，则叠加value。不存在key，则put入新的key-value
2. 最终需要根据key升序排列，刚好可以使用TreeMap

**【代码】**

```java
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    private static final TreeMap<Integer, Integer> results = new TreeMap<>();
    public static final String SPACE = " ";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        for (int i = 0; i < n; i++) {
            String kv = input.nextLine();
            String[] split = kv.split(SPACE);
            if (split.length == 2) {
                deal(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            }
        }
        for (Map.Entry<Integer, Integer> entry : results.entrySet()) {
            System.out.println(entry.getKey() + SPACE + entry.getValue());
        }
    }

    private static void deal(int index, int value) {
        Integer integer = results.get(index);
        int add = integer == null ? 0 : integer;
        results.put(index, value + add);
    }
}
```