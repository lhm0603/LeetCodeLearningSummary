
## 查找舆情热词

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