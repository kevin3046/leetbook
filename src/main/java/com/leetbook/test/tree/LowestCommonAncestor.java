package com.leetbook.test.tree;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/24 14:17
 * @Description:
 * 二叉树的最近公共祖先
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xas5th/
 * 参考题解：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/236-er-cha-shu-de-zui-jin-gong-gong-zu-xian-hou-xu/
 */
public class LowestCommonAncestor {


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left == null) {
            return right;
        }

        if(right == null) {
            return left;
        }
        return root;
    }
}
