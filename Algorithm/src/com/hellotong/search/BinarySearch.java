package com.hellotong.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找，也叫折半查找
 * 二分查找的前提是：待查找序列必须是有序序列
 * 
 * @author hellotong
 * @date 2020-08-24 7:52
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, 11, 11, 34, 89};
        int value = 11;
        
        // 一个元素只出现一次的情况
        /*int index = binarySearch(arr, 0, arr.length - 1, value);
        if (index != -1) {
            System.out.println(value + " 找到了，下标为 " + index);
        } else {
            System.out.println(value + " 不存在");
        }*/
        
        // 一个元素出现多次的情况
        List<Integer> list = binarySearch2(arr, 0, arr.length - 1, value);
        if (list.isEmpty()) {
            System.out.println(value + " 不存在");
        } else {
            System.out.print(value + " 找到了，下标为: ");
            for (Integer index : list) {
                System.out.print(index + " ");
            }
        }

    }

    /**
     * 二分查找，一个元素只出现一次的情况
     * @param arr 待查找数组
     * @param left 左索引
     * @param right 右索引
     * @param findVal 待查找的值
     * @return 如果找到返回下标，否则返回 -1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        // 递归结束条件
        if (left > right) {
            return -1;
        }
        
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            // 右递归查找
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            // 左递归查找
            return binarySearch(arr, left, mid - 1, findVal);
        } else { // findVal == arr[mid]，找到了
            return mid;
        }
    }

    /**
     * 二分查找，一个元素出现多次的情况，需要返回多个索引
     * 思路分析：
     * 1. 在找到索引 mid 时，不要马上返回
     * 2. 向左查找所有满足条件值的全部索引，将其放到集合中
     * 3. 向右查找所有满足条件值的全部索引，将其放到集合中
     * 4. 返回集合
     *
     * @param arr 待查找数组
     * @param left 左索引
     * @param right 右索引
     * @param findVal 待查找的值
     * @return 如果找过了，则返回一个由索引下标组成的集合，如果没找到则返回一个空的集合
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        // 递归结束条件
        if (left > right) {
            return new ArrayList<>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            // 右递归查找
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            // 左递归查找
            return binarySearch2(arr, left, mid - 1, findVal);
        } else { // findVal == arr[mid]，找到了
            // 创建一个存放索引值的 List
            List<Integer> list = new ArrayList<>();

            // 向左查找所有满足条件值的全部索引，将其放到集合中
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == findVal) {
                list.add(temp);
                temp -= 1;
            }
            // 将 mid 添加到集合中
            list.add(mid);
            // 向右查找所有满足条件值的全部索引，将其放到集合中
            temp = mid + 1;
            while (temp <= arr.length - 1 && arr[temp] == findVal) {
                list.add(temp);
                temp += 1;
            }
            
            return list;
        }
    }
}
