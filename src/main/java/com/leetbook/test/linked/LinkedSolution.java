package com.leetbook.test.linked;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/23 15:03
 * @Description:
 */
public class LinkedSolution {

    public static void main(String[] args) {
        //[[7,null],[13,0],[11,4],[10,2],[1,0]]
        Node node0 = new Node(7);

        Node node1 = new Node(13);

        Node node2 = new Node(11);

        Node node3 = new Node(10);

        Node node4 = new Node(1);

        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        node1.random = node0;

        node2.random = node4;

        node3.random = node2;

        node4.random = node0;

        Node res = (new CopyRandomList()).copyRandomList2(node0);


        //4,2,1,3

        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        ListNode res2 = (new SortList()).sortList(head);

        //[1,2]
        ListNode head1 = new ListNode(4);
        head1.next = new ListNode(3);
        head1.next.next = new ListNode(2);
        head1.next.next.next = new ListNode(1);
        head1.next.next.next.next = new ListNode(5);
        //(new IsPalindrome()).isPalindrome(head1);

        //[1,2,3,4,5]
        ListNode head2 = new ListNode(3);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(1);
        head2.next.next.next = new ListNode(5);
        //head2.next.next.next = new ListNode(4);
        //head2.next.next.next.next = new ListNode(5);

        //(new ReverseKGroup()).reverseKGroup(head2,2);
        //(new OddEvenList()).oddEvenList(head2);

        //3,2,0,-4
        ListNode head3 = new ListNode(3);
        head3.next = new ListNode(2);
        head3.next.next = new ListNode(0);
        head3.next.next.next = new ListNode(4);
        head3.next.next.next.next = head3.next;
        (new DetectCycle()).detectCycle(head3);

    }

}