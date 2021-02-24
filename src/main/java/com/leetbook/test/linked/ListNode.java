package com.leetbook.test.linked;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/23 15:47
 * @Description:
 */
public class ListNode {

    int val;
    ListNode next;

    ListNode() {}

    ListNode(int x) {
        val = x;
        next = null;
    }

    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
