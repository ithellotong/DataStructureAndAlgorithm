package com.hellotong.queue;

import java.util.Scanner;

/**
 * 使用数组模拟队列案例
 * 
 * 存在的问题：取出数据后的数组空间没法重复利用，数组使用一次就不能在使用了，
 * 此队列为一次性队列。
 * 
 * 解决方法：将队列做成环形队列，见 CircleArrayQueueDemo.java
 * 
 * @author hellotong
 * @date 2020-08-15 9:05
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        Scanner scanner = new Scanner(System.in);
        // 用于接收用户输入的菜单指令
        char key;
        // 循环的标记
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列获取数据");
            System.out.println("h(head): 显示队头元素");
            System.out.println("e(exit): 退出程序");
            // 接收一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    try {
                        queue.showQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    try {
                        System.out.print("请输入要添加到队列的数据：");
                        int value = scanner.nextInt();
                        queue.addQueue(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        int result = queue.getQueue();
                        System.out.println("取出的数据为" + result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int result = queue.headQueue();
                        System.out.println("队头元素为：" + result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    // 在退出程序之前需要关闭 Scanner
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出");
    }
    
}

/**
 * 使用数组模拟队列
 */
class ArrayQueue {
    /**
     * 队列最大容量
     */
    private int maxSize;
    /**
     * 队头指针，指向队头元素的前一个位置
     */
    private int front;
    /**
     * 队尾指针，指向队列的最后一个元素
     */
    private int rear;
    /**
     * 用于存放队列数据的数组
     */
    private int[] arr;

    /**
     * 初始化队列属性
     * @param arrMaxSize 队列的最大容量
     */
    public ArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        // 初始化队头指针和队尾指针为 -1，表示现在队列为空
        this.front = -1;
        this.rear = -1;
        this.arr = new int[maxSize];
    }

    /**
     * 判断队列是否满
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 添加数据到队列（入队列）
     * @param n 要添加的数据
     */
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            throw new RuntimeException("队列已满，无法添加数据");
        }
        
        rear++;
        arr[rear] = n;
    }

    /**
     * 获取队列数据（出队列）
     * @return 队头元素
     */
    public int getQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法获取数据");
        }
        
        front++;
        return arr[front];
    }

    /**
     * 遍历队列方式 1
     */
    public void showQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法遍历队列");
        }

        for (int i = front + 1; i <= rear; i++) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }

    /**
     * 遍历队列方式 2
     */
    public void showQueue2() {
        // 判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法遍历队列");
        }

        int i = 1;
        while ((i + front) != rear + 1) {
            System.out.printf("arr[%d] = %d\n", i + front, arr[i + front]);
            i++;
        }
    }

    /**
     * 显示队头数据，不是出队列
     * @return 队头元素
     */
    public int headQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法获取数据"); 
        }
        
        return arr[front + 1];
    }
    
}
