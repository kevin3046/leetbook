package com.leetbook.test.math;

import java.util.*;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/5 14:14
 * @Description:
 * 水壶问题
 * https://leetcode-cn.com/problems/water-and-jug-problem/
 * x:5,y=3,z=4
 * 1、x=0,y=0
 * 2、x+=5,y=0 => x=5,y=0
 * 3、x-=3,y+=3 => x=2,y=3
 * 4、x+=0,y-=3 => x=2,y=0
 * 5、x-=2,y+=2 => x=0,y=2
 * 6、x+=5,y+=0 => x=5,y=2
 * 7、x-=1,y+=1 => x=4,y=3
 */
public class CanMeasureWater {

    private boolean res = false;
    private int px;
    private int py;
    private Set<String> set = new HashSet<>();

    /**
     * dfs解法超时
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean canMeasureWater(int x, int y, int z) {
        this.px = x;
        this.py = y;

        dfs(0,0,z);
        return this.res;
    }

    private void dfs(int x,int y,int z){

        if(this.res){
            return;
        }

        if(x == z || y==z || x+y == z || Math.abs(x-y) == z ){
            this.res = true;
            return;
        }
        if(set.contains(x+"-"+y)){
            return;
        }
        set.add(x+"-"+y);
        // x灌满
        dfs(this.px,y,z);

        //y 灌满
        dfs(x,this.py,z);

        //x 清空
        dfs(0,y,z);

        //y 清空
        dfs(x,0,z);

        //x 灌到 y
        dfs(x-Math.min(x,this.py-y),y+Math.min(x,this.py-y),z);

        //y 灌到 x
        dfs(x+Math.min(y,this.px-x),y-Math.min(y,this.px-x),z);

    }

    /**
     * 使用栈来模拟递归过程
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean canMeasureWater2(int x, int y, int z) {
        this.px = x;
        this.py = y;

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0,0});
        while (!stack.isEmpty()){
            if(this.res){
                break;
            }
            int[] current = stack.pop();
            x = current[0];
            y = current[1];
            if(x == z || y==z || x+y == z || Math.abs(x-y) == z ){
                this.res = true;
                break;
            }
            if(set.contains(x+"-"+y)){
                continue;
            }
            set.add(x+"-"+y);
            stack.push(new int[]{this.px,y});
            stack.push(new int[]{x,this.py});
            stack.push(new int[]{0,y});
            stack.push(new int[]{x,0});
            stack.push(new int[]{x-Math.min(x,this.py-y),y+Math.min(x,this.py-y)});
            stack.push(new int[]{x+Math.min(y,this.px-x),y-Math.min(y,this.px-x)});
        }
        return this.res;
    }

}
