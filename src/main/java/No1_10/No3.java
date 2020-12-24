package No1_10;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 示例 4:
 * <p>
 * 输入: s = ""
 * 输出: 0
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No3 {
    static class Solution {
        /*public static int lengthOfLongestSubstring(String s) {
            // i character, str[i] index
            int[] str = new int[128];
            int result = 0, left = 0, len = s.length();
            for (int i = 0; i < len; i++) {
                char c = s.charAt(i);
                if (str[c] == 0) {
                    str[c] = i == 0 ? -1 : i;
                } else {
                    int prev = str[c] == -1 ? 0 : str[c];
                    result = Math.max(result, i - left);
                    for (int j = left; j <= prev; j++) {
                        str[s.charAt(j)] = 0;
                    }
                    str[c] = i;
                    left = prev + 1;
                }
            }
            return Math.max(len - left, result);
        }*/

        public static int lengthOfLongestSubstring(String s) {
            int[] m = new int[128];
            int left = 0, len = 0;
            for (int right = 0; right < s.length(); right++) {
                char c = s.charAt(right);
                left = Math.max(m[c], left);
                len = Math.max(len, right - left + 1);
                m[c] = right + 1;
            }
            return len;
        }

        public static void main(String[] args) {
            System.out.println(lengthOfLongestSubstring("abcabcbb"));
        }
    }
}