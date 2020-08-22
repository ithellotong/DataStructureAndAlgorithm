package com.hellotong.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 选择排序
 * 时间复杂度：O(n^2)
 * 
 * @author hellotong
 * @date 2020-08-22 9:51
 */
public class SelectSort {
    public static void main(String[] args) {
        // int[] arr = {101, 34, 119, 1};
        // 生成 8w 个数据，测试冒泡排序所花费的时间
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        // 获取排序前的时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前时间：" + dateStr);

        selectSort(arr);

        Date date2 = new Date();
        String dateStr2 = simpleDateFormat.format(date2);
        System.out.println("排序后时间：" + dateStr2);

        // 花费 3s 左右
    }
    
    public static void selectSort(int[] arr) {
        // 最小值索引
        int minIndex;
        // 最小值
        int min;
        for (int i = 0; i < arr.length - 1; i++) {
            // 假定 arr[i] 为最小值
            minIndex = i;
            min = arr[minIndex];
            for (int j = i + 1; j < arr.length; j++) {
                // 如果 min 不是最小值，则重置 minIndex min
                if (min > arr[j]) {
                    minIndex = j;
                    min = arr[j];
                }
            }
            
            // 内层循环结束后，minIndex 就是真正的最小值索引，min 就是真正的最小值
            
            // 判断假定是否成立，不成立则交换 arr[i] 和 arr[minIndex]
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
