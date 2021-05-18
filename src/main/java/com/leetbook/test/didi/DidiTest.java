package com.leetbook.test.didi;

import com.leetbook.test.tree.TreeNode;

import java.util.Stack;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/5/14 20:40
 * @Description: 二叉树先序遍历,递归和非递归版本
 */
public class DidiTest {


    public static void test01(TreeNode root){
        if(root == null){
            return;
        }
        System.out.println(root.val);
        test01(root.left);
        test01(root.right);
    }

    public static void test02(TreeNode root){
        if(root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            System.out.println(node.val);
            if(node.right!=null) {
                stack.add(node.right);
            }
            if(node.left!=null) {
                stack.add(node.left);
            }
        }
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        DidiTest.test01(root);
        System.out.println("=================================");
        DidiTest.test02(root);
    }
}
