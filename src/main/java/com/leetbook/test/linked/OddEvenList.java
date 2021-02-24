package com.leetbook.test.linked;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/23 17:20
 * @Description: 奇偶链表
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xa0a97/
 */
public class OddEvenList {

    public ListNode oddEvenList(ListNode head) {
        ListNode prehead = new ListNode(-1);
        ListNode leftHead = prehead;
        ListNode prehead2 = new ListNode(-1);

        ListNode rigtHead = prehead2;

        ListNode current = head;
        int i = 1;
        while (current != null) {
            if (i % 2 != 0) {
                leftHead.next = current;
                leftHead = leftHead.next;
            } else {
                rigtHead.next = current;
                rigtHead = rigtHead.next;
            }
            current = current.next;
            i++;
        }
        rigtHead.next = null;
        leftHead.next = prehead2.next;
        return prehead.next;
    }
}
