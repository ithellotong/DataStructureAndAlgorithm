package com.hellotong.queue;

import java.util.Scanner;

/**
 * 使用数组模拟环形队列案例
 * 
 * 思路如下：
 * 1. 调整 front 的含义，front 指向队列的第一个元素，初始值为 0
 * 2. 调整 rear 的含义，rear 指向队列最后一个元素的下一个位置，初始值也为 0，
 *    空出一个位置作为预留空间形成约定，方便之后的操作。也就是说数组始终中有一个位置是不能被使用的。
 * 3. 队列满的条件：(rear + 1) % maxSize == front   
 * 4. 队列空的条件：front == rear（与原来判断条件一致）
 * 5. 队列中有效元素的个数：(rear + maxSize - front) % maxSize
 * 
 * 用到的算法：取模 %
 * 
 * @author hellotong
 * @date 2020-08-15 11:09
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        // 创建一个容量为 4 的环形队列，但队列的最大有效数据为 3，因为有一个预留空间
        CircleArrayQueue queue = new CircleArrayQueue(4);
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
 * 使用数组模拟环形队列
 */
class CircleArrayQueue {
    /**
     * 队列最大容量
     */
    private int maxSize;
    /**
     * 队头指针，指向队头元素
     */
    private int front;
    /**
     * 队尾指针，指向队尾元素的下一个位置
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
    public CircleArrayQueue(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        // 初始化队头指针和队尾指针为 0，表示现在队列为空
        this.front = 0;
        this.rear = 0;
        this.arr = new int[maxSize];
    }

    /**
     * 判断队列是否满
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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

        arr[rear] = n;
        // 取模，环形处理
        rear = (rear + 1) % maxSize;
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

        // 首先获取队头元素的值
        int value = arr[front];
        // 队头指针后移，取模，环形处理
        front = (front + 1) % maxSize;
        // 返回队头元素的值
        return value;
    }

    /**
     * 遍历队列方式 1
     */
    public void showQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法遍历队列");
        }

        // 从 front 开始遍历 size() 个元素
        for (int i = front; i < front + size(); i++) {
            // 对 i 取模，循环遍历队列元素
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
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
        
        int i = 0;
        // 循环终止条件 (i + front) == rear
        while ((i + front) % maxSize != rear) {
            System.out.printf("arr[%d] = %d\n", (i + front) % maxSize, arr[(i + front) % maxSize]);
            i++;
        }
    }

    /**
     * 获取队列中有效数据的个数
     * @return 队列中有效数据的个数
     */
    public int size() {
        return (rear + maxSize - front) % maxSize;
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

        return arr[front];
    }
}
