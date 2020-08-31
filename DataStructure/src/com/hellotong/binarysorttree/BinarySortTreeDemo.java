package com.hellotong.binarysorttree;

import java.lang.annotation.Target;

/**
 * 二叉排序树
 * 
 * @author hellotong
 * @date 2020-08-31 13:08
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 0};
        BinarySortTree binarySortTree = new BinarySortTree();
        // 循环的向二叉排序树中添加节点
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        // 二叉排序树中序遍历的结果是一个有序序列0
        binarySortTree.infixOrderList();
        System.out.println("删除节点后：");
        // 测试删除叶子节点
        /*binarySortTree.deleteNode(2);
        binarySortTree.deleteNode(5);
        binarySortTree.deleteNode(9);
        binarySortTree.deleteNode(12);*/
        
        // 测试删除只有一棵子树的节点
        // binarySortTree.deleteNode(1);
        
        // 测试删除有两棵子树的节点
        binarySortTree.deleteNode(3);
        binarySortTree.deleteNode(10);
        binarySortTree.infixOrderList();
    }
}

/**
 * 二叉排序树
 */
class BinarySortTree {
    /**
     * 二叉排序树的根节点
     */
    private Node root;

    /**
     * 查找要删除的节点
     * @param value 要删除的节点的值
     * @return 如果找到返回该节点，否则返回 null      
     */
    public Node search(int value) {
        if (root == null) {
            System.out.println("树为空，无法查找");
            return null;
        } else {
            return root.search(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     * @param value 要删除节点的值
     * @return 如果找到返回父节点，否则返回 null
     */
    public Node searchParent(int value) {
        if (root == null) {
            System.out.println("树为空，无法查找父节点");
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 从二叉排序树中删除节点
     * @param value 要删除的节点的值
     */
    public void deleteNode(int value) {
        // 查找要删除的节点
        Node targetNode = search(value);
        // 没找到，直接返回
        if (targetNode == null) {
            return;
        }
        
        // 如果当前这棵二叉排序树只有一个节点，即根节点
        if (root.left == null && root.right == null) {
            // 直接删除
            root = null;
            return;
        }
        
        // 查找要删除节点的父节点
        // 上面已经处理了要删除节点是根节点也就是没有父节点的情况，所以 parentNode 保证不为空
        Node parentNode = searchParent(value);
        // 如果要删除的节点是叶子节点
        if (targetNode.left == null && targetNode.right == null) {
            // 判断 targetNode 是 parentNode 的左子节点还是右子节点
            if (parentNode.left != null && parentNode.left.value == value) {
                // 是左子节点
                parentNode.left = null;
            } else if (parentNode.right != null && parentNode.right.value == value) {
                // 是右子节点
                parentNode.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {
            // 如果要删除的节点有两棵子树
            // 找到并删除要删除节点的右子树的最小值节点 或者 找到并删除要删除节点的左子树的最大值节点
            int minNode = searchAndDelRightTreeMin(targetNode.right);
            deleteNode(minNode);
            // 将最小值节点的值赋给要删除的节点
            targetNode.value = minNode;
        } else {
            // 如果要删除的节点只有一棵子树
            // 判断 targetNode 有左子树还是有右子树
            if (targetNode.left != null) {
                // targetNode 有左子树
                // 判断 targetNode 是 parentNode 的左子节点还是右子节点
                if (parentNode.left != null && parentNode.left.value == value) {
                    // targetNode 是 parentNode 的左子节点
                    parentNode.left = targetNode.left;
                } else {
                    // targetNode 是 parentNode 的右子节点
                    parentNode.right = targetNode.left;
                }
            } else {
                // targetNode 有右子树
                // 判断 targetNode 是 parentNode 的左子节点还是右子节点
                if (parentNode.left != null && parentNode.left.value == value) {
                    // targetNode 是 parentNode 的左子节点
                    parentNode.left = targetNode.right;
                } else {
                    // targetNode 是 parentNode 的右子节点
                    parentNode.right = targetNode.right;
                }
            }
        }
    }

    /**
     * 1. 删除以 node 为根节点的二叉排序树的最小值节点
     * 2. 返回以 node 为根节点的二叉排序树的最小值节点的值
     * @param node
     * @return
     */
    public int searchAndDelRightTreeMin(Node node) {
        // 辅助节点，用于查找最小值节点
        Node temp = node;
        // 一直向左子树找
        while (temp.left != null) {
            temp = temp.left;
        }
        
        // 循环结束 temp 就是最小值节点
        deleteNode(temp.value);
        return temp.value;
    }
    
    /**
     * 向二叉排序树中递归添加节点
     * @param node 要添加的节点
     */
    public void add(Node node) {
        // 如果树为空，直接将要添加的节点设置为根节点
        if (root == null) {
            root = node;
        } else {
            // 从根节点开始递归添加
            root.add(node);
        }
    }

    /**
     * 二叉排序树的中序遍历
     */
    public void infixOrderList() {
        // 树不为空
        if (root != null) {
            root.infixOrderList();
        } else {
            System.out.println("树为空，无法遍历");
        }
    }
}

/**
 * 树节点
 */
class Node {
    /**
     * 节点的值
     */
    public int value;

    /**
     * 指向左子节点，默认为 null
     */
    public Node left;

    /**
     * 指向右子节点
     */
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 查找要删除的节点
     * @param value 要删除的节点的值
     * @return 如果找到返回该节点，否则返回 null      
     */
    public Node search(int value) {
        if (this.value == value) {
            return this;
        }
        
        // 如果要查找的节点值小于当前节点的值
        if (value < this.value) {
            // 如果当前节点的左子节点为空，说明没找到，否则向左子树递归查找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            // 如果要查找的节点值等于或大于当前节点的值
            // 如果当前节点的右子节点为空，说明没找到，否则向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     * @param value 要删除节点的值
     * @return 如果找到返回父节点，否则返回 null
     */
    public Node searchParent(int value) {
        // 如果满足以下条件 this 就是要删除节点的父节点
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果要查找的值小于当前节点（父节点）的值，且当前节点的左子节点不为空
            if (this.left != null && value < this.value) {
                // 向左子树递归查找
                return this.left.searchParent(value);
            } else if (this.right != null && value >= this.value) {
                // 如果要查找的值大于或等于当前节点（父节点）的值，且当前节点的右子节点不为空
                // 向右子树递归查找
                return this.right.searchParent(value);
            } else {
                // 如果找不到父节点返回 null
                return null;
            }
        }
    }
    
    /**
     * 向二叉排序树中递归添加节点
     * @param node 要添加的节点
     */
    public void add(Node node) {
        // node 为空，直接返回
        if (node == null) {
            return;
        }
        
        // 如果要添加的节点的值小于当前节点的值
        if (node.value < this.value) {
            // 当前节点的左子树为空，直接添加到左子树上
            if (this.left == null) {
                this.left = node;
            } else {
                // 当前节点的左子树不为空，递归向左子树添加
                this.left.add(node);
            }
        } else {
            // 如果要添加的节点的值等于或大于当前节点的值
            // 当前节点的右子树为空，直接添加到右子树上
            if (this.right == null) {
                this.right = node;
            } else {
                // 当前节点的右子树不为空，递归向右子树添加
                this.right.add(node);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrderList() {
        if (this.left != null) {
            this.left.infixOrderList();
        }

        System.out.println(this);
        
        if (this.right != null) {
            this.right.infixOrderList();
        }
    }
}
