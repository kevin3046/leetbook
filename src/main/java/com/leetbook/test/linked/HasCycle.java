package com.leetbook.test.linked;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/23 15:47
 * @Description:
 * @tag:哈希表,快慢指针 环形链表
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xaazns/
 */
public class HasCycle {

    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode p = head;
        while (p != null) {
            if (!set.add(p)) {
                return true;
            }
            p = p.next;
        }
        return false;
    }

    /**
     * @param head
     * @return
     * @tag:快慢指针
     * 参考题解：https://leetcode-cn.com/problems/linked-list-cycle/solution/cpp-shuang-zhi-zhen-kuai-man-zhi-zhen-fa-scxu/
     */
    public boolean hasCycle2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
