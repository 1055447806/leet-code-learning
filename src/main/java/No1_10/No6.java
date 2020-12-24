package No1_10;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * 示例 1:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * <p>
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No6 {
    static class Solution {
        public static String convert(String s, int numRows) {
            if (numRows == 1) return s;
            int len = 2 * numRows - 2, p = 0, another;
            char[] charArray = new char[s.length()];
            for (int row = 0; row < numRows; row++) {
                for (int i = row; i < s.length(); i += len) {
                    charArray[p++] = s.charAt(i);
                    if (row != 0 && row != numRows - 1 &&
                            (another = i + len - 2 * row) < s.length()) {
                        charArray[p++] = s.charAt(another);
                    }
                }
            }
            return String.valueOf(charArray);
        }
    }

    public static void main(String[] args) {
        No6.Solution.convert("A", 1);
    }
}
