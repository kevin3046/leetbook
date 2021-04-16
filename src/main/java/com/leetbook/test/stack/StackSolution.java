package com.leetbook.test.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/20 10:06
 * @Description:
 */
public class StackSolution {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        MinStack obj = new MinStack();
        obj.push(-2);
        obj.push(0);
        obj.push(-3);
        System.out.println(obj.getMin());
        obj.pop();
        System.out.println(obj.top());
        System.out.println(obj.getMin());

        (new Calculate()).calculate("3/2 ");

        List<NestedInteger> nestedList = new ArrayList<>();

        NestedIntegerImpl item11 = new NestedIntegerImpl();
        item11.setValue(1);
        NestedIntegerImpl item12 = new NestedIntegerImpl();
        item12.setValue(1);
        NestedIntegerImpl item1 = new NestedIntegerImpl();
        item1.setList(Arrays.asList(item11,item12));
        nestedList.add(item1);

        NestedIntegerImpl item2 = new NestedIntegerImpl();
        item2.setValue(2);
        nestedList.add(item2);

        NestedIntegerImpl item3 = new NestedIntegerImpl();
        item3.setList(Arrays.asList(item11,item12));
        nestedList.add(item3);

        NestedIterator i = new NestedIterator(nestedList);

        List<Integer> res = new ArrayList<>();
        while (i.hasNext()){
            res.add(i.next());
        }

        System.out.println(res);

    }
}