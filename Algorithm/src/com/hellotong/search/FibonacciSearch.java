package com.hellotong.search;

import java.util.Arrays;

/**
 * 斐波那契查找
 *
 * @author hellotong
 * @date 2020-08-25 10:13
 */
public class FibonacciSearch {
    /**
     * 斐波那契数列的大小
     */
    public static int maxSize = 20;


    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};

        System.out.println("index = " + fibSearch(arr, 189));
    }
    
    /**
     * 因为后面我们 mid=low+F(k-1)-1，需要使用到斐波那契数列，因此我们需要先获取到一个斐波那契数列
     * 非递归方法得到一个斐波那契数列
     */
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;

        for (int i = 2; i < f.length; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }

        return f;
    }

    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        //表示斐波那契分割数值的下标
        int k = 0;
        //存放 mid 值
        int mid = 0;
        // 获取斐波那契数列
        int[] f = fib();
        // 获取斐波那契数列分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }

        // 因为 f[k] 值 可能大于 a 的 长度，因此我们需要使用 Arrays 类，构造一个新的数组，并指向 temp[]
        // 不足的部分会使用 0 填充
        int[] temp = Arrays.copyOf(a, f[k]);
        //实际上需求使用 a 数组最后的数填充 temp
        //举例:
        //temp = {1,8, 10, 89, 1000, 1234, 0, 0}=>{1, 8, 10, 89, 1000, 1234, 1234, 1234,}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        
        // 使用 while 来循环处理，找到我们的数 key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            // 继续向数组的前面查找（左边）
            if (key < temp[mid]) {
                high = mid - 1;
                // 为什么 k--
                // 说明
                // 1. 全部元素 = 前面的元素 + 后边元素
                // 2. f[k] = f[k-1] + f[k-2]
                // 因为 前面有 f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                // 即 在 f[k-1] 的前面继续查找 k--
                // 即下次循环 mid = f[k-1-1]-1
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                // 为什么是 k -=2
                // 说明
                // 1. 全部元素 = 前面的元素 + 后边元素
                // 2. f[k] = f[k-1] + f[k-2]
                // 3. 因为后面我们有 f[k-2] 所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
                // 4. 即在 f[k-2] 的前面进行查找 k -=2
                // 5. 即下次循环 mid = f[k - 1 - 2] - 1
                k -= 2;
            } else {
                // 找到，需要从确定返回的是哪个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
