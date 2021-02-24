package com.leetbook.test.tree;

import java.util.PriorityQueue;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/24 14:01
 * @Description:
 * 二叉搜索树中第K小的元素
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xazo8d/
 * 解法一：二叉树中序遍历，左子树---> 根结点 ---> 右子树 递增
 */
public class KthSmallest {

    PriorityQueue<Integer> maxheap = new PriorityQueue<>((x, y) -> y - x);
    private int k;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return maxheap.peek();
    }

    public void dfs(TreeNode root){
        if(root == null){
            return;
        }
        dfs(root.left);
        maxheap.add(root.val);
        if(maxheap.size()>k){
            maxheap.poll();
        }
        dfs(root.right);
    }
}
