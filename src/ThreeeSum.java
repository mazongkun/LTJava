import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class ThreeeSum {
    public static void main(String[] args) {
        int[] arr = new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};
        long start = System.currentTimeMillis();
        List<List<Integer>> list = threeSum2(arr);
        long time = System.currentTimeMillis()-start;

        for (List<Integer> l: list) {
            System.out.println("------------");
            for (int i: l) {
                System.out.print(" " + i);
            }
            System.out.println();
        }
        System.out.println("time: " + time + "ms");
    }

    static List<List<Integer>> threeSum2(int[] nums) {
        int length = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        // 排序
        Arrays.sort(nums);
        for (int i=0; i<length-2; i++) {
            // 第一位数去重
            if (i>0 && nums[i] == nums[i-1]) {
                continue;
            }
            // 锁定第一位数，问题转化为两数之和
            int left = i+1;
            int right = length-1;
            int first = nums[i];
            while (left < right) {
                // 第二位数去重
                if (left > i+1 && nums[left] == nums[left-1]) {
                    left++;
                    continue;
                }
                // 第三位数去重
                if (right < length-1 && nums[right] == nums[right+1]) {
                    right--;
                    continue;
                }

                int sum = first + nums[left] + nums[right];
                if (sum < 0) {
                    // sum < 0，则left右移
                    left++;
                } else if (sum > 0) {
                    // sum < 0，则right左移
                    right--;
                } else {
                    // sum == 0, add to list
                    List<Integer> list = new ArrayList<>();
                    list.add(first);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    left++;
                    right--;
                }
            }
        }
        return result;
    }

    static List<List<Integer>> threeSum(int[] nums) {
        int length = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        if (length < 3) {
            return result;
        }

        Arrays.sort(nums);
        for (int first = 0; first < length; first++) {
            if (first > 0 && nums[first] == nums[first-1]) {
                continue;
            }

            for (int second = first+1; second < length; second++) {
                if (second > first+1 && nums[second] == nums[second-1]) {
                    continue;
                }

                int target = -nums[first];
                int third = length - 1;
                // 此处判断需要">"，不能使用"!="
                while (third > second && nums[second] + nums[third] > target) {
                    third--;
                }
                if (third == second) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    result.add(list);
                }
            }
        }
        return result;
    }
}




