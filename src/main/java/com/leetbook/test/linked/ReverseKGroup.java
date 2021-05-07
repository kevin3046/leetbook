package com.leetbook.test.linked;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/5/6 17:02
 * @Description:
 * 链表中的节点每k个一组翻转
 * https://www.nowcoder.com/practice/b49c3dc907814e9bbfa8437c251b028e?tpId=117&tqId=37746&rp=1&ru=%2Factivity%2Foj&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 */
public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {

        if (head == null) {
            return head;
        }
        if (k == 1) {
            return head;
        }
        int count = getListCounts(head);
        int start = 0;
        ListNode result = head;
        while (start <= count - k) {
            result = reverse(result, start, k);
            start += k;
        }

        return result;
    }

    private int getListCounts(ListNode head) {
        ListNode current = head;
        int count = 0;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }

    private ListNode reverse(ListNode head, int start, int k) {

        ListNode pre = new ListNode(-1);
        ListNode current = head;
        pre.next = current;
        int times = start;
        //让链表的current前进
        while (current != null && times > 0) {
            current = current.next;
            times--;
        }
        //从current位置开始反转k个节点
        ListNode prev = null;
        times = k;
        while (current != null && times > 0) {
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
            times--;
        }
        //把链表后半段接上
        ListNode current1 = prev;
        while (current1.next != null) {
            current1 = current1.next;
        }
        current1.next = current;
        //把链表前半段接上
        ListNode current2 = pre.next;
        times = start - 1;
        if (times > 0) {
            while (current2 != null && times > 0) {
                current2 = current2.next;
                times--;

            }
            current2.next = prev;
        } else {
            pre.next = prev;
        }

        return pre.next;


    }
}
