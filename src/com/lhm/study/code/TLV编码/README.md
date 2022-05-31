## TLV编码

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
