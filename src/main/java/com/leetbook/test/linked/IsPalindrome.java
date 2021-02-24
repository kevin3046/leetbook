package com.leetbook.test.linked;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/23 16:49
 * @Description:
 * @tag:快慢指针，反转链表 请判断一个链表是否为回文链表
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xaw0rm/
 * 解题思路：使用快慢指针，拆分链表为两部分，反转第二部分，再进行比对。
 */
public class IsPalindrome {

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode rightHead = reverseList(slow.next);
        slow.next = null;
        ListNode leftHead = head;


        while (leftHead != null && rightHead != null) {
            if (leftHead.val != rightHead.val) {
                return false;
            }
            leftHead = leftHead.next;
            rightHead = rightHead.next;
        }
        return true;
    }

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
