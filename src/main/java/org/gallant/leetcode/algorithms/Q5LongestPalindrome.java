package org.gallant.leetcode.algorithms;



/**
 * @author kongyong
 * @date 2019/7/29
 *
 * 5. 最长回文子串
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q5LongestPalindrome {

    public static void main(String[] args) {
        String str = "baabad";
        System.out.println(longestPalindrome(str));
    }

    private static String longestPalindrome(String s) {
        String[] vals = s.split("");
        int longestPalindrome = 0;
        int longestPalindromeStartIndex = 0;
        // 向右一步步步进，i会再完成一轮遍历后复位至lastI+1，这样保证遍历所有的回文情况
        int lastI = 0;
        for (int i = lastI; i<vals.length; i++) {
            // 每次i的自增位移，lastJ需要重新重最右侧扫描（i的非自然的"i++"自增，即j循环中的自增，需要重新扫描尾部直至相遇）
            int lastJ = vals.length - 1;
            lastI = i;
            boolean first = true;
            for (int j = lastJ; j>0; j--) {
                // 剩余的所有字符长度加起来也没有当前已发现的最长回文长，所以直接返回结果
                // 终止两层循环，返回结果
                if (longestPalindrome > vals.length - lastI + 1) {
                    i = vals.length;
                    break;
                }
                // 当前节点是否相等
                boolean isSame = false;
                if (vals[j].equals(vals[i])) {
                    isSame = true;
                }
                // 当前节点是否最终节点
                boolean isLast = false;
                // 左右下标相遇，或者偶数的时候两者相遇是相差1的步进，说明遍历完成
                if (i == j || i - j == 1 || i - j == -1) {
                    isLast = true;
                }
                // 或者j移动到最后与i相遇，最后一对相等，证明当前最大回文长度是2，记录数据并继续扫描
                if (isSame) {
                    if (isLast) {
                        // i跟随j一起移动说明一直是相同的字符，直至相遇依然是相同的字符，说明当前是一个回文，记录当前回文长度与起始下标
                        int curLongestPalindrome = lastJ - lastI + 1;
                        // 如果发现更长的回文则更新
                        if (curLongestPalindrome > longestPalindrome) {
                            longestPalindrome = curLongestPalindrome;
                            longestPalindromeStartIndex = lastI;
                        }
                        // 出现回文点，i跟随j移动，遍历完成后，i复位至lastI，然后i自增，lastI更新，进入下一轮的扫描
                        if (i != lastI) {
                            i = lastI;
                        }
                        // i++自增,lastI更新，j复位至尾结点
                        break;
                    } else {
                        // 发现可能的回文点，左右指针同时移动
                        i++;
                    }
                    // 记录每一轮扫描中，上次i与j相等的下标。在发现不是回文时，j复位至改点，然后j--后继续扫描。
                    // 例如：abbafaxyz。i=0，j=5时出现相同点记录lastJ。当i，j位移一步发现当前不是回文，
                    // i复位至0，j如果也从尾部扫描会进入死循环。j需要跳过lastJ（lastJ向左位移一步）继续扫描。
                    if (first) {
                        lastJ = j;
                        first = false;
                    }
                }
                if (!isSame) {
                    first = true;
                    if (isLast) {
                        // 出现回文点，i跟随j移动，遍历完成后，i复位至lastI，然后i自增，lastI更新，进入下一轮的扫描
                        if (i != lastI) {
                            i = lastI;
                        }
                        break;
                    } else {
                        // 回文中断（即不是回文，只是左右两边相同）
                        // 出现回文点，i跟随j移动，遍历完成后，i复位至lastI，然后i自增，lastI更新，进入下一轮的扫描
                        if (i != lastI) {
                            i = lastI;
                        }
                        // j继续向左位移直到与i相遇位置
                    }
                }
            }
        }
        return s.substring(longestPalindromeStartIndex, longestPalindromeStartIndex + longestPalindrome);
    }
}
