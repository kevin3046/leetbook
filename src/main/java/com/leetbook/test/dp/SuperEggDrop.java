package com.leetbook.test.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/5 10:24
 * @Description:
 * 鸡蛋掉落
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xmup75/
 * 参考题解1：https://leetcode-cn.com/problems/super-egg-drop/solution/ji-ben-dong-tai-gui-hua-jie-fa-by-labuladong/
 * 参考题解2，李永乐老师：https://www.bilibili.com/video/BV1KE41137PK
 * 参考题解2：https://leetcode-cn.com/problems/super-egg-drop/solution/887-ji-dan-diao-luo-by-dyzahng-hc9k/
 */
public class SuperEggDrop {

    private Map<String,Integer> dict = new HashMap<>();

    public int superEggDrop(int K, int N) {
        return dp(K, N,1);
    }

    private int dp(int K,int N,int level){
        if(K == 1){
            return N;
        }
        if(N == 0){
            return 0;
        }
        if(dict.containsKey(K+"-"+N)){
            return dict.get(K+"-"+N);
        }
        int res = Integer.MAX_VALUE;
        for(int i=1;i<=N;i++){
            int dp_down = dp(K-1,i-1,level+1);//碎
            int dp_up = dp(K,N-i,level+1);//没碎
            res = Math.min(res,Math.max(dp_down,dp_up)+1);
            if(level == 1){
                System.out.println("i="+i+"-dp_down:"+dp_down+"-dp_up:"+dp_up+"-res:"+res);
            }
        }
        dict.put(K+"-"+N,res);
        return res;
    }
}
