[TOC]

# LeetCodeLearningSummary

[链接到CSDN博客](https://blog.csdn.net/h461415832/article/details/125016616?csdn_share_tail=%7B%22type%22%3A%22blog%22%2C%22rType%22%3A%22article%22%2C%22rId%22%3A%22125016616%22%2C%22source%22%3A%22h461415832%22%7D&ctrtid=V0GiE)

[链接到Github](https://github.com/lhm0603/LeetCodeLearningSummary)

## 【前言】

所有题目的解法均来自个人的思想，与网络上的解法可能略有不同。部分题目有吸收网络上的解题思想，在此感谢网上善于分享的博主！我也在此将自己刷题过程及个人解题想法整理出来，希望对你有帮助！
> PS：所有题目均来源于网络，若有错别字欢迎指出！若答案有错误的地方，也欢迎指出，互相学习！
>
> 刷题ing... 持续更新中...


## 【第18题】【入门】取近似值

写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。如果小数点后数值大于等于 0.5 ,向上取整；小于 0.5 ，则向下取整。
数据范围：保证输入的数字在 32 位浮点数范围内

**【输入描述】**

输入一个正浮点数值

**【输出描述】**

输出该数值的近似整数值

**【示例一】**

- 输入

  ```bash
  5.5
  ```

- 输出

  ```bash
  6
  ```

* 说明：0.5>=0.5，所以5.5需要向上取整为6

**【示例二】**

- 输入

  ```bash
  2.499
  ```

- 输出

  ```bash
  2
  ```

* 说明：0.499<0.5，2.499向下取整为2

**【解题思路】**

1. 题目的意思其实就是：四舍五入。使用Java中的Math.round函数即可

**【代码】**

```java
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        float v = input.nextFloat();
        System.out.println(Math.round(v));
    }
}

```

## 【第17题】查找舆情热词

输入正整数topN和文章数M，正整数topN表示要找出来的出现频率最高的topN个字符串，M篇文章中每篇文章会有两个字符串，一个是标题字符串，一个是正文字符串，字符串间有空格，每个单词被空格隔开。我们的目的就是把这M篇文章连标题带正文拆成一个个单词，然后统计这一堆单词出现频率最高的topN个。
统计规则：标题中出现的词语频率系数为3，正文中出现的词语频率系数为1，返回的答案应该按照词语出现从高到低排序，当词语出现次数频率相同时，在标题中出现频率次数高的排在前面，如果仍然相同，则按照词语在标题中出现的先后顺序进行排序，如果仍相同，则按照词语在正文中出现的先后顺序进行排序，先出现的排在前面。

**【输入描述】**

第一行输入为正整数topN和文章数M。然后由于每篇文章有标题和正文两行，因此后面有2*M行数据。从第二行起，按顺序处理每篇文章的标题串和正文串。

**【输出描述】**

出现频率topN高的单词，每个单词用‘ ’隔开。

**【示例一】**

- 输入

  ```
  5
  2
  Reactive Extensions for the JVM
  RxJava is a Java VM implementation of Reactive Extensions a library for composing asynchronous and Reactive event based programs Reactive by using Reactive observable sequences
  Concurrency JVM a within flow
  Practically parallelism in RxJava means running independent flows and merging their JVM results back into a single flow The operator flatMap does this by first mapping JVM each number from 1 to 10 Reactive into its own individual Flowable
  
  ```

- 输出

  ```
  Reactive JVM Extensions a for
  ```

* 说明

  ```
  用例是自己制作
  ```

**【解题思路】**

1. 字符串拆分
2. 记录每个字符串的出现频率，该字符串在标题中出现的最早索引，该字符串在正文中出现的最早索引
3. 然后根据题目要求，实现排序逻辑

**【代码】**

```java
import java.util.*;

public class Main {
    private static final String REGEX = " ";

    private static final HashMap<String, Word> WORDS = new HashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int topN = input.nextInt();
        int m = input.nextInt();
        input = new Scanner(System.in);
        for (int i = 0; i < m; i++) {
            String label = input.nextLine();
            String content = input.nextLine();
            String[] labelSplit = label.split(REGEX);
            for (int k = 0; k < labelSplit.length; k++) {// 拆分标题，并记录单词
                addWord(labelSplit[k], 3, k, Integer.MAX_VALUE);// 标题频率为3，标题出现索引为k。该单词不属于正文，contentIndex传递int最大值
            }
            String[] contentSplit = content.split(REGEX);
            for (int k = 0; k < contentSplit.length; k++) {
                addWord(contentSplit[k], 1, Integer.MAX_VALUE, k);// 正文频率为1，正文出现索引为k。该单词不属于标题，labelIndex传递int最大值
            }
        }
        ArrayList<Word> words = new ArrayList<>(WORDS.values());
        Collections.sort(words);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size() && i < topN; i++) {
            sb.append(words.get(i)).append(' ');
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - 1, sb.length());
        }
        System.out.println(sb);
    }

    private static void addWord(String text, int weight, int labelIndex, int contentIndex) {
        if (text.length() == 0) {
            return;
        }
        Word word = WORDS.get(text);
        if (word == null) {
            word = new Word(text, weight, labelIndex, contentIndex);
            WORDS.put(text, word);
        }
        word.workWeight += weight;
        word.labelMinIndex = Math.min(word.labelMinIndex, labelIndex);
        word.contentMinIndex = Math.min(word.contentMinIndex, contentIndex);
    }

    private static class Word implements Comparable<Word> {
        String text;
        int workWeight;
        int labelMinIndex;
        int contentMinIndex;

        public Word(String text, int workWeight, int labelMinIndex, int contentMinIndex) {
            this.text = text;
            this.workWeight = workWeight;
            this.labelMinIndex = labelMinIndex;
            this.contentMinIndex = contentMinIndex;
        }

        @Override
        public String toString() {
            return text;
        }

        @Override
        public int compareTo(Word o) {
            if (o.workWeight != workWeight) {
                return o.workWeight - workWeight;// 出现频率降序排列
            }
            if (o.labelMinIndex != labelMinIndex) {
                return labelMinIndex - o.labelMinIndex;// 频率相等，根据标题出现位置升序排列
            }
            if (o.contentMinIndex != contentMinIndex) {// 标题出现位置相等，根据正文出现位置升序排列
                return contentMinIndex - o.contentMinIndex;
            }
            return 0;
        }
    }
}

```


## 【第16题】奇怪的比赛

某电视台举办了低碳生活大奖赛。题目的计分规则相当奇怪：

每位选手需要回答10个问题（其编号为1到10），越后面越有难度。答对的，当前分数翻倍；答错了则扣掉与题号相同的分数（选手必须回答问题，不回答按错误处理）。

每位选手都有一个起步的分数为10分。

某获胜选手最终得分刚好是100分，如果不让你看比赛过程，你能推断出他（她）哪个题目答对了，哪个题目答错了吗？

如果把答对的记为1，答错的记为0，则10个题目的回答情况可以用仅含有1和0的串来表示。例如：0010110011 就是可能的情况。

你的任务是算出所有可能情况，并输出所有可能的01序列（包括已给出的0010110011），每个答案占一行。

**【输入描述】**

没有输入

**【输出描述】**

若干行01序列，若把每一行序列作为一个二进制数对待，则各行按照从小到大排列。
即：上一行序列的值应小于下一行的值。

**【解题思路】**

1. 题目关键信息：起始分数为10，题目总共10道题，每道题只有两种情况（对1，错0）
3. 最后注意输出要求需要排序
3. 题目可以使用递归，也可以使用循环将所有可能结果计算出来。显然用递归方式代码书写比较简洁
4. 因为每道题有两种情况，所以每次递归都需要计算两个情况，并继续递归

**【代码】**

```java
import java.util.Collection;
import java.util.TreeMap;

public class Main {
    private static final TreeMap<Integer, String> result = new TreeMap<>();

    public static void main(String[] args) {
        // 初始值定义
        int score = 10;
        int index = 1;
        int[] answer = new int[10];
        // 开始递归计算
        deal(answer, index, score);
        Collection<String> values = result.values();
        for (String value : values) {
            System.out.println(value);// 输出结果
        }
    }

    private static void deal(int[] answer, int index, int score) {
        if (index > 10) {// 这是结束条件，10次递归后，会将计算结果传入第11次递归。当次情况计算结束
            if (score == 100) {// 判断10次递归的总分数是否为100，是，则记录统计结果
                StringBuilder sb = new StringBuilder(answer.length);
                for (int i : answer) {
                    sb.append(i);
                }
                String value = sb.toString();
                int key = Integer.parseInt(value, 2);// 二进制转10进制
                result.put(key, value);// 存入treeMap，将自动根据key升序排列
            }
            return;
        }
        answer[index - 1] = 0; // 注意下标需要-1，因为题号从1开始（因为答错需要减去题号，所以题号需要按要求从1开始）
        deal(answer, index + 1, score - index);// 计算答错情况
        answer[index - 1] = 1;
        deal(answer, index + 1, score * 2);// 计算答对情况
    }
}
```
## 【第15题】快递运输

**【题目描述】**

一辆运送快递的货车，运送的快递均放在大小不等的长方体快递盒中，为了能够装载更多的快递，同时不能让货车超载，需要计算最多能装多少个快递。

快递的体积不受限制，快递数最多1000个，货车载重最大50000。

**【输入描述】**

第一行输入每个快递的重量，用英文逗号分隔，如：5,10,2,11

第二行输入货车的载重量，如：20

不需要考虑异常输入

**【输出描述】**

输出最多能装多少个快递，如：3

**【示例一】**

- 输入

  ```bash
  5,10,2,11
  20
  ```

- 输出

  ```bash
  3
  ```

* 说明

  货车的载重量为20，最多只能放三个快递5、10、2，因此输出3

**【解题思路】**

1. 此题目的是尽可能的多装在快递个数，所以，小包裹优先装载
2. 升序排列，从小到大依次叠加。并与货车载重量比较。没叠加一次计算一个快递

**【代码】**

```java
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String content = input.nextLine();
        int max = input.nextInt();
        String[] split = content.split(",");
        if (split.length == 0) {
            System.out.println(0);
            return;
        }
        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        Arrays.sort(nums);// 升序排列
        int count = deal(nums, max);
        System.out.println(count);
    }

    private static int deal(int[] nums, int max) {
        int sum = 0;
        int count = 0;
        for (int num : nums) {
            if (num + sum < max) { // 依次叠加判断
                count++;// 统计个数
                sum += num;
            }
        }
        return count;
    }
}
```


## 【第14题】火星人运算符

已知火星人使用的运算符为#、$，其与地球人的等价公式如下：

`x#y = 2x+3y+4`

`x$y = 3*x+y+2`

其中x、y是无符号整数
地球人公式按C语言规则计算
火星人公式中，$的优先级高于#，相同的运算符，按从左到右的顺序计算 现有一段火星人的字符串报文，请你来翻译并计算结果。

**【输入描述】**

火星人字符串表达式（结尾不带回车换行）

* 输入说明：

  ```
  字符串为仅由无符号整数和操作符（#、$）
  组成的计算表达式。例如：123#45#6778
  用例保证字符串中，操作数与操作符之间没有任何分隔符。
  用例保证操作数取值范围为32位无符号整数。
  保证输入以及计算结果不会出现整型溢出。
  保证输入的字符串为合法的求值报文，例如：123#45#6778
  保证不会出现非法的求值报文，例如类似这样字符串：
  #4$5 //缺少操作数
  4$5# //缺少操作数
  4#$5 //缺少操作数
  4 $5 //有空格
  3+4-5*6/7 //有其它操作符
  12345678987654321$54321 //32位整数计算溢出
  ```

**【输出描述】**

根据输入的火星人字符串输出计算结果（结尾不带回车换行）

**【示例一】**

* 输入

  ```
  7#6$5#12
  ```

* 输出

  ```
  226
  ```

* 说明：

  ```
  7#6$5#12
  = 7#(3*6+5+2)#12
  =7#25#12
  =(2*7+3*25+4)#12
  =93#12
  =2*93+3*12+4
  226
  ```

**【解题思路】**

1. 先将两个公式抽出一个方法，方便使用
2. 按符号优先级，先根据优先级较低的符号 `#` 进行拆分
3. 若拆分后不包含其他运算符，则直接转换为整型
4. 若拆分后的字符串中包含优先级较高的符号`$`。分别计算带有符号`$`的表达式的结果
5. 之后组成一个只有 `#`符号参与运算的列表，从左到右依次循环运算



**【代码】**

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String content = input.nextLine();
        String[] split = content.split("#");// 先根据优先级较低符号拆分
        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (!s.contains("$")) {// 拆分部分包不含$，则表示一个单独的数字
                nums[i] = Integer.parseInt(s);
                continue;
            }
            nums[i] = deal$(s);// 拆分部分包含 $ 运算，则先将 $的运算表达式结果计算出来
        }
        
        // 以下部分是计算只有 # 参与的表达式，并最终输出结果
        int x = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int y = nums[i];
            x = calculate2(x, y);
        }
        System.out.println(x);
    }

    private static int deal$(String str) { // 计算只有 $ 参与的表达式，并返回计算结果
        String[] split = str.split("\\$");
        int x = Integer.parseInt(split[0]);
        for (int i = 1; i < split.length; i++) {
            int y = Integer.parseInt(split[i]);
            x = calculate1(x, y);
        }
        return x;
    }

    /**
     * 火星文 $ 运算
     */
    private static int calculate1(int x, int y) {
        return 3 * x + y + 2;
    }

    /**
     * 火星文 # 运算
     */
    private static int calculate2(int x, int y) {
        return 2 * x + 3 * y + 4;
    }
}
```

## 【第13题】叠积木

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

## 【第12题】质素因子

功能：输入一个正整数，按照从小到大的顺序输出它的所有质因子（重复的也要列举）（如180的质因子为2 2 3 3 5 ）

数据范围：
$$
1 ≤ n ≤ 2 × 10^9 + 14
$$

**【输入描述】**

输入一个整数

**【输出描述】**

按照从小到大的顺序输出它的所有质数的因子，以空格隔开。

**【示例一】**

- 输入

  ```bash
  180
  ```

- 输出

  ```bash
  2 2 3 3 5
  ```


**【解题思路】**

1. 这道题目一开始看确实有点懵，题目是要求分解质因数，只是要求所有因数都是质数。
2. 注意数据范围达到long的取值范围，一不小心就会性能超标
3. 这道题折腾了两三次，终于是通过了所有测试用例。（特别是：2000000014，一不小心就性能超标）
4. 其他的解题思路请看代码注释，不一定是最优解，但是通过了。
5. 如果不对的地方，请指出！谢谢

**【代码】**

```java
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long num = input.nextLong();
        deal(num);
    }

    private static void deal(long num) {
        long realNum = num;
        double sqrt = Math.sqrt(num);
        long prime = 2;// 当前可疑质素因子
        ArrayList<Long> list = new ArrayList<>();
        while (true) {
            if (num % prime == 0) {
                list.add(prime);//找到质数因子
                num = num / prime;//更新num，对新的num循环查找下一个质数因子
                continue;
            }
            prime = nextPrime(prime);
            if (num < prime || prime > sqrt) {
                // 若可以质数因子超过num本身，或者质数因子超过原始num的平方根，则无需继续查找下去。但有一种特殊情况
                break;
            }
        }
        // 特殊情况，存在质数因子比原始正整数num的平方根还大的情况，但这种情况下，只会存在1个比num的平方根大的质数因子
        if (num > prime && realNum % num == 0) {
            list.add(num);
        }
        if (list.size() == 0) {
            list.add(num);// 若没有找到质数因子，则输出其本身
        }

        // 拼接输出，虽然最后多一个空格也可以通过，强逼症去掉最后空格
        StringBuilder sb = new StringBuilder();
        for (Long l : list) {
            sb.append(l).append(' ');
        }
        System.out.println(sb.delete(sb.length() - 1, sb.length()));
    }

    /**
     * 查找n的下一个质素
     */
    private static long nextPrime(long n) {
        long m = n + 1;
        if (isPrime(m)) {
            return m;
        }
        return nextPrime(m);
    }

    /**
     * 判断一个数是否是质数
     */
    private static boolean isPrime(long n) {
        if (n < 2) {
            return false;
        }
        double sqrt = Math.sqrt(n);
        for (long i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}

```


## 【第11题】进制转换

写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。

数据范围：保证结果在
$$
1 ≤ n ≤ 2^{31} - 1
$$


**【输入描述】**

输入一个十六进制的数值字符串。

**【输出描述】**

输出该数值的十进制字符串。

**【示例一】**

- 输入

  ```bash
  0xAA
  ```

- 输出

  ```bash
  170
  ```

**【解题思路】**

1. 题目已经将值结果保证在Integer取值范围，所以可以直接使用Integer
2. 注意用例，16进制开头是包含`0x`的，而Java的`Integer.parseInt` 是不包含`0x`的

**【代码】**

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

## 【第10题】字符串分割

* 输入一个字符串，请按长度为8拆分每个输入字符串并进行输出；
* 长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。

**【输入描述】**

连续输入字符串(每个字符串长度小于等于100)

**【输出描述】**

依次输出所有分割后的长度为8的新字符串

**【示例一】**

- 输入

  ```bash
  abc
  ```

- 输出

  ```bash
  abc00000
  ```

**【解题思路】**

1. 这道题的解法应该有N多种，我的想法是每次循环8次，从输入的字符串s中依次往后取8个字符，若s字符串已经被使用完，则补0。
2. 每一轮8次循环结束后，判断字符串s是不是已经用完。则结束循环。
3. 注意空字符串不处理。

**【代码】**

```java

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        deal(input.nextLine());
    }

    private static void deal(String s) {
        int index = 0;
        int length = s.length();
        while (index < length) {// 结束条件，包含空字符串情况
            StringBuilder sb = new StringBuilder(8);
            for (int i = 0; i < 8; i++) {
                if (index < length) {
                    sb.append(s.charAt(index));
                } else {
                    sb.append('0');// 若字符已经用完，下标已越界，则追加0
                }
                index++;
            }
            System.out.println(sb);
        }
    }
}

