
## GPU算力

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