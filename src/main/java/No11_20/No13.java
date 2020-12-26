package No11_20;

/**
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: "III"
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: "IV"
 * 输出: 4
 * 示例 3:
 * <p>
 * 输入: "IX"
 * 输出: 9
 * 示例 4:
 * <p>
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * 示例 5:
 * <p>
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *  
 * <p>
 * 提示：
 * <p>
 * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
 * IC 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
 * 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/roman-to-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class No13 {
    class Solution {
        /**
         * 按字母组合起始字符分类，可以分为三种类型：
         * 1. 千位 M开头：M MM MMM 分别为 1 至 3
         * 2. 百位 C 或 D 开头：C CC CCC CD D DC DCC DCCC CM 分别为 1 至 9
         * 3. 十位 X 或 L 开头：X XX XXX XL L LX LXX LXXX XC 分别为 1 至 9
         * 4. 个位 I 或 V 开头：I II III IV V VI VII VIII IX 分别为 1 至 9
         * 共七种情况，可以直接使用 if 进行流程控制
         *
         * @param s 输入字符串
         * @return 输出数值
         */
        public int romanToInt(String s) {
            char[] chars = s.toCharArray();
            int p = 0, sum = 0;
            while (p < chars.length) {
                if (chars[p] == 'M') {
                    // M
                    sum += 1000;
                    p++;
                } else if (chars[p] == 'C') {
                    // C CM CD
                    if (p + 1 < chars.length) {
                        if (chars[p + 1] == 'M') {
                            sum += 900;
                            p += 2;
                        } else if (chars[p + 1] == 'D') {
                            sum += 400;
                            p += 2;
                        } else {
                            sum += 100;
                            p++;
                        }
                    } else {
                        sum += 100;
                        p++;
                    }
                } else if (chars[p] == 'D') {
                    // D
                    sum += 500;
                    p++;
                } else if (chars[p] == 'X') {
                    // X XC XL
                    if (p + 1 < chars.length) {
                        if (chars[p + 1] == 'C') {
                            sum += 90;
                            p += 2;
                        } else if (chars[p + 1] == 'L') {
                            sum += 40;
                            p += 2;
                        } else {
                            sum += 10;
                            p++;
                        }
                    } else {
                        sum += 10;
                        p++;
                    }
                } else if (chars[p] == 'L') {
                    // L
                    sum += 50;
                    p++;
                } else if (chars[p] == 'I') {
                    // I IV IX
                    if (p + 1 < chars.length) {
                        if (chars[p + 1] == 'X') {
                            sum += 9;
                            p += 2;
                        } else if (chars[p + 1] == 'V') {
                            sum += 4;
                            p += 2;
                        } else {
                            sum += 1;
                            p++;
                        }
                    } else {
                        sum += 1;
                        p++;
                    }
                } else /*if (chars[p] == 'V') */ {
                    // V 因为上面 6 种情况已经不成立，则 V 必成立
                    sum += 5;
                    p++;
                }
            }
            return sum;
        }
    }
}
