package com.hellotong.tree;

/**
 * 使用数组模拟顺序存储二叉树，完成前序中序后序遍历
 * 顺序二叉树的应用场景：堆排序
 * 
 * @author hellotong
 * @date 2020-08-29 8:39
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        // 前序遍历
        System.out.println("前序遍历");
        arrayBinaryTree.preOrderList();
        // 中序遍历
        System.out.println();
        System.out.println("中序遍历");
        arrayBinaryTree.infixOrderList();
        // 后序遍历
        System.out.println();
        System.out.println("后序遍历");
        arrayBinaryTree.postOrderList();
    }
}

/**
 * 定义一个 ArrayBinaryTree，实现顺序二叉树的遍历
 */
class ArrayBinaryTree {
    /**
     * 用于存储数据的数组
     */
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 重载顺序二叉树的前序遍历，内部传输数组下标 0 / 根节点 0
     */
    public void preOrderList() {
        preOrderList(0);
    }

    /**
     * 顺序二叉树的前序遍历
     * 先输出，再遍历左、右节点
     * @param index 数组的下标
     */
    public void preOrderList(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，无法遍历");
            return;
        }
        
        System.out.print(arr[index] + " ");
        // 防止 (index * 2 + 1) 越界
        if ((index * 2 + 1) < arr.length) {
            preOrderList(index * 2 + 1);
        }
        
        if ((index * 2 + 2) < arr.length) {
            preOrderList(index * 2 + 2);
        }
    }

    /**
     * 重载顺序二叉树的中序遍历，内部传输数组下标 0 / 根节点 0
     */
    public void infixOrderList() {
        infixOrderList(0);
    }

    /**
     * 顺序二叉树的中序遍历
     * 先遍历左节点，再输出，再遍历右节点
     * @param index 数组的下标
     */
    public void infixOrderList(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，无法遍历");
            return;
        }

        // 防止 (index * 2 + 1) 越界
        if ((index * 2 + 1) < arr.length) {
            infixOrderList(index * 2 + 1);
        }
        System.out.print(arr[index] + " ");
        if ((index * 2 + 2) < arr.length) {
            infixOrderList(index * 2 + 2);
        }
    }

    /**
     * 重载顺序二叉树的后序遍历，内部传输数组下标 0 / 根节点 0
     */
    public void postOrderList() {
        postOrderList(0);
    }

    /**
     * 顺序二叉树的中序遍历
     * 先遍历左、右节点，再输出
     * @param index 数组的下标
     */
    public void postOrderList(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，无法遍历");
            return;
        }

        // 防止 (index * 2 + 1) 越界
        if ((index * 2 + 1) < arr.length) {
            postOrderList(index * 2 + 1);
        }
        if ((index * 2 + 2) < arr.length) {
            postOrderList(index * 2 + 2);
        }
        System.out.print(arr[index] + " ");
    }
}
