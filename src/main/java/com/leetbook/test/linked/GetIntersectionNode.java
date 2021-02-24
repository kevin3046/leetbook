package com.leetbook.test.linked;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/23 16:41
 * @Description:
 * 相交链表
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xan8ah/
 * 参考题解：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/tu-jie-xiang-jiao-lian-biao-by-user7208t/
 */
public class GetIntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null){
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

}
