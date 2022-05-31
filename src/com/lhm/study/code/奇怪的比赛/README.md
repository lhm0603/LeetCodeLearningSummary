## 奇怪的比赛

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