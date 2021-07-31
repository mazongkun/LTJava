import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 1104. 二叉树寻路
 * 在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。
 *
 * 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
 *
 * 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
 *
 *
 *
 * 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。
 *
 *
 *
 * 示例 1：
 *
 * 输入：label = 14
 * 输出：[1,3,4,14]
 * 示例 2：
 *
 * 输入：label = 26
 * 输出：[1,2,6,10,26]
 *
 *
 * 提示：
 *
 * 1 <= label <= 10^6
 */

public class PathInZigZagTree {
    public static void main(String[] args) {
        List<Integer> list = pathInZigZagTree(14);
        for (int i: list) {
            System.out.print(" " + i);
        }
    }

    private static List<Integer> pathInZigZagTree(int label) {
        LinkedList<Integer> result = new LinkedList<>();
        // 计算label在第几行
        int line = 1;
        int num = 0;
        while (true) {
            num += Math.pow(2, line-1);
            if (label <= num) {
                break;
            }
            line++;
        }

        // 按行构造二叉树
        List<LinkedList<Integer>> tree = new ArrayList<>();
        // 插入一个空list站位（非必须，只是便于计算）
        tree.add(new LinkedList<>());
        num = 0;
        int curNum = 1;
        for (int i=1; i<=line; i++) {
            LinkedList<Integer> list = new LinkedList<>();
            tree.add(list);
            num += Math.pow(2, i-1);
            if (i % 2 == 1) {
                // 从左往右
                while (curNum <= num) {
                    list.offerLast(curNum++);
                }
            } else {
                // 从右往左
                while (curNum <= num) {
                    list.offerFirst(curNum++);
                }
            }
        }

        // 从label开始向上找parent，直到root
        int indexLabel;
        int tmpLabel = label;
        while (line > 0) {
            LinkedList<Integer> list = tree.get(line);
            indexLabel = list.indexOf(tmpLabel);
            // 从头部插入
            result.offerFirst(tmpLabel);

            indexLabel /= 2;
            LinkedList<Integer> upFloorList = tree.get(line-1);
            if (upFloorList.size() > 0) {
                tmpLabel = upFloorList.get(indexLabel);
            }
            line--;
        }

        return result;
    }
}












