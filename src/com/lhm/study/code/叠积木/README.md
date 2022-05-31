
##  叠积木

积⽊宽⾼相等，长度不等，每层只能放⼀个或拼接两个积⽊，要求每层长度相等，求最⼤层数。

**【输入描述】**

给定积⽊的长度，以空格分隔，例如:3 6 6 3。

**【输出描述】**

如果可以搭建，返回最⼤层数，如果不可以返回-1。

**【示例一】**

- 输入

  ```bash
  3 6 6 3
  ```

- 输出

  ```bash
  3
  ```

**【示例二】**

- 输入

  ```bash
  1 4 2 3 6
  ```

- 输出

  ```bash
  -1
  ```

**【解题思路】**

1. 读懂题目的要求，积木每一层只能是1块，或者2块拼接
2. 必须每一层的长度相等，否则算为不可搭建，则输出`-1`
3. 分析规律：将积木长度进行排序后，最长的积木只能两种情况：1、单独一块去搭建；2、跟最小长度那一块去搭建

**【代码】**

```java
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String s = input.nextLine();
    String[] split = s.split(" ");
    LinkedList<Integer> list = new LinkedList<>();
    for (String ss : split) {
      list.add(Integer.parseInt(ss));
    }
    // 降序排列
    list.sort((o1, o2) -> o2 - o1);
    int deal = deal(list);
    System.out.println(deal);
  }

  private static int deal(LinkedList<Integer> list) {
    if (list.size() == 0) {// 没有积木，0层
      return 0;
    }
    int count = 1;
    if (list.size() == 1) {// 只有1块积木，只有1层
      return count;
    }
    LinkedList<Integer> temp = new LinkedList<>(list);// 拷贝一份数据备用
    int result = recur(temp, temp.removeFirst(), count);// 处理最长那一块单独作为1层的情况
    if (result > 0) {
      return result;// 若搭建成功，肯定比下面那种情况层数要多
    }
    // 若上面情况搭建不成功，则将最长那一块与最短那一块作为一层，继续分析处理
    int base = list.removeFirst() + list.removeLast();
    return recur(list, base, count);
  }

  /**
   * @param list  所有积木
   * @param base  地基长度
   * @param count 层数
   */
  private static int recur(LinkedList<Integer> list, int base, int count) {
    if (list.size() == 0) {
      return count;// 且整个递归过程没有执行最后的return -1，且积木刚好用完。则返回层数
    }
    Integer value = list.removeFirst();//移除剩下最长那一块去跟地基比较
    if (value == base) {// 表示当前这块与地基长度也相同
      return recur(list, base, count + 1);// 匹配成功，层级+1
    }
    if (list.size() > 0) {// 若还有积木，则取出最小的那一块加上上面所剩最长那一块去与地基比较
      Integer integer = list.removeLast();
      if (value + integer == base) {
        return recur(list, base, count + 1);// 匹配成功，层级+1
      }
    }
    return -1;// 若上诉情况都不符合，则表示积木已经无法满足题目要求，返回-1
  }
}

```