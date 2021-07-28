import java.util.*;

/**
 * 863. 二叉树中所有距离为 K 的结点
 * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
 *
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * 输出：[7,4,1]
 * 解释：
 * 所求结点为与目标结点（值为 5）距离为 2 的结点，
 * 值分别为 7，4，以及 1
 *
 *
 *
 * 注意，输入的 "root" 和 "target" 实际上是树上的结点。
 * 上面的输入仅仅是对这些对象进行了序列化描述。
 *
 *
 * 提示：
 *
 * 给定的树是非空的。
 * 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
 * 目标结点 target 是树上的结点。
 * 0 <= K <= 1000.
 */
public class DistanceK {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        if (k == 0) {
            result.add(target.val);
            return result;
        }
        // 先将root放入map
        map.put(root, null);
        traverse(root);
        findNode(target, k);

        return result;
    }

    private static List<Integer> result = new ArrayList<>();
    private static Map<TreeNode, TreeNode> map = new HashMap<>();
    private static void findNode(TreeNode node, int stepLeft) {
        if (node == null) {
            return;
        }
        TreeNode parent = map.get(node);
        TreeNode left = node.left;
        TreeNode right = node.right;
        // 从map中删除以当前节点node为key的值，避免后续重复检查
        map.remove(node);

        if (stepLeft == 0) {
            // get target
            result.add(node.val);
            return;
        }

        if (parent != null && map.containsKey(parent))
            findNode(parent, stepLeft - 1);
        if (left != null && map.containsKey(left))
            findNode(left, stepLeft - 1);
        if (right != null && map.containsKey(right))
            findNode(right, stepLeft - 1);
    }

    // 将所有节点放入map: key = curNode, val = parent
    private static void traverse(TreeNode node) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            map.put(node.left, node);
        }
        if (node.right != null) {
            map.put(node.right, node);
        }

        traverse(node.left);
        traverse(node.right);
    }
}
