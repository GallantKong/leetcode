package org.gallant.leetcode.algorithms;

/**
 * @author kongyong
 * @date 2019/7/27
 *
 * 无重复字符的最长子串
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q3LengthOfLongestSubstring {

    public static void main(String[] args) {
        String s = "11aac2";
        System.out.println(lengthOfLongestSubstring(s));
    }

    private static int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
//        不为空的字符串最小为1
        int longest = 1;
        String[] strArray = s.split("");
//        从第一位开始查找不相等的字串长度
        for (int i = 0; i < strArray.length; i++) {
//            1. 已经计算出最长字串长度
//            2. 出现了相等元素，从第二位开始查找是否存在更长的长度
//            记录当前子串，用于判断是否存在重复字符
            StringBuilder substring = new StringBuilder(strArray[i]);
//            当前字串中的最长不重复子串长度
            int currentLongest = 1;
//            从i位之后开始计算最长不重复子串长度
            for (int j = i+1; j < strArray.length; j++) {
//                出现了重复，i向前位移，计算下一个子串的最长不重复长度
                if (substring.indexOf(strArray[j]) > -1) {
                    break;
                } else {
//                    追加当前字串，后面用来判断是否出现重复
                    substring.append(strArray[j]);
//                    递增当前最长不重复子串长度
                    currentLongest++;
//                    出现更长的最长不重复子串长度，更新结果
                    if (currentLongest > longest) {
                        longest = currentLongest;
                    }
                }
            }
//            剩余的字串全部长度也不能超过当前的最大值，直接终止并返回结果即可
            if (strArray.length - i <= longest) {
                break;
            }
        }
        return longest;
    }
}
