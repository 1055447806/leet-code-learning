package No1_10;

/**
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 * <p>
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 * <p>
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 * <p>
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 * <p>
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *  
 * <p>
 * 提示：
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No4 {
    static class Solution {
        public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
            // bigger 较长的数组，smaller 较短的数组
            int[] bigger = nums1.length > nums2.length ? nums1 : nums2, smaller = bigger == nums1 ? nums2 : nums1;
            // p 用于二分查找的指针，half 被获取的左边的集合的个数。
            int pLeft = 0, pRight = smaller.length,
                    half = smaller.length + (bigger.length - smaller.length + 1) / 2;
            // pLeft 和 pRight 最终会相等，
            while (pLeft < pRight) {
                // 小数组取 pMid 左边的元素
                int pMid = (pLeft + pRight + 1) / 2;
                if (smaller[pMid - 1] > bigger[half - pMid]) {
                    pRight = pMid - 1;
                } else {
                    pLeft = pMid;
                }
            }
            double smallerLeftMax = pLeft == 0 ? Integer.MIN_VALUE : smaller[pLeft - 1];
            double smallerRightMin = pLeft == smaller.length ? Integer.MAX_VALUE : smaller[pLeft];
            double biggerLeftMax = half - pLeft == 0 ? Integer.MIN_VALUE : bigger[half - pLeft - 1];
            double biggerRightMin = half - pLeft == bigger.length ? Integer.MAX_VALUE : bigger[half - pLeft];

            return (bigger.length - smaller.length) % 2 == 1 ? Math.max(smallerLeftMax, biggerLeftMax) :
                    (Math.max(smallerLeftMax, biggerLeftMax) + Math.min(smallerRightMin, biggerRightMin)) / 2;
        }

        public static void main(String[] args) {
            int[] nums1 = {1, 2};
            int[] nums2 = {3, 4};
            System.out.println(findMedianSortedArrays(nums1, nums2));
        }
    }
}
