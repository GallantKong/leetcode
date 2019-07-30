package org.gallant.leetcode.algorithms;

import java.util.Set;

/**
 * @author kongyong
 * @date 2019/7/30
 *
 * 6. Z 字形变换
 *
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 * 示例 1:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 *
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q6Convert2Z {

    public static void main(String[] args) {
        String s="LEETCODEISHIRING";
        int numRows = 3;
        System.out.println(convert(s, numRows));
    }

    private static String convert(String s, int numRows) {
        int startIndex = 0;
        int endIndex = 2 * (numRows - 1);
        return "";
    }

    private static String convert(String s, int numRows, int startIndex, int endIndex, Set<String> result) {
        result.add(s.substring(startIndex, startIndex+1));
        result.add(s.substring(endIndex, endIndex+1));
        return "";
    }
}
