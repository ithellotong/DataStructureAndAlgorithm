package com.hellotong.search;

/**
 * 顺序查找
 * 
 * @author hellotong
 * @date 2020-08-24 7:29
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        int value = 11;
        int index = seqSearch(arr, value);
        if (index != -1) {
            System.out.println(value + " 找到了，下标为 " + index);
        } else {
            System.out.println(value + " 不存在");
        }
    }

    /**
     * 只有找到一个值等于 value 时就返回下标
     * @param arr 待查找数组
     * @param value 待查找的值
     * @return 待查找的值在数组中的下标，若存在则返回元素下标，否则返回 -1，表示不存在
     */
    public static int seqSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            // 找到了
            if (arr[i] == value) {
                return i;
            }
        }
        // 没找到
        return -1;
    }
}
