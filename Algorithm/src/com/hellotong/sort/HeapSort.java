package com.hellotong.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 堆排序
 * 时间复杂度：O(nlogn)
 * 堆排序的基本思想：
 * 1. 将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
 * 2. 将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
 * 3. 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
 * 
 * @author hellotong
 * @date 2020-08-29 18:45
 */
public class HeapSort {
    public static void main(String[] args) {
        // int[] arr = {4, 6, 8, 5, 9, -1, 100, -90};
        // 生成 800w 个数据，测试希尔排序所花费的时间
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        // 获取排序前的时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前时间：" + dateStr);
        
        // 800w 数据花费 3 s 左右
        heapSort(arr);

        Date date2 = new Date();
        String dateStr2 = simpleDateFormat.format(date2);
        System.out.println("排序后时间：" + dateStr2);
        // System.out.println(Arrays.toString(arr));
    }

    /**
     * 堆排序
     * @param arr 待排序数组
     */
    public static void heapSort(int[] arr) {
        System.out.println("堆排序");
        
        // 分步处理
        /*System.out.println("第一次调整：");
        adjustHeap(arr, 1, arr.length);
        System.out.println("第二次调整：");
        adjustHeap(arr, 0, arr.length);*/
            
        // 1. 将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆;
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        // 循环结束该数组就是一个大顶堆

        // 2. 将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端
        // 3.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
        // 用于元素交换
        int temp = 0;
        // 调整次数比数组长度少 1，假如 5 个元素调整 4 次就行了
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;
            // 交换完之后，继续从根节点开始向下调整，使其成为大顶堆，每次调用长度 - 1
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 按照堆的特性调整数组（堆），每调用一次调整一次
     * 含义有两个：不同情况调用时含义不一样
     * 第一个含义：（构建堆的过程）
     * 1. 先从最后一个非叶子节点开始调整，此时非叶子节点上存放的就是该子树上的最大值
     * 2. 再从倒数第二个非叶子节点开始调整，此时倒数第二个非叶子节点上存放的就是该子树上的最大值
     * 3. 以此类推，直到第一个非叶子节点调整完成，此时该数组就是一个大顶堆
     * 第二个含义：（调整堆的过程）
     * 前提 ：当数组为一个大顶堆时，根节点存放的就是整个数组的最大值，此时需要将最大值与数组最后一个元素进行交换
     * 在元素交换后，此时堆的结构已经发生了变化，就需要继续调整数组为大顶堆
     * 1. 此时调用 adjustHeap，就直接从根节点往下调整即可，
     * 2. 调整结束后就又成了大顶堆了，循环操作
     * 
     * @param arr 待排序数组
     * @param i 非叶子节点的下标
     * @param length 要调整的数组的长度，每次调整成大顶堆之后，长度就会 - 1
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        // 用于存放非叶子节点的值，用于交换
        int temp = arr[i];
        // 从非叶子节点开始向下调整，满足堆的特性
        for (int k = 2 * i + 1; k < length; k = k * 2 + 1) {
            // 如果左子节点小于右子节点，k 指向右子节点
            // k + 1 < length 防止 arr[k + 1] 越界
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            
            // 非叶子节点上的值和左右子节点的较大值进行交换
            if (arr[i] < arr[k]) {
                arr[i] = arr[k];
                arr[k] = temp;
                // !!! 重要，如果 i 不是最后一个非叶子节点，则 i 指向 k，会继续进行向下调整
                i = k;
            } else {
                break;
            }
        }
    }
}
