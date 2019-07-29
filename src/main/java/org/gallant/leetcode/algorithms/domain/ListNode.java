package org.gallant.leetcode.algorithms.domain;

/**
 * @author kongyong
 * @date 2019/7/27
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    @Override
    public String toString() {
        return String.format("%s -> %s", val, next == null ? "nvl" : next.toString());
    }
}
