package com.leetbook.test.math;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/5 14:14
 * @Description:
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

    public boolean canMeasureWater(int x, int y, int z) {
        if(x<z && y<z) {
            return false;
        }
        return true;
    }
}
