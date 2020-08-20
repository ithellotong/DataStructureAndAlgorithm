package com.hellotong.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 时间复杂度为 O(n^2)
 * 
 * @author hellotong
 * @date 2020-08-20 21:56
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2};
        System.out.println("排序前的数组为" + Arrays.toString(arr));
        int temp = 0;
        // 5 个数最多需要 4 个排序
        // 一趟排序确定一个数（最大值、次小值...）
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("排序后的数组为；" + Arrays.toString(arr));
    }
}
