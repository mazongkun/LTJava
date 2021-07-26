/**
 * 1736. 替换隐藏数字得到的最晚时间
 * 给你一个字符串 time ，格式为 hh:mm（小时：分钟），其中某几位数字被隐藏（用 ? 表示）。
 *
 * 有效的时间为 00:00 到 23:59 之间的所有时间，包括 00:00 和 23:59 。
 *
 * 替换 time 中隐藏的数字，返回你可以得到的最晚有效时间。
 *
 *
 *
 * 示例 1：
 *
 * 输入：time = "2?:?0"
 * 输出："23:50"
 * 解释：以数字 '2' 开头的最晚一小时是 23 ，以 '0' 结尾的最晚一分钟是 50 。
 * 示例 2：
 *
 * 输入：time = "0?:3?"
 * 输出："09:39"
 * 示例 3：
 *
 * 输入：time = "1?:22"
 * 输出："19:22"
 *
 *
 * 提示：
 *
 * time 的格式为 hh:mm
 * 题目数据保证你可以由输入的字符串生成有效的时间
 */
public class MaximumTime {
    public static void main(String[] args) {
        String time = "?3:02";
        System.out.println(maximumTime(time));
    }

    static String maximumTime(String time) {
        char[] arr = time.toCharArray();
        if (arr[0] == '?' && arr[1] == '?') {
            arr[0] = '2';
            arr[1] = '3';
        } else if (arr[0] == '?') {
            arr[0] = (arr[1] - '0' > 3) ? '1' : '2';
        } else if (arr[1] == '?') {
            arr[1] = (arr[0] == '2') ? '3' : '9';
        }

        arr[3] = (arr[3] == '?') ? '5' : arr[3];
        arr[4] = (arr[4] == '?') ? '9' : arr[4];
        return String.valueOf(arr);
    }
}
