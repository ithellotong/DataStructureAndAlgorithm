package com.hellotong.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 希尔排序
 * 时间复杂度：O(nlogn)
 * 希尔排序法基本思想：希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含
 * 的关键词越来越多，当增量减至 1 时，整个文件恰被分成一组，算法便终止.
 * 
 * @author hellotong
 * @date 2020-08-22 12:45
 */
public class ShellSort {
    public static void main(String[] args) {
        // int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        // 生成 8w 个数据，测试冒泡排序所花费的时间
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        // 获取排序前的时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前时间：" + dateStr);

        // 花费不到 50ms 左右
        // shellSort(arr);
        // 花费不到 65ms 左右
        shellSort2(arr);

        Date date2 = new Date();
        String dateStr2 = simpleDateFormat.format(date2);
        System.out.println("排序后时间：" + dateStr2);

        // 花费不到 1s 左右
    }

    /**
     * 希尔排序，对有序序列在插入时采用交换法
     * @param arr 待排序数组
     */
    public static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0;
        // 决定做几趟排序，确定分组的步长
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 外层循环找到每个分组的最后一个元素
            for (int i = gap; i < arr.length; i++) {
                // 内层循环对每个分组中的元素使用插入排序（每个分组的最后一个元素开始）
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    } else {
                    // arr[j] <= arr[j + gap] 退出循环
                        break;
                    }
                }
            }
            // System.out.println("第" + (++count) + "轮排序：" + Arrays.toString(arr));
        }
    }

    /**
     * 希尔排序，对有序序列在插入时采用移动法
     * @param arr 待排序数组
     */
    public static void shellSort2(int[] arr) {
        int count = 0;
        // 决定做几趟排序，确定分组的步长
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 外层循环找到每个分组的最后一个元素
            for (int i = gap; i < arr.length; i++) {
                // 待插入位置
                int insertIndex = i;
                // 待插入值
                int insertVal = arr[insertIndex];
                // 如果 arr[insertIndex] >= arr[insertIndex - gap] 就不移动，不进入 while
                if (arr[insertIndex] < arr[insertIndex - gap]) {
                    // 寻找待插入元素的位置
                    while (insertIndex - gap >= 0 && insertVal < arr[insertIndex - gap]) {
                        arr[insertIndex] = arr[insertIndex - gap];
                        insertIndex -= gap;
                    }
                    
                    arr[insertIndex] = insertVal;
                }
            }
            // System.out.println("第" + (++count) + "轮排序：" + Arrays.toString(arr));
        }
    }
}
