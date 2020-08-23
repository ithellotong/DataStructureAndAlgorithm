package com.hellotong.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 归并排序
 * 时间复杂度：O(nlogn)
 * 归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，该算法采用经典的分治（divide-and-conquer）
 * 策略（分治法将问题分(divide)成一些小的问题然后递归求解，而治(conquer)的阶段则将分的阶段得到的各答案"修
 * 补"在一起，即分而治之)。
 *
 * @author hellotong
 * @date 2020-08-23 12:29
 */
public class MergeSort {
    public static void main(String[] args) {
        // int[] arr = {8, 4, 5, 7, 1, 3, 6, 2, 0}; // 9 个数字 需要 merge 9 - 1 次
        // 80000 个数字 需要 merge 80000 - 1 次
        int[] arr = new int[80000];
        int[] temp = new int[arr.length];
        
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        // 获取排序前的时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前时间：" + dateStr);

        mergeSort(arr, 0, arr.length - 1, temp);

        Date date2 = new Date();
        String dateStr2 = simpleDateFormat.format(date2);
        System.out.println("排序后时间：" + dateStr2);
        
        // System.out.println("排序后数组：" + Arrays.toString(arr));

        // 花费 50ms 左右
    }

    /**
     * 先分解后合并
     *
     * @param arr   待排序数组
     * @param left  左索引
     * @param right 右索引
     * @param temp  中转数组
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        // left < right 继续分解
        if (left < right) {
            // 中间索引
            int mid = (left + right) / 2;
            // 向左递归进行分解
            mergeSort(arr, left, mid, temp);
            // 向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            // 分解之后合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并两个有序序列
     *
     * @param arr   待排序数组
     * @param left  左边序列的起始下标
     * @param mid   中间索引
     * @param right 右边序列的末尾下标
     * @param temp  临时中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        // 左边序列用于遍历的索引
        int i = left;
        // 右边序列用于遍历的索引，j = mid 也行 
        int j = mid + 1;
        // temp 数组的索引
        int t = 0;

        // 1. 比较 arr[i] 和 arr[j]，小的放到 temp 数组中
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        // 2. 如果左边序列元素有剩余，则将剩余的元素直接拷贝到 temp 数组中
        while (i <= mid) {
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        // 3. 如果右边序列元素有剩余，则将剩余的元素直接拷贝到 temp 数组中
        while (j <= right) {
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        // 将 temp 数组的元素拷贝到 arr 数组中
        // temp 数组索引重置
        t = 0;
        int tempLeft = left;
        // System.out.println("tempLeft = " + tempLeft + " right = " + right);
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            tempLeft += 1;
            t += 1;
        }
    }
}
