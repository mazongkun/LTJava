import java.util.List;

/**
 * /
 * 2. 两数相加
 *   给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 *   请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 *   你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 *   输入：l1 = [2,4,3], l2 = [5,6,4]
 *   输出：[7,0,8]
 *   解释：342 + 465 = 807.
 *   示例 2：
 *
 *   输入：l1 = [0], l2 = [0]
 *   输出：[0]
 *   示例 3：
 *
 *   输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 *   输出：[8,9,9,9,0,0,0,1]
 *    
 *
 *   提示：
 *
 *   每个链表中的节点数在范围 [1, 100] 内
 *   0 <= Node.val <= 9
 *   题目数据保证列表表示的数字不含前导零
 *  /
 */
public class AddTwoNumbers {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        
    }


    static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;

        boolean needAdd = false; // 是否需要进1
        ListNode cur = null;
        ListNode cur1 = l1, cur2 = l2;
        while (cur1 != null || cur2 != null) {
            int add = needAdd ? 1 : 0; // 补进1
            int val1 = cur1 == null ? 0: cur1.val;
            int val2 = cur2 == null ? 0: cur2.val;
            int val = val1 + val2 + add;
            needAdd = val >= 10;
            val = val % 10;

            if (cur == null) {
                // head
                result = cur = new ListNode(val);
            } else {
                // not head
                cur.next = new ListNode(val);
                cur = cur.next;
            }

            cur1 = cur1 == null ? null : cur1.next;
            cur2 = cur2 == null ? null : cur2.next;
        }

        if (needAdd) { // 最后补进1
            cur.next = new ListNode(1);
        }
        return result;
    }
}
