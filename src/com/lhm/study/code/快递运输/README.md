## 快递运输

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