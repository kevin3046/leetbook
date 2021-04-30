package com.leetbook.test.array;

import java.util.Arrays;

/**
 * @Auther: kevin3046@163.com
 * @Date: 2021/4/30 14:40
 * @Description:
 * @tag:快速排序
 * 参考文章：https://wiki.jikexueyuan.com/project/easy-learn-algorithm/fast-sort.html
 */
public class QuickSort {


    public void mySort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序（Quicksort）是对冒泡排序的一种改进，借用了分治的思想，由C. A. R. Hoare在1962年提出。
     * 它的基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，
     * 其中一部分的所有数据都比另外一部分的所有数据都要小，
     * 然后再按此方法对这两部分数据分别进行快速排序，
     * 整个排序过程可以递归进行，以此达到整个数据变成有序序列。
     *
     * @param arr
     * @param low
     * @param high
     */
    public void quickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length <= 0) {
            return;
        }
        if (low >= high) {
            return;
        }

        int left = low;
        int right = high;
        int temp = arr[left]; //挖坑1：保存基准的值

        while (left < right) {
            //先从右往左找一个小于 temp 的数
            while (left < right && arr[right] >= temp) {
                right--;
            }
            arr[left] = arr[right]; //坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
            //再从左往右找一个大于 temp 的数
            while (left < right && arr[left] <= temp) {
                left++;
            }
            arr[right] = arr[left]; //坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
        }
        arr[left] = temp; //基准值填补到坑3中，准备分治递归快排
        System.out.println("Sorting: " + Arrays.toString(arr));
        quickSort(arr, low, left - 1);
        quickSort(arr, left + 1, high);
    }


}
