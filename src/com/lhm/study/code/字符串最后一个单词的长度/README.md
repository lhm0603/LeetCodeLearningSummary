##  字符串最后一个单词的长度

计算字符串最后一个单词的长度，单词以空格隔开，字符串长度小于5000。（注：字符串末尾不以空格为结尾）

**【输入描述】**

输入一行，代表要计算的字符串，非空，长度小于5000。

**【输出描述】**

输出一个整数，表示输入字符串最后一个单词的长度。

**【示例一】**

- 输入

  ```bash
  hello nowcoder
  ```

- 输出

  ```bash
  8
  ```

* 说明

  最后一个单词为`nowcoder`，长度为8

**【解题思路】**

1. 这道题目很简单，特别是已经强调最后不以空格结尾，所以其实只要通过字符串的`lastIndexOf`找出最后一个空格的下标即可截取最后一个单词的内容，并输出单词长度。
2. 若找不到最后一个空格的下标，则表示整个字符串是一个单词。直接输出字符串长度即可

**【代码】**

```java
public class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String words = input.nextLine();
    int index = words.lastIndexOf(" ");
    if (index < 0) {// 找不到空格下标，表示只输入是一个单词
      System.out.println(words.length());
      return;
    }
    String word = words.substring(index + 1);//截取最后一个空格后面的部分字符串
    System.out.println(word.length());
  }
}
```