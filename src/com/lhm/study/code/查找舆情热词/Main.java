package com.lhm.study.code.查找舆情热词;

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
