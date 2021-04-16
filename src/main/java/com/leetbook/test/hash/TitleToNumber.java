package com.leetbook.test.hash;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/2/24 09:58
 * @Description:
 * Excel表列序号
 * https://leetcode-cn.com/leetbook/read/top-interview-questions/xa6dkt/
 */
public class TitleToNumber {

    public int titleToNumber(String s) {
//        Map<Character,Integer> map = new HashMap<>();
//        int j=1;
//        for(int i=65;i<91;i++){
//            map.put((char)i,j);
//            j++;
//        }
//        Map<Character,Integer> map = new HashMap<>();
//        map.put('A',1);map.put('B',2);map.put('C',3);map.put('D',4);map.put('E',5);
//        map.put('F',6);map.put('G',7);map.put('H',8);map.put('I',9);map.put('J',10);
//        map.put('K',11);map.put('L',12);map.put('M',13);map.put('N',14);map.put('O',15);
//        map.put('P',16);map.put('Q',17);map.put('R',18);map.put('S',19);map.put('T',20);
//        map.put('U',21);map.put('V',22);map.put('W',23);map.put('X',24);map.put('Y',25);
//        map.put('Z',26);

        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            int a = s.charAt(i) - 'A' + 1;
            //res = res * 26 + map.get(s.charAt(i));
            res = res * 26 + a;
        }
        return res;
    }
}
