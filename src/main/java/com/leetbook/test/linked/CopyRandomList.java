package com.leetbook.test.linked;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/23 14:35
 * @Description:
 * @tag:哈希表
 * 复制带随机指针的链表
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xam1wr/
 */
public class CopyRandomList {


    public Node copyRandomList(Node head) {
        Node res = new Node(-1);
        Node current = head;
        Node prev = new Node(-1);
        res.next = prev;
        Map<Node, Integer> map = new HashMap<>();
        Map<Integer, Node> map1 = new HashMap<>();

        int i = 0;
        while (current != null) {
            prev.next = new Node(current.val);

            map.put(current, i);
            map1.put(i, prev.next);

            current = current.next;
            prev = prev.next;
            i++;
        }
        current = head;
        Node current1 = res.next.next;

        while (current != null) {
            if (current.random != null) {
                int num = map.get(current.random);
                current1.random = map1.get(num);
            }
            current = current.next;
            current1 = current1.next;
        }

        return res.next.next;
    }

    /**
     * 参考题解：https://leetcode-cn.com/problems/copy-list-with-random-pointer/solution/javatu-jie-cong-hashmapdao-chang-shu-kong-jian-by-/
     * @param head
     * @return
     */
    public Node copyRandomList2(Node head) {

        if (head == null){
            return null;
        }
        HashMap<Node, Node> map = new HashMap<>();
        Node p = head;
        while (p != null) {
            map.put(p, new Node(p.val));
            p = p.next;
        }
        p = head;
        while (p != null) {
            map.get(p).next = map.get(p.next);
            map.get(p).random = map.get(p.random);
            p = p.next;
        }
        return map.get(head);

    }
}