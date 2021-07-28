import java.util.Deque;
import java.util.LinkedList;

public class RemoveKdigits {

    public static void main(String[] args) {
            System.out.println(removeKdigits("2132142321", 3));
    }

    /**
     * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
     *
     *  
     * 示例 1 ：
     *
     * 输入：num = "1432219", k = 3
     * 输出："1219"
     * 解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
     * 示例 2 ：
     *
     * 输入：num = "10200", k = 1
     * 输出："200"
     * 解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
     * 示例 3 ：
     *
     * 输入：num = "10", k = 2
     * 输出："0"
     * 解释：从原数字移除所有的数字，剩余为空就是 0 。
     *  
     *
     * 提示：
     *
     * 1 <= k <= num.length <= 105
     * num 仅由若干位数字（0 - 9）组成
     * 除了 0 本身之外，num 不含任何前导零
     *
     * @param num
     * @param k
     * @return
     */
    private static String removeKdigits(String num, int k) {
        Deque<Character> deque = new LinkedList<>();
        // 创建一个单调栈（使用双向列表实现）
        // 遍历，依次比较当前数字与其左侧的数字，若左侧数字大，则删掉左侧数字
        for (int i=0; i<num.length(); i++) {
            char digit = num.charAt(i);
            while (k > 0 && ! deque.isEmpty() && deque.peekLast() > digit) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(digit);
        }

        while (k>0) {
            // 删除个数未满k个，删除列表尾部，
            deque.pollLast();
            k--;
        }
//        System.out.println("deque: " + deque);
        // 移除头部的0
        while (! deque.isEmpty() && deque.peek() == '0') {
            deque.pollFirst();
        }

        StringBuilder sb = new StringBuilder();
        while (! deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }
        // 若删除数字后列表为空，直接返回0
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
