/**
 * 671. 二叉树中第二小的节点
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
 *
 * 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。
 *
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [2,2,5,null,null,5,7]
 * 输出：5
 * 解释：最小的值是 2 ，第二小的值是 5 。
 * 示例 2：
 *
 *
 * 输入：root = [2,2,2]
 * 输出：-1
 * 解释：最小的值是 2, 但是不存在第二小的值。
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 25] 内
 * 1 <= Node.val <= 231 - 1
 * 对于树中每个节点 root.val == min(root.left.val, root.right.val)
 */
public class FindSecondMinimumValue {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {

    }

    static int secondMinimum;
    static int rootVal;
    static int findSecondMinimumValue(TreeNode root) {
        secondMinimum = -1;
        rootVal = root.val;
        dfs(root);
        return secondMinimum;
    }

    // 深度优先搜索
    static void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        // 如果已找到截止当前第二小的数，且当前检查的节点大于这个数，直接返回
        if (secondMinimum > -1 && node.val > secondMinimum) {
            return;
        }
        // 如果当前节点值大于根节点值(上一步筛选，有可能有小于secondMinimum，但又大于rootVal的值)，
        // 给secondMinimum赋值
        if (node.val > rootVal) {
            secondMinimum = node.val;
        }

        dfs(node.left);
        dfs(node.right);
     }
}
