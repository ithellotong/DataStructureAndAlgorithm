package com.hellotong.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 时间复杂度：O(nlogn)
 * 快速排序（Quicksort）是对冒泡排序的一种改进。基本思想是：通过一趟排序将要排序的数据分割成独立的两
 * 部分，其中一部分的所有数据都比另外一部分的所有数据都要小，然后再按此方法对这两部分数据分别进行快速排
 * 序，整个排序过程可以递归进行，以此达到整个数据变成有序序列
 *
 * @author hellotong
 * @date 2020-08-23 10:28
 */
public class QuickSort {
    public static void main(String[] args) {
        // int[] arr = {-9, 78, 0, 23, -567, 70};
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        quickSort2(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 快速排序，pivot 选取序列的第一个值为枢轴值
     *
     * @param arr   待排序数组
     * @param left  左下标
     * @param right 右下标
     */
    public static void quickSort2(int[] arr, int left, int right) {
        // 递归结束条件
        if (left < right) {
            int low = left;
            int high = right;
            // 枢轴值
            int pivot = arr[low];
            while (low < high) {
                while (low < high && arr[high] >= pivot) {
                    high--;
                }
                arr[low] = arr[high];

                while (low < high && arr[low] <= pivot) {
                    low++;
                }
                arr[high] = arr[low];
            }

            // 退出循环时，low == high arr[low] = arr[high] = pivot
            // arr[high] = pivot 一样
            arr[low] = pivot;

            // 递归左边
            quickSort2(arr, left, low - 1);

            // 递归右边
            quickSort2(arr, low + 1, right);
        }
    }

    /**
     * 快速排序，pivot 选取中间值为枢轴值
     *
     * @param arr   待排序数组
     * @param left  左下标
     * @param right 右下标
     */
    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        // 中枢值
        int pivot = arr[(left + right) / 2];
        // 临时变量用于交换
        int temp = 0;

        // while 循环的目的就是让
        // 比 pivot 小的放在左边，比 pivot 大的放在右边
        while (l < r) {
            // 在 pivot 的左边一直找，找到大于等于 pivot 值，才退出
            while (arr[l] < pivot) {
                l += 1;
            }

            // 在 pivot 的右边一直找，找到小于等于 pivot 值，才退出
            while (arr[r] > pivot) {
                r -= 1;
            }

            // 代表 pivot 的左边和右边已经分好了
            if (l >= r) {
                break;
            }

            // 否则，就交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 如果交换完发现 arr[l] == pivot，则右下标前移，防止跳不出循环
            if (arr[l] == pivot) {
                r -= 1;
            }

            // 如果交换完发现 arr[r] == pivot，则左下标后移，防止跳不出循环
            if (arr[r] == pivot) {
                l += 1;
            }
        }

        // 如果 l == r，必须 l++ r--，否则出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }

        // 左递归
        if (left < r) {
            quickSort(arr, left, r);
        }

        // 右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
