package com.hellotong.avl;

/**
 * 平衡二叉树，是在二叉排序（搜索）树基础上的改进
 * 平衡二叉树也叫平衡二叉搜索树（Self-balancing binary search tree）又被称为 AVL 树， 可以保证查询效率较高。
 * 具有以下特点：它是一 棵空树或它的左右两个子树的高度差的绝对值不超过 1，并且左右两个子树都是一棵平衡二叉树。
 * 二叉排序树 BST 存在的问题分析:
 * (1) 左子树全部为空，从形式上看，更像一个单链表.
 * (2) 插入速度没有影响
 * (3) 查询速度明显降低(因为需要依次比较), 不能发挥 BST的优势，因为每次还需要比较左子树，
 * 其查询速度比单链表还慢
 * (4) 解决方案-平衡二叉树(AVL)
 *
 * @author hellotong
 * @date 2020-09-01 9:43
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        // 测试左旋转
        // int[] arr = {4, 3, 6, 5, 7, 8};
        // 测试右旋转
        // int[] arr = {10, 12, 8, 9, 7, 6};
        // 测试双旋转
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        // 递归添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        // 中序遍历
        avlTree.infixOrderList();

        System.out.println("在平衡处理后");
        System.out.println("树的高度：" + avlTree.getRoot().height());
        System.out.println("左子树的高度：" + avlTree.getRoot().left.height());
        System.out.println("右子树的高度：" + avlTree.getRoot().right.height());
        System.out.println("根节点为：" + avlTree.getRoot());
        System.out.println("根节点的左子节点：" + avlTree.getRoot().left);
        System.out.println("根节点的右子节点：" + avlTree.getRoot().right.left);
    }
}

/**
 * 平衡二叉树
 */
class AVLTree {
    /**
     * 二叉排序树的根节点
     */
    private Node root;

    /**
     * 获取根节点
     *
     * @return 根节点
     */
    public Node getRoot() {
        return root;
    }

    /**
     * 查找要删除的节点
     *
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
     *
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
     *
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
                // 删除的不是根节点
                if (parentNode != null) {
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
                    // 删除的是根节点
                    root = root.left;
                }

            } else {
                // 删除的不是根节点
                if (parentNode != null) {
                    // targetNode 有右子树
                    // 判断 targetNode 是 parentNode 的左子节点还是右子节点
                    if (parentNode.left != null && parentNode.left.value == value) {
                        // targetNode 是 parentNode 的左子节点
                        parentNode.left = targetNode.right;
                    } else {
                        // targetNode 是 parentNode 的右子节点
                        parentNode.right = targetNode.right;
                    }
                } else {
                    // 删除的是根节点
                    root = root.right;
                }
            }
        }
    }

    /**
     * 1. 删除以 node 为根节点的二叉排序树的最小值节点
     * 2. 返回以 node 为根节点的二叉排序树的最小值节点的值
     *
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
     *
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
     * 左旋转
     * 当 (右子树的高度 - 左子树的高度) > 1 时左旋转
     * 在添加完一个节点后判断是否调用
     */
    public void leftRotate() {
        // 创建一个新的节点，其值为当前节点的值
        // value = this.value，this 即为调用者
        Node newNode = new Node(this.value);
        // 把新的节点的左子树设置为当前节点的左子树
        newNode.left = this.left;
        // 把新的节点的右子树设置为当前节点的右子树的左子树
        newNode.right = this.right.left;
        // 把当前节点的值设置为当前节点的右子节点的值
        this.value = this.right.value;
        // 把当前节点的右子树设置为当前节点的右子树的右子树
        this.right = this.right.right;
        // 当前节点的左子树设置为新的节点
        this.left = newNode;
    }

    /**
     * 右旋转
     * 当 (左子树的高度 - 大子树的高度) > 1 时右旋转
     * 在添加完一个节点后判断是否调用
     */
    public void rightRotate() {
        // 创建一个新的节点，其值为当前节点的值
        // value = this.value，this 即为调用者
        Node newNode = new Node(this.value);
        // 把新的节点的右子树设置为当前节点的右子树
        newNode.right = this.right;
        // 把新的节点的左子树设置为当前节点的左子树的右子树
        newNode.left = this.left.right;
        // 把当前节点的值设置为当前节点的左子节点的值
        this.value = this.left.value;
        // 把当前节点的左子树设置为当前节点的左子树的左子树
        this.left = this.left.left;
        // 当前节点的右子树设置为新的节点
        this.right = newNode;
    }

    /**
     * 返回右子树的高度
     *
     * @return 右子树的高度
     */
    public int rightHeight() {
        if (this.right == null) {
            return 0;
        } else {
            return this.right.height();
        }
    }

    /**
     * 返回左子树的高度
     *
     * @return 左子树的高度
     */
    public int leftHeight() {
        if (this.left == null) {
            return 0;
        } else {
            return this.left.height();
        }
    }

    /**
     * 返回以当前节点为根节点的树的高度
     *
     * @return 以当前节点为根节点的树的高度
     */
    public int height() {
        // 树的高度就是左子树高度和右子树高度的最大值，最后 + 1，加上根节点的自身的高度 1
        return Math.max(this.left == null ? 0 : this.left.height(), this.right == null ? 0 : this.right.height()) + 1;
    }

    /**
     * 查找要删除的节点
     *
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
     *
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
     *
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

        // 添加完一个节点后，当 (右子树的高度 - 左子树的高度) > 1 时左旋转
        if (this.rightHeight() - this.leftHeight() > 1) {
            // 如果当前节点的右子树的左子树的高度大于当前节点的右子树的右子树的高度
            if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
                /**
                 * 双旋转
                 */
                // 先对当前节点的右子树进行右旋转
                this.right.rightRotate();
                // 再对当前节点进行左旋转
                this.leftRotate();
            } else {
                // 否则直接左旋转 
                this.leftRotate();
            }

            // 如果这个条件进来，就不去判断下面的条件，直接返回
            return;
        }

        // 添加完一个节点后，当 (左子树的高度 - 右子树的高度) > 1 时右旋转
        if (this.leftHeight() - this.rightHeight() > 1) {
            // 如果当前节点的左子树的右子树的高度大于当前节点的左子树的左子树的高度
            if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                /**
                 * 双旋转
                 */
                // 先对当前节点的左子树进行左旋转
                this.left.leftRotate();
                // 再对当前节点进行右旋转
                this.rightRotate();
            } else {
                // 否则直接右旋转
                this.rightRotate();
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
