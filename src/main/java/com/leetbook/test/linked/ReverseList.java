package com.leetbook.test.linked;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/23 16:43
 * @Description:
 * 反转链表
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xavip3/
 */
public class ReverseList {

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }



}


