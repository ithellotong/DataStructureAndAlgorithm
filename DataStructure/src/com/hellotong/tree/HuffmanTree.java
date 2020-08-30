package com.hellotong.tree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 赫夫曼树
 * 构成赫夫曼树的步骤:
 * 1. 从小到大进行排序,将每一个数据,每个数据都是一个节点,每个节点可以看成是一颗最简单的二叉树
 * 2. 取出根节点权值最小的两颗二叉树
 * 3. 组成一颗新的二叉树,该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
 * 4. 再将这颗新的二叉树,以根节点的权值大小再次排序,不断重复1-2-3-4的步骤,直到数列中,所有的数据都被处理,就得到一颗赫夫曼树
 *
 * @author hellotong
 * @date 2020-08-30 10:01
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        // 前序遍历赫夫曼树
        preOderList(root);
    }

    public static Node createHuffmanTree(int[] arr) {
        // 为了操作方便，首先需要从做三件事
        // 1. 把数组的每一个 value 封装到一个 Node 中
        // 2. 将每个 Node 添加到一个 ArrayList 集合中，方便排序
        ArrayList<Node> list = new ArrayList<>();
        for (int value : arr) {
            list.add(new Node(value));
        }
        
        // 当集合中的元素大于 1 时进入循环，终止条件：集合中只有一个节点，即赫夫曼树的根节点
        while (list.size() > 1) {
            // 步骤
            // 1. 先对集合中的元素进行排序
            Collections.sort(list);
            // 2. 取出并从集合中移除最小的元素（节点 / 一棵二叉树）
            Node leftNode = list.remove(0);
            // 3. 取出并从集合中移除次小的元素（节点 / 一棵二叉树）
            Node rightNode = list.remove(0);
            // 4. 由最小节点和次小节点创建一根新的二叉树
            Node parentNode = new Node(leftNode.value + rightNode.value);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            // 5. 将新创建的二叉树的根节点添加到集合中
            list.add(parentNode);
            // 循环操作...
        }
        
        // 返回赫夫曼树的根节点
        return list.get(0);
    }

    /**
     * 前序遍历
     * @param root 树的根节点
     */
    public static void preOderList(Node root) {
        if (root != null) {
            root.preOrderList();
        } else {
            System.out.println("树为空，无法遍历");
        }
    }
}

/**
 * 树节点
 * 因为节点需要进行排序，所以要实现 Comparable 接口
 */
class Node implements Comparable<Node> {
    /**
     * 节点的权值
     */
    public int value;

    /**
     * 指向左子节点。默认为 null
     */
    public Node left;

    /**
     * 指向右子节点，默认为 null
     */
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 前序遍历
     */
    public void preOrderList() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrderList();
        }
        if (this.right != null) {
            this.right.preOrderList();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 按照 value 的值从小到大排序
        return this.value - o.value;
    }
}
