package com.hellotong.binarysearchnorecursion;

/**
 * 二分查找非递归算法
 * 
 * @author hellotong
 * @date 2020-09-04 11:02
 */
public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        int[] arr = {1, 3, 7, 8, 20, 33, 100};
        int index = binarySearch(arr, 8);
        System.out.println("index = " + index);
    }

    /**
     * 二分查找
     * @param arr 待查找升序数组
     * @param target 目标值
     * @return 如果存在返回目标值下标，否则返回 -1
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        // 如果没找到返回 -1
        return -1;
    }
}
