/**
 * 1893. 检查是否区域内所有整数都被覆盖
 * 给你一个二维整数数组 ranges 和两个整数 left 和 right 。每个 ranges[i] = [starti, endi] 表示一个从 starti 到 endi 的 闭区间 。
 *
 * 如果闭区间 [left, right] 内每个整数都被 ranges 中 至少一个 区间覆盖，那么请你返回 true ，否则返回 false 。
 *
 * 已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi ，那么我们称整数x 被覆盖了。
 *
 *
 *
 * 示例 1：
 *
 * 输入：ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
 * 输出：true
 * 解释：2 到 5 的每个整数都被覆盖了：
 * - 2 被第一个区间覆盖。
 * - 3 和 4 被第二个区间覆盖。
 * - 5 被第三个区间覆盖。
 * 示例 2：
 *
 * 输入：ranges = [[1,10],[10,20]], left = 21, right = 21
 * 输出：false
 * 解释：21 没有被任何一个区间覆盖。
 *
 *
 * 提示：
 *
 * 1 <= ranges.length <= 50
 * 1 <= starti <= endi <= 50
 * 1 <= left <= right <= 50
 */

public class IsCovered {
    public static void main(String[] args) {
        int[][] ranges = new int[][]{
                new int[]{1,2},
                new int[]{3,4},
                new int[]{5,6}
        };
        int left = 2;
        int right = 5;
        System.out.println("isCovered: " + isCovered(ranges, left, right));
    }

    static boolean isCovered(int[][] ranges, int left, int right) {
        // 从left到right遍历，检查每个数
        int cur = left;
        while (cur <= right) {
            // 检查是否包含cur
            boolean isCovered = false;
            for (int[] range: ranges) {
                if (range[0] <= cur && cur <= range[1]) {
                    // 若包含，cur直接偏移至range右边界+1
                    // （cur在range内，则从cur到range的右边界的数，都被包含在ranges中）
                    // 并继续遍历ranges，使得cur尽可能的往右偏
                    cur = range[1] + 1;
                    isCovered = true;
                }
            }
            // 若range不包含cur，直接返回false
            if (! isCovered) {
                return false;
            }
        }
        return true;
    }
}
