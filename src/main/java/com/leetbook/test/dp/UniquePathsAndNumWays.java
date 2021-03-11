package com.leetbook.test.dp;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/3/11 14:21
 * @Description:
 * 不同路径，向右走1，向下走1，动态转移方程为：f[i][j] = f[i-1][j] + f[i][j-1]
 * 不同路径的变种，向右走1到2步，向下走1到3步
 * 根据上一个方程的解法推导出其变种，动态转移方程为：f[i][j] = f[i-1][j] + f[i-2][j] + f[i][j-1] + f[i][j-2]+ f[i][j-3];
 */
public class UniquePathsAndNumWays {


    public int uniquePathsAndNumWays(int m,int n){

        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for(int i=1;i<m;i++){
            int temp = dp[i-1][0];
            if(i-2>=0){
                temp += dp[i-2][0];
            }
            dp[i][0] = temp;
        }

        for(int j=1;j<n;j++){
            int temp = dp[0][j-1];

            if(j-2>=0){
                temp+= dp[0][j-2];
            }
            if(j-3>=0){
                temp+= dp[0][j-3];
            }
            dp[0][j] = temp;
        }

        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                int temp = dp[i-1][j] + dp[i][j-1];

                if(i-2>=0){
                    temp += dp[i-2][j];
                }

                if(j-2>=0){
                    temp+= dp[i][j-2];
                }
                if(j-3>=0){
                    temp+= dp[i][j-3];
                }
                dp[i][j] = temp;
            }
        }
        return dp[m-1][n-1];
    }
}
