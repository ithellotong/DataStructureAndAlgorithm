package com.hellotong.stack;

import java.util.Scanner;

/**
 * 数组模拟栈
 * 
 * @author hellotong
 * @date 2020-08-18 11:51
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        // 创建一个容量为 3 的栈
        ArrayStack stack = new ArrayStack(3);
        // 创建菜单，通过用户选择执行操作
        String key;
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        
        while (loop) {
            System.out.println("list: 遍历栈");
            System.out.println("push: 入栈");
            System.out.println("pop: 出栈");
            System.out.println("exit: 退出程序");
            System.out.println("请输入你的选择：");
            key = scanner.next();
            switch (key) {
                case "list":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        System.out.println("出栈的数据为：" + stack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    loop = false;
                    break;
                default:
                    System.out.println("输入错误，请重新输入");
                    break;
            }
        }

        System.out.println("程序退出");
    }
}

/**
 * 定义一个 ArrayStack，表示栈
 */
class ArrayStack {
    /**
     * 栈的容量
     */
    public int maxSize;

    /**
     * 用于存放栈的数据
     */
    public int[] stack;

    /**
     * 栈顶，初始值为 -1，代表栈中没有数据
     */
    public int top = -1;

    /**
     * 构造器，初始化栈的属性
     * @param maxSize 栈的容量
     */
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 判断栈是否已满
     * @return true or false
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈是否为空
     * @return true or false
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     * @param value 入栈元素
     */
    public void push(int value) {
        // 先判断栈是否已满
        if (isFull()) {
            System.out.println("栈满，无法入栈");
            return;
        }
        
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     * @return 栈顶元素
     */
    public int pop() {
        // 先判断栈是否为空
        if (isEmpty()) {
            // 抛出异常，结束程序执行，无需返回值，无需 return
            throw new RuntimeException("栈空，无法出栈");
        }

        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 遍历栈
     */
    public void list() {
        // 先判断栈是否为空
        if (isEmpty()) {
            System.out.println("栈空，无法遍历");
            return;
        }
        
        // 从栈顶开始往下遍历
        for (int i = top; i >= 0; i--) {
            System.out.println("stack[" + i + "] = " + stack[i]);
        }
    }
}