```

## 【第9题】字符串最后一个单词的长度

写出一个程序，接受一个由字母、数字和空格组成的字符串，和一个字符，然后输出输入字符串中该字符的出现次数。（不区分大小写字母）

数据范围： `1 ≤ n ≤ 1000`

**【输入描述】**

第一行输入一个由字母和数字以及空格组成的字符串，第二行输入一个字符。

**【输出描述】**

输出输入字符串中含有该字符的个数。（不区分大小写字母）

**【示例一】**

- 输入

  ```bash
  ABCabc
  A
  ```

- 输出

  ```bash
  2
  ```

**【解题思路】**

1. 循环查找字符串进行比较即可，注意不区分大小写！

**【代码】**

```java
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String content = input.nextLine().toLowerCase(Locale.ENGLISH);
        char tag = input.nextLine().toLowerCase(Locale.ENGLISH).charAt(0);
        int count = 0;
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == tag) {
                count++;
            }
        }
        System.out.println(count);
    }
}
```

## 【第8题】明明的随机数

明明生成了*N*个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。

数据范围：`1≤n≤1000` ，输入的数字大小满足 `1≤val≤500`

**【输入描述】**

第一行先输入随机整数的个数 N 。 接下来的 N 行每行输入一个整数，代表明明生成的随机数。 具体格式可以参考下面的"示例"。

**【输出描述】**

输出多行，表示输入数据处理后的结果

**【示例一】**

- 输入

  ```bash
  3
  2
  2
  1
  ```

- 输出

  ```bash
  1
  2
  ```

* 说明

  ```
  输入解释：
  第一个数字是3，也即这个小样例的N=3，说明用计算机生成了3个1到500之间的随机整数，接下来每行一个随机数字，共3行，也即这3个随机数字为：
  2
  2
  1
  所以样例的输出为：
  1
  2
  ```

**【解题思路】**

1. 去重，可以使用Set集合。
2. 从小到大排序，即升序。所以直接使用TreeSet即可。TreeSet内部已经根据自然升序排列。
3. 注意输出要换行输出

**【代码】**

```java
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int len = input.nextInt();
        TreeSet<Integer> result = new TreeSet<>();
        for (int i = 0; i < len; i++) {
            result.add(input.nextInt());
        }
        for (Integer num : result) {
            System.out.println(num);
        }
    }
}
```


## 【第7题】字符串最后一个单词的长度

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

## 【第6题】GPU算力

为了充分发挥Gpu算力，
需要尽可能多的将任务交给GPU执行，
现在有一个任务数组，
数组元素表示在这1s内新增的任务个数，
且每秒都有新增任务，
假设GPU最多一次执行n个任务，
一次执行耗时1s，
在保证Gpu不空闲的情况下，最少需要多长时间执行完成。

**【输入描述】**

第一个参数为gpu最多执行的任务个数
取值范围1~10000
第二个参数为任务数组的长度
取值范围1~10000
第三个参数为任务数组
数字范围1~10000

**【输出描述】**

执行完所有任务需要多少秒

**【示例一】**

- 输入

  ```bash
  3
  5
  1 2 3 4 5
  ```

- 输出

  ```bash
  6
  ```

- 说明：
  一次最多执行3个任务，最少耗时6s

执行完所有任务需要多少秒

**【示例二】**

- 输入

  ```bash
  4
  5
  5 4 1 1 1
  ```

- 输出

  ```bash
  5
  ```

- 说明：
  一次最多执行4个任务，最少耗时5s

**【解题思路】**

1. 一开始看题没看懂，看了示例才发现，如果数组位置任务比较少，那么执行到后期时，部分算力可能会轮空。
2. 比如示例二，执行到第4秒时，只能时数组下标0和1的位置还存在任务需要执行，那么就会有两个算力轮空。
3. 所以，解题思路就是，要优先让算力多的任务参与执行。

**【代码】**

```java
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int taskCunt = input.nextInt();
        int taskArrLen = input.nextInt();
        int[] taskArr = new int[taskArrLen];
        for (int i = 0; i < taskArrLen; i++) {
            taskArr[i] = input.nextInt();
        }
        int count = deal(taskArr, taskCunt);
        System.out.println(count);
    }

    private static int deal(int[] taskArr, int taskCunt) {
        ArrayList<Integer> tasks = new ArrayList<>();
        for (int i : taskArr) {
            tasks.add(i);
        }
        sortDesc(tasks);
        int sCount = 0;
        while (tasks.size() > 0) {
            for (int i = 0; i < taskCunt; i++) {// 每次取出3个
                if (i < tasks.size()) {
                    int num = tasks.get(i) - 1;
                    if (num == 0) {// 若无任务，则移除
                        tasks.remove(i);
                    } else {
                        tasks.set(i, num);
                    }
                }
            }
            sCount++;// 每循环一次，计数一次
            sortDesc(tasks);// 需要重新降序排列
        }
        return sCount;
    }

    private static void sortDesc(ArrayList<Integer> tasks) {
        // 降序排列
        tasks.sort((o1, o2) -> o2 - o1);
    }
}
```

## 【第5题】数组对元素求和最小值

给定两个整数数组，arr1、arr2，数组元素按升序排列；
假设从arr1、arr2中分别取出一个元素，可构成一对元素；
现在需要取出k对元素，并对取出的所有元素求和，计算和的最小值；
注意：两对元素对应arr1、arr2的下标是相同的，视为同一对元素。

**【输入描述】**

输入两行数组arr1、arr2
arr1，arr2中的每个元素e， `0< e <1000`
接下来一行，正整数k `0 < k <= arr1.size * arr2.size`

**【输出描述】**

满足要求的最小值

**【示例一】**

- 输入

  ```bash
  1 1 2
  1 2 3
  2
  ```

- 输出

  ```bash
  4
  ```

- 说明：

  用例中需要取两个元素，取第一个数组第0个元素与第二个数组第0个元素组成一个元素[1,1];
  取第一个数组第1个元素与第二个数组第0个元素组成一个元素[1,1];
  求和为1+1+1+1=4 ,满足要求最小。

**【示例二】**

- 输入

  ```bash
  1 2 3 4 
  4 5 6 9
  4
  ```

- 输出

  ```bash
  24
  ```



**【解题思路】**

1. 把两个数组所有可能组成的对数全部统计存入集合中，冰将集合升序排列
2. 然后根据需要求和的个数，从集合开头取出对应个数的值进行求和即可

**【代码】**

```java

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String arr1Str = input.nextLine();
        String arr2Str = input.nextLine();
        int count = input.nextInt();
        int[] arr1 = parseArr(arr1Str);
        int[] arr2 = parseArr(arr2Str);
        ArrayList<Integer> allSum = new ArrayList<>(arr1.length * arr2.length);
        for (int n : arr1) {
            for (int m : arr2) {
                allSum.add(n + m);
            }
        }
        Collections.sort(allSum);
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += allSum.get(i);
        }
        System.out.println(sum);
    }

    /**
     * 将输入格式转化为int数组
     */
    private static int[] parseArr(String arrStr) {
        String[] strings = arrStr.split(" ");
        int[] arr = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            arr[i] = Integer.parseInt(strings[i]);
        }
        return arr;
    }
}
```

## 【第4题】组成最大数

小组中每位都有一张卡片，卡片上是6位内的正整数，将卡片连起来可以组成多种数字，计算组成的最大数字。



**【输入描述】**

“,”号分割的多个正整数字符串，不需要考虑非数字异常情况，小组最多25个人



**【输出描述】**

最大的数字字符串



**【示例一】**

* 输入

  ```
  22,221  
  ```

* 输出

  ```
  22221
  ```

**【示例二】**

* 输入

  ```
  4589,101,41425,9999
  ```

* 输出

  ```
  9999458941425101
  ```



**【解题思路】**

1. 按字符串自然大小排序之后，数字字符从大到小依次为9>8>7...3>2>1。

2. 且 字符串“89”要大于“723”，因为字符串自然排序匹配规则是根据第一个字符比较大小，若相等，则比较下一个，超出部分摄取比对。

3. 所以只需要对输入的所有数字字符串进行排序，之后按顺序输出即可！



**【代码】**

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

## 【第3题】猴子爬山

一天一只顽猴想去从山脚爬到山顶，途中经过一个有个N个台阶的阶梯，但是这猴子有一个习惯： 每一次只能跳1步或跳3步，试问猴子通过这个阶梯有多少种不同的跳跃方式？



**【输入描述】**

输入只有一个整数N（0<N<=50）此阶梯有多少个阶梯



**【输出描述】**

输出有多少种跳跃方式（解决方案数）



**【示例一】**

* 输入

  ```
  50
  ```

* 输出

  ```
  122106097
  ```



**【示例二】**

* 输入

  ```
  3
  ```

* 输出

  ```
  2
  ```



**【解题思路】**

分析规律：

```
台阶个数，对应跳跃方案数
0个台阶，有1个方案。那就是不跳
1个台阶，有1个方案。跳1个台阶（1）
2个台阶，有1个方案。一个一个跳，跳两次（12）
3个台阶，有2个方案。一个一个跳，跳三次。以及一次性跳三阶（123,3）
4个台阶，有3个方案。（1234,14,34）
5个台阶，有4个方案。（12345,125,145,345）
6个台阶，有6个方案。（123456,1236,1256,1456,3456,36）
7个台阶，有9个方案。（123456789....369)

