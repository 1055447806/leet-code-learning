package No1_10;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No5 {
    static class Solution {
        /**
         * 使用动态规划求解
         */
        public String longestPalindromeWithDynamicProgramming(String s) {
            int leftMax = 0, rightMax = 0;
            boolean[][] pointers = new boolean[s.length()][s.length()];
            for (int offset = 0; offset < s.length(); offset++) {
                for (int left = 0; left + offset < s.length(); left++) {
                    int right = left + offset;
                    pointers[left][right] = s.charAt(left) == s.charAt(right) && (offset < 2 || pointers[left + 1][right - 1]);
                    if (pointers[left][right]) {
                        leftMax = left;
                        rightMax = right;
                    }
                }
            }
            return s.substring(leftMax, rightMax + 1);
        }

        /**
         * 使用中心拓展求解
         */
        public String longestPalindromeWithExpandAroundCenter(String s) {
            int[] result = new int[]{0, 0};
            for (int i = 0; i < s.length(); i++) {
                expandAroundCenter(s, i, i, result);
                expandAroundCenter(s, i, i + 1, result);
            }
            return s.substring(result[0], result[1] + 1);
        }

        public void expandAroundCenter(String str, int left, int right, int[] result) {
            while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
                left--;
                right++;
            }
            if (--right - ++left + 1 > result[1] - result[0] + 1) {
                result[0] = left;
                result[1] = right;
            }
        }

        /**
         * Manacher 算法
         */
        public String longestPalindrome(String s) {
            return null;
        }

        public static void main(String[] args) {
            System.out.println(new Solution().longestPalindrome("babad"));
        }
    }
}
