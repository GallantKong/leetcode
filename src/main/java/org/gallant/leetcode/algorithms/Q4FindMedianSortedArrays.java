package org.gallant.leetcode.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kongyong
 * @date 2019/7/27
 *
 * 4. 寻找两个有序数组的中位数
 *
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q4FindMedianSortedArrays {

    private static final double TWO = 2.0;

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int allLen = nums1.length + nums2.length;
        boolean isEvenNum = false;
        if (allLen % TWO == 0) {
            isEvenNum = true;
        }
        // 如果是奇数，midIndex需要加+1，为了与偶数时的midIndex统一。例如：总长度3，中间下标是1，如果统一使用小于判断则需要将其+1
        // 直接向上取整
        int midIndex = (int) Math.ceil(allLen / TWO);
        // 时间复杂度为O(log m + n)
        // merge 每次遍历半数+1的值，复杂度约等于log(m+n)
        List<Integer> all = merge(nums1, nums2, midIndex);
        if (!isEvenNum) {
            return all.get(midIndex - 1);
        } else {
            return (all.get(midIndex - 1) + all.get(midIndex)) / 2.0;
        }
    }

    private static List<Integer> merge(int[] nums1, int[] nums2, int midIndex) {
        List<Integer> data = new ArrayList<>(nums1.length + nums2.length);
        // 只需要获取一半+1的有序数据即可，基数直接获取中间数，偶数需要获取中间两位的平均值，所以是一半+1
        // 因为midIndex是总长度的一般，右半部分需要增加补偿值，续接nums1下标的值，即j+nums1.length
        for (int i=0, j=0; i <= midIndex && j + nums1.length <= midIndex;) {
            if (i<nums1.length && j<nums2.length) {
                if (nums1[i] >= nums2[j]) {
                    data.add(nums2[j]);
                    j++;
                } else {
                    data.add(nums1[i]);
                    i++;
                }
            } else {
                // 不登场的数组，处理剩余数据
                if (j < nums2.length) {
                    data.add(nums2[j]);
                    j++;
                }
                if (i < nums1.length) {
                    data.add(nums1[i]);
                    i++;
                }
            }
        }
        return data;
    }
}
