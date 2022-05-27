# 数组对元素求和最小值

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