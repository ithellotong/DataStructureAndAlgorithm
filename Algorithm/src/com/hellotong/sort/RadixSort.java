package com.hellotong.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 基数排序
 * 时间复杂度：O(k * n)
 * 基数排序的基本思想：将所有待比较数值统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。
 * 这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列。
 *
 * @author hellotong
 * @date 2020-08-23 17:16
 */
public class RadixSort {
    public static void main(String[] args) {
        // int[] arr = {53, 3, 542, 748, 14, 214};
        int[] arr = new int[8000000];

        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        // 获取排序前的时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前时间：" + dateStr);

        // 8000000 数据，花费 600 ms，速度非常快，基数排序是空间换时间，数据量大时内存会不够用，所以不适用于海量数据
        radixSort(arr);

        Date date2 = new Date();
        String dateStr2 = simpleDateFormat.format(date2);
        System.out.println("排序后时间：" + dateStr2);
    }

    public static void radixSort(int[] arr) {
        // 找出 arr 数组中最大数的最大位数
        // 假定 arr[0] 为最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // 循环退出后，max 即为 arr 数组的最大值
        // 求最大值的最大位数（巧妙的利用了字符串的特性）
        int maxLength = (max + "").length();

        // 定义一个二维数组，每个一维数组是一个桶
        // 二维数组的横坐标表示第几个桶，纵坐标表示第几个桶的第几个元素
        // 桶的数量是固定的，0-9 为 10 个桶，每个桶有多少个元素不确定，所以初始化为所有元素的数量
        int[][] bucket = new int[10][arr.length];
        // 定义一个一维数组用于记录每个桶（10 个）中实际的元素个数
        int[] bucketElementCounts = new int[10];

        // 0 表示处理个位，1 表示处理十位，以此类推
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            // 第 i 轮，针对每个元素的位数进行排序处理
            for (int j = 0; j < arr.length; j++) {
                // 得到每个元素的位数，第一次为 个位，以此类推
                int digitOfElement = arr[j] / n % 10;
                // 将 arr[j] 放到 digitOfElement 对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                // digitOfElement 这个桶中的元素个数加 1
                bucketElementCounts[digitOfElement]++;
            }

            // 将每个桶中的元素依次放到 arr 数组中
            // bucketElementCounts.length = 10 也可以是 bucket.length（一维数组的长度）
            // index 为 arr 的索引
            int index = 0;
            // k 表示第几个桶
            for (int k = 0; k < bucketElementCounts.length; k++) {
                // 第 k 个桶中有数据才拷贝
                if (bucketElementCounts[k] != 0) {
                    // 将第 k 个桶中的第 l 个元素依次拷贝到 arr 数组中
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index] = bucket[k][l];
                        // 索引后移
                        index++;
                    }
                }

                // 每个桶中的元素拷贝完之后，将桶重置为空，便于下次处理第十位、百位...数
                bucketElementCounts[k] = 0;
            }

            // System.out.println("第 " + (i + 1) + " 轮排序后" + Arrays.toString(arr));
        }
    }
}
