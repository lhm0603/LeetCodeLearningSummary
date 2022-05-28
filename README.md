# LeetCodeLearningSummary
编程算法刷题学习及总结


# 【前言】
所有题目的解法均来自个人的思想，与网络上的解法可能略有不同。部分题目有吸收网络上的解题思想，在此感谢网上善于分享的博主！我也在此将自己刷题过程及个人解题想法整理出来，希望对你有帮助！
> PS：所有题目均来源于网络，若有错别字欢迎指出！若答案有错误的地方，也欢迎指出，互相学习！

# 【第1题】TLV编码

TLV编码是按TagLengthValue格式进行编码的，一段码流中的信元用tag标识，tag在码流中唯一不重复，length表示信元value的长度，value表示信元的值，码流以某信元的tag开头，tag固定占一个字节，length固定占两个字节，字节序为小端序，现给定tlv格式编码的码流以及需要解码的信元tag，请输出该信元的value。

输入码流的16进制字符中，不包括小写字母；
且要求输出的16进制字符串中也不要包含小写字母；
码流字符串的最大长度不超过50000个字节。

## 输入描述

第一行为第一个字符串 ，表示待解码信元的tag；
输入第二行为一个字符串， 表示待解码的16进制码流；
字节之间用空格分割。

## 输出描述

输出一个字符串，表示待解码信元以16进制表示的value。

## 示例一

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

## 解题

1. 这道题主要是要理解`小端序`的概念，其实就是两个十六进制反过来写，处理的时候需要反过来拼接
2. 其他题目已经说的比较清楚了，tag开头占1个字节，length固定占两个字节，再根据length取出后面的value值。依次往后查询到目标tag，取出对应的value即可。
3. 注意length的小端序处理

## java代码

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

# 【第2题】勾股数元组

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

# 【第3题】猴子爬山

一天一只顽猴想去从山脚爬到山顶，途中经过一个有个N个台阶的阶梯，但是这猴子有一个习惯： 每一次只能跳1步或跳3步，试问猴子通过这个阶梯有多少种不同的跳跃方式？



## 输入描述

输入只有一个整数N（0<N<=50）此阶梯有多少个阶梯



## 输出描述

输出有多少种跳跃方式（解决方案数）



## 示例一

* 输入

  ```
  50
  ```

* 输出

  ```
  122106097
  ```



## 示例二

* 输入

  ```
  3
  ```

* 输出

  ```
  2
  ```



## 解题

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

## java代码

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

# 【第4题】组成最大数

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

# 【第5题】数组对元素求和最小值

给定两个整数数组，arr1、arr2，数组元素按升序排列；
假设从arr1、arr2中分别取出一个元素，可构成一对元素；
现在需要取出k对元素，并对取出的所有元素求和，计算和的最小值；
注意：两对元素对应arr1、arr2的下标是相同的，视为同一对元素。

## 输入描述

输入两行数组arr1、arr2
arr1，arr2中的每个元素e， `0< e <1000`
接下来一行，正整数k `0 < k <= arr1.size * arr2.size`

## 输出描述

满足要求的最小值

## 示例一

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

## 示例二

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



## 解题

1. 把两个数组所有可能组成的对数全部统计存入集合中，冰将集合升序排列
2. 然后根据需要求和的个数，从集合开头取出对应个数的值进行求和即可

## java代码

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

# 【第6题】GPU算力

为了充分发挥Gpu算力，
需要尽可能多的将任务交给GPU执行，
现在有一个任务数组，
数组元素表示在这1s内新增的任务个数，
且每秒都有新增任务，
假设GPU最多一次执行n个任务，
一次执行耗时1s，
在保证Gpu不空闲的情况下，最少需要多长时间执行完成。

## 输入描述

第一个参数为gpu最多执行的任务个数
取值范围1~10000
第二个参数为任务数组的长度
取值范围1~10000
第三个参数为任务数组
数字范围1~10000

## 输出描述

执行完所有任务需要多少秒

## 示例一

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

## 示例二

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

## 解题

1. 一开始看题没看懂，看了示例才发现，如果数组位置任务比较少，那么执行到后期时，部分算力可能会轮空。
2. 比如示例二，执行到第4秒时，只能时数组下标0和1的位置还存在任务需要执行，那么就会有两个算力轮空。
3. 所以，解题思路就是，要优先让算力多的任务参与执行。

## java代码

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

# 【第7题】字符串最后一个单词的长度

计算字符串最后一个单词的长度，单词以空格隔开，字符串长度小于5000。（注：字符串末尾不以空格为结尾）

## 输入描述

输入一行，代表要计算的字符串，非空，长度小于5000。

## 输出描述

输出一个整数，表示输入字符串最后一个单词的长度。

## 示例一

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

## 解题

1. 这道题目很简单，特别是已经强调最后不以空格结尾，所以其实只要通过字符串的`lastIndexOf`找出最后一个空格的下标即可截取最后一个单词的内容，并输出单词长度。
2. 若找不到最后一个空格的下标，则表示整个字符串是一个单词。直接输出字符串长度即可

## java代码

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


# 【第8题】明明的随机数

明明生成了*N*个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。

数据范围：`1≤n≤1000` ，输入的数字大小满足 `1≤val≤500`

## 输入描述

第一行先输入随机整数的个数 N 。 接下来的 N 行每行输入一个整数，代表明明生成的随机数。 具体格式可以参考下面的"示例"。

## 输出描述

输出多行，表示输入数据处理后的结果

## 示例一

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

## 解题

1. 去重，可以使用Set集合。
2. 从小到大排序，即升序。所以直接使用TreeSet即可。TreeSet内部已经根据自然升序排列。
3. 注意输出要换行输出

## java代码

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

# 【第9题】字符串最后一个单词的长度

写出一个程序，接受一个由字母、数字和空格组成的字符串，和一个字符，然后输出输入字符串中该字符的出现次数。（不区分大小写字母）

数据范围： `1 ≤ n ≤ 1000`

## 输入描述

第一行输入一个由字母和数字以及空格组成的字符串，第二行输入一个字符。

## 输出描述

输出输入字符串中含有该字符的个数。（不区分大小写字母）

## 示例一

- 输入

  ```bash
  ABCabc
  A
  ```

- 输出

  ```bash
  2
  ```

## 解题

1. 循环查找字符串进行比较即可，注意不区分大小写！

## java代码

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

# 【第10题】字符串分割

* 输入一个字符串，请按长度为8拆分每个输入字符串并进行输出；
* 长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。

## 输入描述

连续输入字符串(每个字符串长度小于等于100)

## 输出描述

依次输出所有分割后的长度为8的新字符串

## 示例一

- 输入

  ```bash
  abc
  ```

- 输出

  ```bash
  abc00000
  ```

## 解题

1. 这道题的解法应该有N多种，我的想法是每次循环8次，从输入的字符串s中依次往后取8个字符，若s字符串已经被使用完，则补0。
2. 每一轮8次循环结束后，判断字符串s是不是已经用完。则结束循环。
3. 注意空字符串不处理。

## java代码

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

持续更新中...