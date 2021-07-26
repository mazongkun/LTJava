import java.util.*;

/**
 * 1743. 从相邻元素对还原数组
 * 存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。
 *
 * 给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums 中相邻。
 *
 * 题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i], nums[i+1]] ，也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。
 *
 * 返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。
 *
 *
 *
 * 示例 1：
 *
 * 输入：adjacentPairs = [[2,1],[3,4],[3,2]]
 * 输出：[1,2,3,4]
 * 解释：数组的所有相邻元素对都在 adjacentPairs 中。
 * 特别要注意的是，adjacentPairs[i] 只表示两个元素相邻，并不保证其 左-右 顺序。
 * 示例 2：
 *
 * 输入：adjacentPairs = [[4,-2],[1,4],[-3,1]]
 * 输出：[-2,4,1,-3]
 * 解释：数组中可能存在负数。
 * 另一种解答是 [-3,1,4,-2] ，也会被视作正确答案。
 * 示例 3：
 *
 * 输入：adjacentPairs = [[100000,-100000]]
 * 输出：[100000,-100000]
 *
 *
 * 提示：
 *
 * nums.length == n
 * adjacentPairs.length == n - 1
 * adjacentPairs[i].length == 2
 * 2 <= n <= 105
 * -105 <= nums[i], ui, vi <= 105
 * 题目数据保证存在一些以 adjacentPairs 作为元素对的数组 nums
 */
public class RestoreArray {
    public static void main(String[] args) {
        int[][] adjacentArr = new int[][]{
                {2,1},
                {3,4},
                {3,2}
        };
        int[] result = restoreArray2(adjacentArr);
        for (int n: result) {
            System.out.println(n);
        }
    }

    static int[] restoreArray2(int[][] adjacentPairs) {
        // 遍历每个数key，将与key相邻的数放进map
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int[] pair: adjacentPairs) {
            map.putIfAbsent(pair[0], new ArrayList<>());
            map.get(pair[0]).add(pair[1]);
            map.putIfAbsent(pair[1], new ArrayList<>());
            map.get(pair[1]).add(pair[0]);
        }

        // 找到头部
        int[] arr = new int[adjacentPairs.length+1];
        Set<Map.Entry<Integer, List<Integer>>> set = map.entrySet();
        for (Map.Entry<Integer, List<Integer>> entry: set) {
            if (entry.getValue().size() == 1) {
                arr[0] = entry.getKey();
                break;
            }
        }

        // 从头部开始，根据map中保存的相邻的数，完成整个数组的推理
        int index = 1;
        while (index < arr.length) {
            int lastNum = arr[index-1];
            int preNum = index > 1 ? arr[index-2] : lastNum;

            List<Integer> neighbors = map.get(lastNum);
            for (int neighbor: neighbors) {
                if (neighbor == preNum) {
                    continue;
                }
                arr[index] = neighbor;
            }
            index++;
        }

        return arr;
    }

    static int[] restoreArray(int[][] adjacentPairs) {
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (int i=0; i<adjacentPairs.length; i++) {
            for (int key: adjacentPairs[i]) {
                // 遍历每个数key，将每个数相邻的下标存入map
                if (map.containsKey(key)) {
                    map.get(key).add(i);
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    map.put(key, list);
                }
            }
        }

        // 找到头部
        int head = 0;
        Set<Map.Entry<Integer, List<Integer>>> set = map.entrySet();
        for (Map.Entry<Integer, List<Integer>> entry: set) {
            if (entry.getValue().size() == 1) {
                head = entry.getKey();
            }
        }

        // 通过头部以及在map中的下标，依次将数组赋值
        int[] result = new int[adjacentPairs.length+1];
        result[0] = head;
        int index = 1;

        while (index < result.length) {
            int curNum = 0;
            int lastNum = result[index-1];
            int preNum = (index > 1) ? result[index-2] : lastNum;
            for (int adjacentIndex: map.get(lastNum)) {
                int[] adjacentArr = adjacentPairs[adjacentIndex];
                if ((lastNum == adjacentArr[0] && preNum != adjacentArr[1])
                        || (preNum == adjacentArr[0] && lastNum != adjacentArr[1])) {
                    curNum = adjacentArr[1];
                    break;
                } else if ((lastNum == adjacentArr[1] && preNum != adjacentArr[0])
                        || (preNum == adjacentArr[1] && lastNum != adjacentArr[0])) {
                    curNum = adjacentArr[0];
                    break;
                }
            }
            result[index] = curNum;
            index ++;
        }

        return result;
    }
}
