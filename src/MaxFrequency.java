import java.util.Arrays;

/**
 * 1838. 最高频元素的频数
 * 元素的 频数 是该元素在一个数组中出现的次数。
 *
 * 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
 *
 * 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,4], k = 5
 * 输出：3
 * 解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
 * 4 是数组中最高频元素，频数是 3 。
 * 示例 2：
 *
 * 输入：nums = [1,4,8,13], k = 5
 * 输出：2
 * 解释：存在多种最优解决方案：
 * - 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
 * - 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
 * - 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
 * 示例 3：
 *
 * 输入：nums = [3,9,6], k = 2
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * 1 <= k <= 105
 */
public class MaxFrequency {
    public static void main(String[] args) {
        int[] arr = new int[]{9922,9980,9990,9922,9932,9989,9929,9938,9941,9966,9985,9906,9964,9903,9995,9963,10000,9950,9939,9985,9944,9960,9989,9977,9901,9923,9997,9971,9909,9985,9979,9906,9955,9988,9996,9995,9901,9996,9924,9967,9991,9981,9914,9933,9946,9928,9975,9990,9968,9985,9963,9927,9946,9919,9931,9955,9979,9943,9905,9918,9962,9970,9939,9901,9940,9933,9917,9988,9935,9941,9947,9971,9901,9926,9908,9969,9978,9984,9952,9945,9958,9958,9930,9923,9950,9993,9938,9976,9942,9946,9990,9951,9971,9980,9966,9944,9976,9954,9970,9984,9939,9961,9996,9993,9935,9949,9975,9952,9998,9956,9957,9949,9902,9946,9979,9904,9925,9948,9952,9961,9948,9982,9922,9958,9956};
        int k = 1911;
        System.out.println("frequency: " + maxFrequency(arr, k));
    }

    // 排序 + 滑动窗口
    static int maxFrequency(int[] nums, int k) {
        int frequency = 1;

        // 先排序为递增数组
        Arrays.sort(nums);

        int length = nums.length;
        int left = 0;
        int total = 0;
        for (int right=1; right<length; right++) {
            // 每多一个频次的数，所有从下标left开始到right的数都得增加，因此需要乘(right-left)
            total += (nums[right] - nums[right-1]) * (right-left);
            while (total > k) {
                // 去掉最大步长的所有数，并将left向右偏移
                total -= nums[right] - nums[left];
                left++;
            }

            // 算出下标最大距离
            frequency = Math.max(frequency, right-left+1);
        }

        return frequency;
    }

    // 自己实现的方案，但会超时
    static int maxFrequency2(int[] nums, int k) {
        int frequency = 1;

        Arrays.sort(nums);

        int length = nums.length;
        for (int i=1; i<length; i++) {
            int tmp = k;
            int tmpFrequency = 1;
            for (int j=i; j<length; j++) {
                long d = nums[j] - nums[j-1];

                tmp -= d * (j-i+1);
                if (tmp >= 0) {
                    tmpFrequency++;
                } else {
                    break;
                }
            }
            frequency = Math.max(frequency, tmpFrequency);
        }

        return frequency;
    }
}

