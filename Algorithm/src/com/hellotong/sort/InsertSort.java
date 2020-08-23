package com.hellotong.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 插入排序
 * 时间复杂度：O(n^2)
 * 插入排序（Insertion Sorting）的基本思想是：把 n 个待排序的元素看成为一个有序表和一个无序表，开始时有
 * 序表中只包含一个元素，无序表中包含有 n-1 个元素，排序过程中每次从无序表中取出第一个元素，把它的排
 * 序码依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，使之成为新的有序表。
 * 
 * @author hellotong
 * @date 2020-08-22 10:37
 */
public class InsertSort {
    public static void main(String[] args) {
        // int[] arr = {101, 34, 119, 1};
        // 生成 8w 个数据，测试插入排序所花费的时间
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        // 获取排序前的时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前时间：" + dateStr);

        insertSort(arr);

        Date date2 = new Date();
        String dateStr2 = simpleDateFormat.format(date2);
        System.out.println("排序后时间：" + dateStr2);

        // 花费不到 1s 左右
    }
    
    public static void insertSort(int[] arr) {
        // 第一个元素已经有序构成有序表，从第二个元素开始向有序表中插入并找到位置
        for (int i = 1; i < arr.length; i++) {
            // 获取待插入元素的值和索引值
            int insertVal = arr[i];
            // 初始值为待插入元素的前一个位置，开始向前进行比较
            int insertIndex = i - 1;
            
            // insertIndex >= 0 保证数组不越界
            // insertVal < arr[insertIndex] 待插入的数还没找到插入位置，继续向前进行比较 
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                // arr[insertIndex] 后移
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            
            // while 循环结束说明已经找到位置了，即为 insertIndex + 1
            // insertIndex + 1 != i 时，arr[insertIndex + 1] 就是 insertVal 不需要重新赋值
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }
}
