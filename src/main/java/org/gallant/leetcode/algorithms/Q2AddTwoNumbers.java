package org.gallant.leetcode.algorithms;

import org.gallant.leetcode.algorithms.domain.ListNode;

/**
 * @author kongyong
 * @date 2019/7/27
 *
 * 2. 两数相加
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Q2AddTwoNumbers {

    public static void main(String[] args) {
        ListNode listNode = add(null, 2);
        add(listNode, 4);
        add(listNode, 3);
        ListNode listNode2 = add(null, 5);
        add(listNode2, 6);
        add(listNode2, 4);
        System.out.println(listNode+"\n"+listNode2);
        System.out.println(addTwoNumbers(listNode, listNode2));
    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode vL1 = l1;
        ListNode vL2 = l2;
        int compensation = 0;
        while (vL1 != null || vL2 != null) {
            int val = (vL1 == null ? 0 : vL1.val) + (vL2 == null ? 0 :vL2.val);
            // 大于10要进位的补偿值
            if (compensation > 0) {
                val += compensation;
                compensation = 0;
            }
            // 小于10直接追加至尾部
            if (val <= 9) {
                result = add(result, val);
            } else {
//                大于9,十位数进位，暂存至补偿值中。个位数直接追加至链表尾部
                String valStr = ""+ val;
                result = add(result, Integer.parseInt(valStr.substring(1, 2)));
                compensation = Integer.parseInt(valStr.substring(0, 1));
            }
            if (vL1 != null) {
                vL1 = vL1.next;
            }
            if (vL2 != null) {
                vL2 = vL2.next;
            }
        }
        return result;
    }
    /**
     * 向链表尾部追加链表节点
     * @param parent : 
     * @param val :
     * @return org.gallant.leetcode.algorithms.domain.ListNode :
     */
    private static ListNode add(ListNode parent, int val) {
        if (parent == null) {
            parent = new ListNode(val);
        } else {
            parent.next = add(parent.next, val);
        }
        return  parent;
    }
}
