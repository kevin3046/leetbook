package com.leetbook.test.stack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/23 11:05
 * @Description:扁平化嵌套列表迭代器
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xa3tsv/
 * 参考题解：https://leetcode-cn.com/problems/flatten-nested-list-iterator/solution/java-liang-chong-jie-fa-by-wwwhang/
 *
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
public class NestedIterator implements Iterator<Integer> {

    List<Integer> list = new ArrayList<>();
    ListIterator<Integer> it;

    public NestedIterator(List<NestedInteger> nestedList) {
        dfs(nestedList);
        it = list.listIterator();
    }

    @Override
    public Integer next() {
        return it.next();
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    private void dfs(List<NestedInteger> nestedList){
        for (NestedInteger nestedinteger: nestedList) {
            if (nestedinteger.isInteger()) {
                list.add(nestedinteger.getInteger());
            } else {
                dfs(nestedinteger.getList());
            }
        }
    }
}