发现规律
1、台阶数n小于3时，都只有一种方案。
2、台阶数n大于等于3时，跳跃方案恰好等于(n-1)的台阶方案数+(n-3)的台阶方案数。例如：
台阶n=6的方案数等于5的台阶方案数+3的台阶方案数。
```

**【代码】**

```java
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        int count = cal(num);
        System.out.println(count);
    }

    private static int cal(int num) {
        if (num < 3) {
            return 1;
        }
        return cal(num - 1) + cal(num - 3);
    }

    //台阶数：0  1  2  3  4  5  6  7  8  9
    //方案数：1  1  1  2  3  4  6  9  13 19
}
```

## 【第2题】勾股数元组

如果三个正整数A、B、C ,A²+B²=C²则为勾股数
如果ABC之间两两互质，即A与B，A与C，B与C均互质没有公约数，
则称其为勾股数元组。
请求出给定n~m范围内所有的勾股数元组

**【输入描述】**

起始范围
`1 < n < 10000`
`n < m < 10000`

**【输出描述】**

ABC保证`A<B<C`
输出格式`A B C`
多组勾股数元组，按照A B C升序的排序方式输出。
若给定范围内，找不到勾股数元组时，输出`Na`。

**【示例一】**

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

**【示例二】**

- 输入

  ```bash
  5
  10
  ```

- 输出

  ```bash
  Na
  ```

**【解题思路】**

1. 首先，写一个函数判断两个数之间互为质数。可以用最大公约数为1的方式解决
2. 然后根据已知条件，`a<b<c` ，可以使用三层循环，分别根据条件判断，符合条件则输出，并记录符合条件次数，若没有符合条件的结果，则循环结束后输出`Na`

**【代码】**

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
## 【第1题】TLV编码

TLV编码是按TagLengthValue格式进行编码的，一段码流中的信元用tag标识，tag在码流中唯一不重复，length表示信元value的长度，value表示信元的值，码流以某信元的tag开头，tag固定占一个字节，length固定占两个字节，字节序为小端序，现给定tlv格式编码的码流以及需要解码的信元tag，请输出该信元的value。

输入码流的16进制字符中，不包括小写字母；
且要求输出的16进制字符串中也不要包含小写字母；
码流字符串的最大长度不超过50000个字节。

**【输入描述】**

第一行为第一个字符串 ，表示待解码信元的tag；
输入第二行为一个字符串， 表示待解码的16进制码流；
字节之间用空格分割。

**【输出描述】**

输出一个字符串，表示待解码信元以16进制表示的value。

**【示例一】**

- 输入

  ```bash
  31
  32 01 00 AE 90 02 00 01 02 30 03 00 AB 32 31 31 02 00 32 33 33 01 00 CC
  ```

- 输出

  ```bash
  32 33
  ```

- 说明：
  需要解析的信源的tag是31；
  从码流的起始处开始匹配，tag为32的信元长度为1(01 00,小端序表示为1)；
  第二个信元的tag为90 其长度为2；
  第三个信元的tag为30 其长度为3；
  第四个信元的tag为31 其长度为2(02 00)；
  所以返回长度后面的两个字节即可 为 32 33。

**【解题】**

1. 这道题主要是要理解`小端序`的概念，其实就是两个十六进制反过来写，处理的时候需要反过来拼接
2. 其他题目已经说的比较清楚了，tag开头占1个字节，length固定占两个字节，再根据length取出后面的value值。依次往后查询到目标tag，取出对应的value即可。
3. 注意length的小端序处理

**【代码】**

```java

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String tag = input.nextLine();
        String content = input.nextLine();
        String[] split = content.split(" ");
        for (int i = 0; i < split.length; i++) {
            String curTag = split[i];
            String len1 = split[++i];
            String len2 = split[++i];
            int length = formatRadix16To10(len2 + len1);// 注意小端序要反过来相加
            // 若tag不是目标，则跳过
            if (!tag.equalsIgnoreCase(curTag)) {
                i += length;// 跳过无用的value值，查找下一个tag
                continue;
            }
            System.out.println(takeString(split, i + 1, length));
            break;
        }
    }

    private static String takeString(String[] strings, int start, int len) {
        StringBuilder result = new StringBuilder();
        for (int i = start; i < strings.length && i < start + len; i++) {
            result.append(strings[i]).append(" ");
        }
        if (result.length() > 0) {
            result.delete(result.length() - 1, result.length());
        }
        return result.toString();
    }

    // 字符串16进制值转10进制
    private static int formatRadix16To10(String str) {
        return Integer.parseInt(str, 16);
    }
}
```
