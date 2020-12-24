package No1_10;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 *  
 * 示例 1：
 * <p>
 * 输入：s = "aa" p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3：
 * <p>
 * 输入：s = "ab" p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4：
 * <p>
 * 输入：s = "aab" p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5：
 * <p>
 * 输入：s = "mississippi" p = "mis*is*p*."
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 20
 * 0 <= p.length <= 30
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No10 {

    /**
     * 使用动态规划法求解
     */
    static class Solution {
        public static boolean isMatch(String s, String p) {
            char[] charsS = s.toCharArray();
            char[] charsP = p.toCharArray();
            // 动态规划表
            boolean[][] dp = new boolean[charsP.length + 1][charsS.length + 1];
            dp[0][0] = true;
            // 正则对应的每一行的最小指针映射表
            int[][] minPointers = new int[p.length() + 1][2];
            for (int i = 1; i < minPointers.length; i++) {
                minPointers[i][0] = i;
                minPointers[i][1] = -1;
            }
            // 填充表 dp[1:][]
            for (int j = 1; j <= p.length(); j++) {
                char currS, currP = charsP[j - 1];
                if (j < p.length() && charsP[j] == '*') {
                    for (int i = getPrevMinPointer(minPointers, j); i <= charsS.length; i++) {
                        currS = i == 0 ? ' ' : charsS[i - 1];
                        boolean k = getUp(dp, minPointers, j, i) || currP == '.' || getLeft(dp, j, i) && currP == currS;
                        dp[j][i] = k;
                        if (minPointers[j][1] == -1 && k) {
                            minPointers[j][1] = i;
                        }
                    }
                    // '*' 则多走一行
                    j++;
                } else {
                    for (int i = getPrevMinPointer(minPointers, j) + 1; i <= charsS.length; i++) {
                        currS = charsS[i - 1];
                        boolean k = getLeftUp(dp, minPointers, j, i) && (currP == '.' || currP == currS);
                        dp[j][i] = k;
                        if (minPointers[j][1] == -1 && k) {
                            minPointers[j][1] = i;
                        }
                    }
                    if (minPointers[j][1] == -1) {
                        return false;
                    }
                }
            }
            return minPointers[charsP.length][1] == -1 ? dp[charsP.length - 1][charsS.length] : dp[charsP.length][charsS.length];
        }

        /**
         * 获取上一行的最小指针
         *
         * @param minPointers 最小指针表
         * @param curr        当前行数
         * @return 上一行的最小指针
         */
        public static int getPrevMinPointer(int[][] minPointers, int curr) {
            return minPointers[curr - 1][1] == -1 ? minPointers[curr - 2][1] : minPointers[curr - 1][1];
        }

        /**
         * 获取上节点
         *
         * @param dp          动态规划表
         * @param minPointers 最小指针表
         * @param j           当前正则行数
         * @param i           当前字符串列数
         * @return 上节点值
         */
        public static boolean getUp(boolean[][] dp, int[][] minPointers, int j, int i) {
            return minPointers[j - 1][1] == -1 ? dp[j - 2][i] : dp[j - 1][i];
        }

        /**
         * 获取左节点
         *
         * @param dp 动态规划表
         * @param j  当前正则行数
         * @param i  当前字符串列数
         * @return 左节点值
         */
        public static boolean getLeft(boolean[][] dp, int j, int i) {
            return dp[j][i - 1];
        }

        /**
         * 获取左上节点
         *
         * @param dp          动态规划表
         * @param minPointers 最小指针表
         * @param j           当前正则行数
         * @param i           当前字符串列数
         * @return 左上节点值
         */
        public static boolean getLeftUp(boolean[][] dp, int[][] minPointers, int j, int i) {
            return minPointers[j - 1][1] == -1 ? dp[j - 2][i - 1] : dp[j - 1][i - 1];
        }
    }
}
