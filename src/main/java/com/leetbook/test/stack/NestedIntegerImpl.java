package com.leetbook.test.stack;

import java.util.List;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/23 11:12
 * @Description:
 */
public class NestedIntegerImpl implements NestedInteger {


    private Integer value;

    private List<NestedInteger> list;

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setList(List<NestedInteger> list) {
        this.list = list;
    }

    @Override
    public boolean isInteger() {
        return value != null;
    }

    @Override
    public Integer getInteger() {
        return value;
    }

    @Override
    public List<NestedInteger> getList() {
        return this.list;
    }
}
