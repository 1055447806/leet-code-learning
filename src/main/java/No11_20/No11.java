package No11_20;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 示例 2：
 * <p>
 * 输入：height = [1,1]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：height = [4,3,2,1,4]
 * 输出：16
 * 示例 4：
 * <p>
 * 输入：height = [1,2,1]
 * 输出：2
 *  
 * <p>
 * 提示：
 * <p>
 * n = height.length
 * 2 <= n <= 3 * 104
 * 0 <= height[i] <= 3 * 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No11 {
    static class Solution {
        /*
        // 循环
        public static int maxArea(int[] height) {
            int area = 0;
            int pLeft = 0, pRight = height.length - 1;
            while (pLeft < pRight) {
                area = Math.max(Math.min(height[pLeft], height[pRight]) * (pRight - pLeft), area);
                if (height[pLeft] < height[pRight]) {
                    pLeft++;
                } else {
                    pRight--;
                }
            }
            return area;
        }
        */

        // 递归
        public int maxArea(int[] height) {
            return maxAreaWork(height, 0, height.length - 1);
        }

        public int maxAreaWork(int[] height, int pLeft, int pRight) {
            if (pRight - pLeft == 1) return Math.min(height[pLeft], height[pRight]);
            int area = (pRight - pLeft) * Math.min(height[pLeft], height[pRight]);
            if (height[pLeft] < height[pRight]) {
                pLeft++;
            } else {
                pRight--;
            }
            return Math.max(area, maxAreaWork(height, pLeft, pRight));
        }
    }
}
