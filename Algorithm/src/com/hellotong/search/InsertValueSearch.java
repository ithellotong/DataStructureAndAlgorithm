package com.hellotong.search;

/**
 * 插值查找
 * 插值查找算法类似于二分查找，不同的是插值查找每次从自适应 mid 处开始查找。
 * mid = left + (right – left) * (findVal – arr[left]) / (arr[right] – arr[left])
 * 注意：
 * 1. 对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找，速度较快。
 * 2. 关键字分布不均匀的情况下，该方法不一定比折半查找要好
 * 
 * @author hellotong
 * @date 2020-08-24 9:59
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int value = 100;
        int index = insertValueSearch(arr, 0, arr.length - 1, value);
        if (index != -1) {
            System.out.println(value + " 找到了，下标为 " + index);
        } else {
            System.out.println(value + " 不存在");
        }
    }
    
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("---");
        // 注意：arr[0] > findVal || arr[arr.length - 1] < findVal 必须需要
        // 否则我们得到的 mid 可能越界
        if (left > right || arr[0] > findVal || arr[arr.length - 1] < findVal) {
            return -1;
        }
        
        // mid 自适应
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
