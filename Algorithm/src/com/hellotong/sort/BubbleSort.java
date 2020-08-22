package com.hellotong.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 冒泡排序
 * 时间复杂度为 O(n^2)
 * 
 * @author hellotong
 * @date 2020-08-20 21:56
 */
public class BubbleSort {
    public static void main(String[] args) {
        // int[] arr = {3, 9, -1, 10, 11};
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
        
        bubbleSort(arr);

        Date date2 = new Date();
        String dateStr2 = simpleDateFormat.format(date2);
        System.out.println("排序前时间：" + dateStr2); 
        
        // 花费 15s 左右
    }
    
    public static void bubbleSort(int[] arr) {
        // System.out.println("排序前的数组为" + Arrays.toString(arr));
        int temp = 0;
        // 标记一趟排序中元素是否进行过交换
        boolean flag = false;
        // 5 个数最多需要 4 个排序
        // 一趟排序确定一个数（最大值、次小值...）
        // 时间复杂度为 O(n^2) 两层 for 循环
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            // 说明在一趟排序中，一次交换都没有发生，表明序列已经有序，break 结束循环即可
            if (flag == false) {
                break;
            }
            // 重置 flag，用于下次判断
            flag = false;
        }
        // System.out.println("排序后的数组为；" + Arrays.toString(arr));
    }
}
