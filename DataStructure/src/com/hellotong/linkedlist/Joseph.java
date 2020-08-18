package com.hellotong.linkedlist;

import sun.util.resources.de.CurrencyNames_de_CH;

/**
 * Josephu 约瑟夫环问题
 * 问题为：设编号为 1，2，… n 的 n 个人围坐一圈，约定编号为 k（1<=k<=n）的人从 1 开始报数，数到
 * m 的那个人出列，它的下一位又从 1 开始报数，数到 m 的那个人又出列，依次类推，直到所有人出列为止，由此
 * 产生一个出队编号的序列
 *
 * @author hellotong
 * @date 2020-08-17 19:09
 */
public class Joseph {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addChlid(5);
        circleSingleLinkedList.showChild();
        
        printChildNoByRule(circleSingleLinkedList.first, 2, 2, 5);
    }

    /**
     * 打印小孩出队的编号序列
     * 思路：
     * 1. 检查参数的合法性
     * 2. 初始化辅助指针 curChild 指向当前小孩节点，初始值指向 first
     * 初始化辅助指针 preCurChild 指向 当前小孩节点的前一个节点，初始值指向环形链表的尾节点
     * 3. 通过传入的 k 定位到开始报数的小孩节点，移动 curChild 和 preCurChild 指针，
     * 此时 curChild 指向的就是编号为 k 的小孩节点，preCurChild 当前为小孩节点的前一个
     * 4. 从编号为 k 的小孩节点开始数 m 下，移动 curChild 和 preCurChild 指针，
     * 此时 curChild 指向的就是要出圈的小孩，打印小孩编号。
     * 5. 循环出圈，直到圈中只剩下一个节点，此时 curChild == preCurChild
     * 
     * @param first 环形单链表的第一个节点
     * @param k 从编号为几的小孩开始报数
     * @param m 数几下
     */
    public static void printChildNoByRule(Child first, int k, int m, int nums) {
        // k 的范围检查
        if (k < 1 || k > nums) {
            System.out.println("k 的范围不合法");
            return;
        }
        
        // 指向当前小孩，初始值指向 first
        Child curChild = first;
        // 指向当前小孩的前一个小孩
        Child preCurChild = first;
        // 遍历找到链表的尾节点，preCurChild 初始值指向尾节点
        while (preCurChild.next != first) {
            preCurChild = preCurChild.next;
        }
        // 循环结束后，preCurChild 指向的就是尾节点
        
        // 定位到开始报数的小孩
        for (int i = 1; i < k; i++) {
            preCurChild = curChild;
            curChild = curChild.next;
        }
        
        // 循环结束后 curChild 指向的就是编号为 k 的小孩节点
        
        do {
            // 开始数数
            for (int i = 1; i < m; i++) {
                // 移动两个指针
                preCurChild = curChild;
                curChild = curChild.next;
            }

            // 循环结束后 curChild 指向的就是要出队的小孩，preCurChild 为 curChild 的前一个
            // 打印要出队的小孩的编号
            System.out.println(curChild.no);
            // 删除要出队的小孩节点
            preCurChild.next = curChild.next;
            curChild = preCurChild.next;
        } while (curChild != preCurChild);
        
        // 循环结束时链表中只剩一个节点，即 curChild == preCurChild
        // 打印即可
        System.out.println(curChild.no);
    }
}

/**
 * 环形单链表
 */
class CircleSingleLinkedList {
    /**
     * 声明第一个节点 first，当前没有编号
     */
    public Child first = null;

    /**
     * 添加小孩节点，构建成一个环形单链表
     * @param nums 要创建多少个小孩节点
     */
    public void addChlid(int nums) {
        // 判断 nums 的范围
        if (nums < 1) {
            System.out.println("nums 范围不合法");
            return;
        }
        
        // 定义辅助指针，用于指向当前链表的最后一个节点
        Child last = first;
        
        // for 循环创建环形单链表
        // 下标从 1 开始，编号即为 i
        for (int i = 1; i <= nums; i++) {
            Child child = new Child(i);
            // 先初始化第一个节点，同时构建一个节点的环形单链表
            if (first == null) {
                first = child;
                first.next = first;
                last = first;
            } else {
            // 初始化其他节点
                last.next = child;
                child.next = first;
                last = child;
            }
        }
    }

    /**
     * 显示所有小孩的编号
     */
    public void showChild() {
        // 定义辅助指针用于遍历链表
        Child curChild = first;
        while (true) {
            System.out.println("小孩的编号为：" + curChild.no);
            if (curChild.next != first) {
                curChild = curChild.next;
            } else {
                break;
            }
        }
    }
    
}

/**
 * 定义小孩节点
 */
class Child {
    /**
     * 编号
     */
    public int no;

    /**
     * 指向下一个小孩节点
     */
    public Child next;
    
    public Child(int no) {
        this.no = no;
    }
}
