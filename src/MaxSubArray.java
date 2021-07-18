/**
 * 剑指 Offer 42. 连续子数组的最大和
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 *
 * 要求时间复杂度为O(n)。
 *
 * 示例1:
 *
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 */

public class MaxSubArray {
    public static void main(String[] args) {
        int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(arr));
    }

    static int maxSubArray(int[] nums) {
        int pre = 0;
        int maxSum = nums[0];

        for (int n: nums) {
            // f(n) 为到n为止和最大的数
            // f(n) 与 f(n-1) + n对比
            pre = Math.max(pre + n, n);
            // pre为当次最大
            maxSum = Math.max(pre, maxSum);
        }

        return maxSum;
    }
}