/**
 * 9. 回文数
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 *
 *
 *
 * 示例 1：
 *
 * 输入：x = 121
 * 输出：true
 * 示例 2：
 *
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3：
 *
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 * 示例 4：
 *
 * 输入：x = -101
 * 输出：false
 *
 *
 * 提示：
 *
 * -231 <= x <= 231 - 1
 *
 *
 * 进阶：你能不将整数转为字符串来解决这个问题吗？
 */

public class IsPalindrome {
    public static void main(String[] args) {
        int x = 1410110141;
        System.out.println("isPalindrome: " + isPalindrome(x));
    }

    static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        int newNum = 0;
        int copy = x;
        do {
            // 倒置x，再将新生成的数与x做比较
            int tmp = copy % 10;
            newNum = newNum * 10 + tmp;
            copy /= 10;
        } while (copy / 10 != 0 || copy > 0);
        return newNum == x;
    }
}











