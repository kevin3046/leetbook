package com.leetbook.test.sort;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/26 11:22
 * @Description:
 */
public class BinaryIndexdTree {
    int[] tree;
    int length;

    BinaryIndexdTree(int size){
        length = size;
        tree = new int[length+1];
    }

    public void update(int index,int data){
        while (index<=length){
            tree[index] += data;
            index += lowbit(index);
        }
    }

    public int query(int t){
        int ans = 0;
        while (t>0){
            ans += tree[t];
            t -= lowbit(t);
        }
        return ans;
    }

    public int lowbit(int t){
        return t & -t;
    }
}
