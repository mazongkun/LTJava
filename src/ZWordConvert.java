/**
 * 6. Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 *
 *
 * 示例 1：
 *
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 示例 3：
 *
 * 输入：s = "A", numRows = 1
 * 输出："A"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 */

public class ZWordConvert {
    public static void main(String[] args) {
        System.out.println(zWordConvert("A", 1));
    }

    static String zWordConvert(String s, int numRows) {
        if (numRows == 1 || s.length() == 1) {
            return s;
        }

        int length = s.length();
        int index;
        int step = numRows + (numRows - 2);
        StringBuilder result = new StringBuilder();

        // line loop
        int line = 0;
        while (line < numRows) {
            if (line == 0 || line == numRows-1) {
                // first line & last line
                index = line;
                while (index < length) {
                    result.append(s.charAt(index));
                    index += step;
                }

            } else {
                // middle
                index = line;
                int stepInner = step - 2 * line;
                while (index < length) {
                    result.append(s.charAt(index));

                    index += stepInner;
                    // 步长交替，两次的和为step
                    stepInner = step - stepInner;
                }
            }

            line++;
        }

        return result.toString();
    }
}
