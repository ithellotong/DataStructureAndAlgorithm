package com.hellotong.stack;

import java.util.Scanner;

/**
 * 链表模拟栈
 * 
 * @author hellotong
 * @date 2020-08-18 12:23
 */
public class SingleLinkedListStackDemo {
    public static void main(String[] args) {
        // 创建一个链栈
        SingleLinkedListStack stack = new SingleLinkedListStack(3);
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

class SingleLinkedListStack {
    /**
     * 链表头节点
     */
    public Node head = new Node();
    
    /**
     * 栈的容量
     */
    public int maxSize;

    /**
     * 链表中实际元素的个数
     */
    public int size;
    
    /**
     * 栈顶指针，初始值指向头节点
     */
    public Node top = head;
    
    public SingleLinkedListStack(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * 判断栈是否已满
     * @return true or false
     */
    public boolean isFull() {
        return size == maxSize;
    }

    /**
     * 判断栈是否为空
     * @return true or false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 入栈
     * 使用头插法
     * @param value 要入栈的元素
     */
    public void push(int value) {
        // 先判断栈是否已满
        if (isFull()) {
            System.out.println("栈满，无法入栈");
            return;
        }
        
        size++;
        Node node = new Node(value);

        // 使用头插法插入
        node.next = head.next;
        head.next = node;
        
        // 栈顶指针始终指向第一个节点（栈顶元素）
        top = node;
    }

    /**
     * 出栈
     * @return 栈顶元素
     */
    public int pop() {
        // 先判断栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈空，无法出栈");
        }
        
        size--;
        // 删除栈顶元素，即第一个节点
        int value = top.value;
        head.next = top.next;
        // 重新设置栈顶指针
        top = head.next;
        
        return value;
    }

    /**
     * 遍历
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，无法遍历");
            return;
        }
        
        // 遍历
        Node cur = head.next;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }

        System.out.println();
    }
}

/**
 * 定义链表节点
 */
class Node {
    /**
     * 数据
     */
    public int value;
    /**
     * 指向下一个节点
     */
    public Node next;
    
    public Node(int value) {
        this.value = value;
    }
    
    public Node() {
    }
}
