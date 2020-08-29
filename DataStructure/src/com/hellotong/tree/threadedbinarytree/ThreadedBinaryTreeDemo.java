package com.hellotong.tree.threadedbinarytree;

import java.util.Scanner;

/**
 * 线索二叉树的线索化和遍历
 * 
 * @author hellotong
 * @date 2020-08-29 9:31
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        // 创建节点
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node3 = new HeroNode(3, "lucy");
        HeroNode node6 = new HeroNode(6, "jack");
        HeroNode node8 = new HeroNode(8, "mary");
        HeroNode node10 = new HeroNode(10, "smith");
        HeroNode node14 = new HeroNode(14, "rose");
        /*HeroNode node18 = new HeroNode(18, "dik");
        HeroNode node20 = new HeroNode(20, "jerry");*/
        
        // 为二叉树建立关系
        root.setLeft(node3);
        root.setRight(node6);
        node3.setLeft(node8);
        node3.setRight(node10);
        node6.setLeft(node14);
        /*node6.setRight(node18);
        node14.setLeft(node20);*/
        
        // 设置二叉树的根节点
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        // 中序线索化
        threadedBinaryTree.infixThreadedNode();
        // 测试中序线索化是否成功，测试 node10 的前驱和后继节点是否为 3 和 1
        HeroNode left = node10.getLeft();
        HeroNode right = node10.getRight();
        System.out.println("node10 的前驱节点为：" + left);
        System.out.println("node10 的后继节点为：" + right);
        
        // 遍历中序线索二叉树
        System.out.println("遍历中序线索二叉树为：");
        threadedBinaryTree.infixThreadedList();

        // 前序线索化
        /*threadedBinaryTree.preThreadedNode();
        // 测试中序线索化是否成功，测试 node10 的前驱和后继节点是否为 8 和 6
        HeroNode left = node10.getLeft();
        HeroNode right = node10.getRight();
        System.out.println("node10 的前驱节点为：" + left);
        System.out.println("node10 的后继节点为：" + right);
        // 遍历中序线索二叉树
        System.out.println("遍历前序线索二叉树为：");
        threadedBinaryTree.preThreadedList();*/

        // 后序线索化
       /* threadedBinaryTree.postThreadedNode();
        // 测试中序线索化是否成功，测试 node10 的前驱和后继节点是否为 8 和 3
        HeroNode left = node10.getLeft();
        HeroNode right = node10.getRight();
        System.out.println("node10 的前驱节点为：" + left);
        System.out.println("node10 的后继节点为：" + right);
        // 遍历中序线索二叉树
        System.out.println("遍历前序线索二叉树为：");
        threadedBinaryTree.postThreadedList();*/
    }
}

/**
 * 定义一棵二叉树，实现线索化功能，即线索化二叉树
 */
class ThreadedBinaryTree {
    /**
     * 树的根节点
     */
    private HeroNode root;

    /**
     * 指向前驱节点，用于线索化，初始值为 null
     */
    private HeroNode pre;

    /**
     * 设置一棵二叉树的根节点
     *
     * @param root
     */
    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 重载后序线索化二叉树方法，内部直接传入根节点，方便调用
     */
    public void postThreadedNode() {
        postThreadedNode(this.root);
    }

    /**
     * 后序线索化二叉树
     * @param node
     */
    public void postThreadedNode(HeroNode node) {
        // 如果 node == null，无法线索化
        if (node == null) {
            return;
        }
        
        /* 线索化步骤
        1. 先线索化左子树
        2. 再线索化右子树
        3. 线索化当前节点
         */
        // 1. 先线索化左子树
        postThreadedNode(node.getLeft());
        // 2. 再线索化右子树
        postThreadedNode(node.getRight());

        // 3. 线索化当前节点
        // 线索化前驱节点
        // node.getLeft() == null，说明 node 的左指针域为空可以用来线索化
        if (node.getLeft() == null) {
            // 设置当前节点的 leftType 为 1 表示指向前驱节点
            node.setLeftType(1);
            // node 指向前驱节点，第一次线索化时，pre 为 null，即第一个元素的前驱节点为 null，正合题意
            node.setLeft(pre);
        }

        // 线索化后继节点，此操作是在第二次线索化时才开始进行的
        // 因为第一次线索化时，pre 还指向 null
        if (pre != null && pre.getRight() == null) {
            // 设置 node 的前驱节点 pre 的 leftType 为 1 表示指向后继节点
            pre.setRightType(1);
            // pre 为前驱节点，使用前驱节点指向当前节点
            pre.setRight(node);
        }

        // !!! 最重要的一步
        // 前驱节点下移
        pre = node;
    }

    /**
     * 遍历后序线索化二叉树 TODO
     */
    public void postThreadedList() {
        /*// 存储遍历过程中每个节点，初始值为根节点 root
        HeroNode node = root;

        // 只要当前节点不为空，就一直遍历
        while (node != null) {
            // 找到第一个 leftType == 1 的节点，第一个节点为 8（最左端节点）
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            // 第一次循环结束，node 指向的就是第一个节点
            // 输出
            System.out.println(node);

            // 判断 node 的右指针域是否指向后继节点，如果是就一直输出
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }

            // 否则 node 指向 node.right
            node = node.getRight();
        }*/
    }

    /**
     * 重载前序线索化二叉树方法，内部直接传入根节点，方便调用
     */
    public void preThreadedNode() {
        preThreadedNode(this.root);
    }

    /**
     * 前序线索化二叉树
     * @param node
     */
    public void preThreadedNode(HeroNode node) {
        // 如果 node == null，无法线索化
        if (node == null) {
            return;
        }
        
        /* 线索化步骤
        1. 线索化当前节点
        2. 先线索化左子树
        3. 再线索化右子树
         */
        // 1. 线索化当前节点
        // 线索化前驱节点
        // node.getLeft() == null，说明 node 的左指针域为空可以用来线索化
        if (node.getLeft() == null) {
            // 设置当前节点的 leftType 为 1 表示指向前驱节点
            node.setLeftType(1);
            // node 指向前驱节点，第一次线索化时，pre 为 null，即第一个元素的前驱节点为 null，正合题意
            node.setLeft(pre);
        }

        // 线索化后继节点，此操作是在第二次线索化时才开始进行的
        // 因为第一次线索化时，pre 还指向 null
        if (pre != null && pre.getRight() == null) {
            // 设置 node 的前驱节点 pre 的 leftType 为 1 表示指向后继节点
            pre.setRightType(1);
            // pre 为前驱节点，使用前驱节点指向当前节点
            pre.setRight(node);
        }

        // !!! 最重要的一步
        // 前驱节点下移
        /*if (node.getLeft() != null) {
            node = node.getLeft();
        } else {
            node = pre.getRight();
        }*/
        
        pre = node;
        
        // 2. 先线索化左子树
        if (node.getLeftType() == 0) {
            preThreadedNode(node.getLeft());
        }

        // 3. 再线索化右子树
        if (node.getRightType() == 0) {
            preThreadedNode(node.getRight());
        }
    }

    /**
     * 遍历前序线索化二叉树 TODO:增加数据，还有 BUG
     */
    public void preThreadedList() {
        // 存储遍历过程中每个节点，初始值为根节点 root
        HeroNode node = root;

        // 只要当前节点不为空，就一直遍历
        while (node != null) {
            // 先输出当前节点
            System.out.println(node);
            
            // 找到第一个 leftType == 1 的节点，第一个节点为 8（最左端节点）
            while (node.getLeftType() == 0) {
                node = node.getLeft();
                System.out.println(node);
            }
            // 第一次循环结束，node 指向的就是第一个节点
            

            // 判断 node 的右指针域是否指向后继节点，如果是就一直输出
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }

            // 否则 node 指向 node.right
            if (node.getLeftType() == 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
                
        }
    }

    /**
     * 重载中序线索化二叉树方法，内部直接传入根节点，方便调用
     */
    public void infixThreadedNode() {
        infixThreadedNode(this.root);
    }
    
    /**
     * 中序线索化二叉树
     * @param node
     */
    public void infixThreadedNode(HeroNode node) {
        // 如果 node == null，无法线索化
        if (node == null) {
            return;
        }
        
        /* 线索化步骤
        1. 先线索化左子树
        2. 线索化当前节点
        3. 再线索化右子树
         */
        // 1. 先线索化左子树
        infixThreadedNode(node.getLeft());
        
        // 2. 线索化当前节点
        // 线索化前驱节点
        // node.getLeft() == null，说明 node 的左指针域为空可以用来线索化
        if (node.getLeft() == null) {
            // 设置当前节点的 leftType 为 1 表示指向前驱节点
            node.setLeftType(1);
            // node 指向前驱节点，第一次线索化时，pre 为 null，即第一个元素的前驱节点为 null，正合题意
            node.setLeft(pre);
        }
        
        // 线索化后继节点，此操作是在第二次线索化时才开始进行的
        // 因为第一次线索化时，pre 还指向 null
        if (pre != null && pre.getRight() == null) {
            // 设置 node 的前驱节点 pre 的 leftType 为 1 表示指向后继节点
            pre.setRightType(1);
            // pre 为前驱节点，使用前驱节点指向当前节点
            pre.setRight(node);
        }
        
        // !!! 最重要的一步
        // 前驱节点下移
        pre = node;
        
        // 3. 再线索化右子树
        infixThreadedNode(node.getRight());
    }

    /**
     * 遍历中序线索化二叉树
     */
    public void infixThreadedList() {
        // 存储遍历过程中每个节点，初始值为根节点 root
        HeroNode node = root;
        
        // 只要当前节点不为空，就一直遍历
        while (node != null) {
            // 找到第一个 leftType == 1 的节点，第一个节点为 8（最左端节点）
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            // 第一次循环结束，node 指向的就是第一个节点
            // 输出
            System.out.println(node);
            
            // 判断 node 的右指针域是否指向后继节点，如果是就一直输出
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            
            // 否则 node 指向 node.right
            node = node.getRight();
        }
    }

    /**
     * 前序遍历
     */
    public void preOrderList() {
        // 根节点不为空
        if (this.root != null) {
            this.root.preOrderList();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrderList() {
        // 根节点不为空
        if (this.root != null) {
            this.root.infixOrderList();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 后序遍历
     */
    public void postOrderList() {
        // 根节点不为空
        if (this.root != null) {
            this.root.postOrderList();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 前序查找
     *
     * @param no 要查找的 no
     * @return 如果找到则返回，否则返回 null
     */
    public HeroNode preOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.root != null) {
            resNode = this.root.preOrderSearch(no);
        } else {
            System.out.println("二叉树为空，无法查找");
        }
        return resNode;
    }

    /**
     * 中序查找
     *
     * @param no 要查找的 no
     * @return 如果找到则返回，否则返回 null
     */
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.root != null) {
            resNode = this.root.infixOrderSearch(no);
        } else {
            System.out.println("二叉树为空，无法查找");
        }
        return resNode;
    }

    /**
     * 后序查找
     *
     * @param no 要查找的 no
     * @return 如果找到则返回，否则返回 null
     */
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.root != null) {
            resNode = this.root.postOrderSearch(no);
        } else {
            System.out.println("二叉树为空，无法查找");
        }
        return resNode;
    }

    /**
     * 删除编号为 no 的节点
     * 规定：
     * 1. 如果要删除的节点是叶子节点，则直接删除
     * 2. 如果要删除的节点是非叶子节点，则直接删除该子树
     *
     * @param no 要删除的英雄编号 no
     */
    public void deleteNode(int no) {
        if (this.root != null) {
            // 不是空树且 root 节点就是要删除的节点
            if (this.root.getNo() == no) {
                // 根节点置空
                root = null;
            } else {
                this.root.deleteNode(no);
            }
        } else {
            System.out.println("二叉树为空，无法删除");
        }
    }

    /**
     * 删除编号为 no 的节点
     * 规定如下:
     * 1. 如果要删除的节点是叶子节点，则直接删除
     * 2. 如果要删除的节点是非叶子节点，现在我们不希望将该非叶子节点为根节点的子树删除，需要指定规则, 假如
     * 3. 如果该非叶子节点 A 只有一个子节点 B，则子节点 B 替代节点 A
     * 4. 如果该非叶子节点 A 有左子节点 B 和右子节点 C，则让左子节点 B 替代节点 A
     *
     * @param no 要删除的英雄编号 no
     */
    public void deleteNode2(int no) {
        if (this.root == null) {
            System.out.println("二叉树为空，无法删除");
            return;
        }

        // 不是空树且 root 节点就是要删除的节点
        if (this.root != null && this.root.getNo() == no) {
            // 如果二叉树只有一个 root 节点
            if (this.root.getLeft() == null && this.root.getRight() == null) {
                this.root = null;
            } else if (this.root.getLeft() != null && this.root.getRight() == null) {
                // 如果根节点的左子节点不为空，右子节点为空
                // 将根节点设置为左子节点
                this.setRoot(this.root.getLeft());
            } else if (this.root.getLeft() == null && this.root.getRight() != null) {
                // 如果根节点的左子节点为空，右子节点不为空
                // 将根节点设置为右子节点
                this.setRoot(this.root.getRight());
            } else if (this.root.getRight() != null && this.root.getRight() != null) {
                // 如果根节点的左子节点不为空，右子节点不为空

                // 如果根节点的左子节点的右子节点不为空，则规定将其挂在二叉树的最左节点的 left 上
                // 做法不唯一，按实际情况规定
                if (this.root.getLeft().getRight() != null) {
                    // 遍历找到二叉树的最左节点
                    HeroNode temp = this.root.getLeft();
                    while (temp.getLeft() != null) {
                        temp = temp.getLeft();
                    }

                    // 循环结束，temp 指向的就是最左节点
                    temp.setLeft(this.root.getLeft().getRight());
                }
                // 将根节点设置为左子节点
                this.root.getLeft().setRight(this.root.getRight());
                this.setRoot(this.root.getLeft());
            }
        } else if (this.root.getNo() != no){
            this.root.deleteNode2(no);
        }
    }

    /**
     * 修改英雄编号为 no 的信息
     * @param no 要修改的英雄编号
     * @return 修改成功返回 true，否则返回 false
     */
    public boolean modifyNode(int no) {
        if (this.root != null) {
            return this.root.modifyNode(no);
        } else {
            System.out.println("二叉树为空，无法修改");
            return false;
        }
    }
}

/**
 * 定义 HeroNode 节点
 */
class HeroNode {
    /**
     * 英雄编号
     */
    private int no;

    /**
     * 英雄姓名
     */
    private String name;

    /**
     * 指向左子节点
     */
    private HeroNode left;

    /**
     * 指向右子节点
     */
    private HeroNode right;

    /**
     * 如果 lefType == 0 表示指向左子树，如果 leftType == 1 则表示指向指向前驱节点
     * 如果 rightType == 0 表示指向右子树，如果 rightType == 1 则表示指向后继节点
     * 默认值为 0 表示指向子树，在线索化时进行修改具体值
     */
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    /**
     * 前序遍历
     * 1. 先输出当前节点（初始时候为 root 节点）
     * 2. 如果当前节点的左子节点不为空，则递归继续前序遍历
     * 3. 如果当前节点的右子节点不为空，则递归继续前序遍历
     */
    public void preOrderList() {
        // 初始时候为 root 节点，所以 this 肯定不为空
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrderList();
        }
        if (this.right != null) {
            this.right.preOrderList();
        }
    }

    /**
     * 中序遍历
     * 1. 如果当前节点的左子节点不为空，则递归继续中序遍历
     * 2. 输出当前节点
     * 3. 如果当前节点的右子节点不为空，则递归继续中序遍历
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

    /**
     * 后序遍历
     * 1. 如果当前节点的左子节点不为空，则递归继续后序遍历
     * 2. 如果当前节点的右子节点不为空，则递归继续后序遍历
     * 3. 输出当前节点
     */
    public void postOrderList() {
        if (this.left != null) {
            this.left.postOrderList();
        }
        if (this.right != null) {
            this.right.postOrderList();
        }
        System.out.println(this);
    }

    /**
     * 前序查找编号为 no 的英雄
     * 1. 先比较当前节点的 no 和查找的 no 是否相等
     * 2. 如果相等，则返回
     * 如果不相等，则判断当前节点的左子节点是否为空
     * 如果不为空，则递归继续前序查找
     * 如果左递归前序查找，找到了节点，则返回
     * 如果左递归前序查找，没找到节点，则判断当前节点的右子节点是否为空
     * 如果不为空，则递归继续前序查找
     * 如果右递归前序查找，找到了节点，则返回
     * 如果左递归前序查找，没找到节点，则返回 null
     *
     * @param no 要查找的 no
     * @return 如果找到则返回 HeroNode，否则返回 null
     */
    public HeroNode preOrderSearch(int no) {
        // 先判断当前节点的 no 和查找的 no 是否相等
        if (this.no == no) {
            return this;
        }

        // 用于存放查询结果
        HeroNode resNode = null;
        // 如果不相等，则判断当前节点的左子节点是否为空
        if (this.left != null) {
            // 如果不为空，则递归继续前序查找
            resNode = this.left.preOrderSearch(no);
        }

        // 判断 resNode 是否为空，即是否找到
        // 如果左递归前序查找，找到了节点，则返回
        if (resNode != null) {
            return resNode;
        }

        // 如果左递归前序查找，没找到节点
        // 则判断当前节点的右子节点是否为空，如果不为空，则递归继续前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }

        // 最后不需要判断了，直接返回 resNode，如果找到了，resNode 不为空，如果没找到，则 resNode 初始值就是空
        return resNode;
    }

    /**
     * 中序查找编号为 no 的英雄
     * 1. 先判断当前节点的左子节点是否为空
     * 2. 如果不为空，则递归继续中序查找
     * 如果左递归中序查找，找到了节点，则返回
     * 如果左递归前序查找，没找到节点，则比较当前节点的 no 和要查找的 no 是否相等
     * 如果相等，则返回
     * 如果不相等，则判断当前节点的右子节点是否为空
     * 如果不为空，则右递归继续中序查找
     * 如果右递归中序查找，找到了节点，则返回
     * 如果右递归中序查找，没找到节点，则返回 null
     *
     * @param no 要查找的 no
     * @return 如果找到则返回 HeroNode，否则返回 null
     */
    public HeroNode infixOrderSearch(int no) {
        // 用于存放查询结果
        HeroNode resNode = null;
        // 先判断当前节点的左子节点是否为空
        if (this.left != null) {
            // 如果不为空，则递归继续中序查找
            resNode = this.left.infixOrderSearch(no);
        }

        // 如果左递归中序查找，找到了节点，则返回
        if (resNode != null) {
            return resNode;
        }

        // 如果左递归前序查找，没找到节点，则比较当前节点的 no 和要查找的 no 是否相等
        // 如果相等，则返回
        if (this.no == no) {
            return this;
        }

        // 如果不相等，则判断当前节点的右子节点是否为空
        if (this.right != null) {
            // 如果不为空，则右递归继续中序查找
            resNode = this.right.infixOrderSearch(no);
        }

        // 最后不需要判断了，直接返回 resNode，如果找到了，resNode 不为空，如果没找到，则 resNode 初始值就是空
        return resNode;
    }

    /**
     * 后序查找编号为 no 的英雄
     * 1. 先判断当前节点的左子节点是否为空
     * 2. 如果不为空，则递归继续后序查找
     * 如果左递归后序查找，找到了节点，则返回
     * 如果左递归后序查找，没找到节点，则判断当前节点的右子节点是否为空
     * 如果不为空，则递归继续后序查找
     * 如果右递归后序查找，找到了节点，则返回
     * 如果右递归后序查找，没找到节点，则比较当前节点的 no 和要查找的 no 是否相等
     * 如果相等，则返回
     * 如果不相等，则返回 null
     *
     * @param no 要查找的 no
     * @return 如果找到则返回 HeroNode，否则返回 null
     */
    public HeroNode postOrderSearch(int no) {
        // 用于存放查询结果
        HeroNode resNode = null;
        // 先判断当前节点的左子节点是否为空
        if (this.left != null) {
            // 如果不为空，则递归继续后序查找
            resNode = this.left.postOrderSearch(no);
        }

        // 如果左递归后序查找，找到了节点，则返回
        if (resNode != null) {
            return resNode;
        }

        // 如果左递归后序查找，没找到节点，则判断当前节点的右子节点是否为空
        if (this.right != null) {
            // 如果不为空，则递归继续后序查找
            resNode = this.right.postOrderSearch(no);
        }

        // 如果右递归后序查找，找到了节点，则返回
        if (resNode != null) {
            return resNode;
        }

        // 如果右递归后序查找，没找到节点，则比较当前节点的 no 和要查找的 no 是否相等
        // 如果相等，则返回
        if (this.no == no) {
            return this;
        }

        // 最后不需要判断了，直接返回 resNode，如果找到了，resNode 不为空，如果没找到，则 resNode 初始值就是空
        return resNode;
    }

    /**
     * 删除编号为 no 的节点
     * 规定：
     * 1. 如果要删除的节点是叶子节点，则直接删除
     * 2. 如果要删除的节点是非叶子节点，则直接删除该子树
     * 思路：
     * 1. 首先在 BinaryTree 的删除节点方法中，判断 root 是否为空且 root 是否即为要删除的节点
     * 如果是，则直接将整棵树置空
     * 2. 注意：因为二叉树是单向的，所以要完成删除节点的操作，则判断的应该是当前节点的子节点，才能够删除成功
     * 3. 如果当前节点的左子节点不为空，且 this.left.no == no，则将 this.left = null，并返回，结束递归删除
     * 4. 如果当前节点的右子节点不为空，且 this.right.no == no，则将 this.right = null，并返回，结束递归删除
     * 5. 如果第 3 和第 4 步没有删除成功，则递归向左子树查找，如果找到则删除
     * 6. 如果第 4 步没有删除成功，则递归向右子树查找，如果找到则删除
     *
     * @param no 要删除的英雄编号 no
     */
    public void deleteNode(int no) {
        // 如果当前节点的左子节点不为空，且 this.left.no == no，则将 this.left = null，并返回，结束递归删除
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }

        // 如果当前节点的右子节点不为空，且 this.right.no == no，则将 this.right = null，并返回，结束递归删除
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        // 递归向左子树查找，如果找到则删除
        if (this.left != null) {
            this.left.deleteNode(no);
        }

        // 递归向右子树查找，如果找到则删除
        if (this.right != null) {
            this.right.deleteNode(no);
        }
    }

    /**
     * 删除编号为 no 的节点
     * 规定如下:
     * 1. 如果要删除的节点是叶子节点，则直接删除
     * 2. 如果要删除的节点是非叶子节点，现在我们不希望将该非叶子节点为根节点的子树删除，需要指定规则, 假如
     * 3. 如果该非叶子节点 A 只有一个子节点 B，则子节点 B 替代节点 A
     * 4. 如果该非叶子节点 A 有左子节点 B 和右子节点 C，则让左子节点 B 替代节点 A
     *
     * @param no 要删除的英雄编号 no
     */
    public void deleteNode2(int no) {
        // 如果要删除的节点是叶子节点，则直接删除
        // this.left 为叶子节点且就是要删除的节点
        if (this.left != null && this.left.left == null && this.left.right == null && this.left.no == no) {
            this.left = null;
            return;
        }
        // this.right 为叶子节点且就是要删除的节点
        if (this.right != null && this.right.left == null && this.right.right == null && this.right.no == no) {
            this.right = null;
            return;
        }

        // 如果该非叶子节点 A 只有一个子节点 B，则子节点 B 替代节点 A
        // 如果 B 为 A 的左子节点
        if (this.left != null && this.left.no == no && this.left.left != null && this.left.right == null) {
            this.left = this.left.left;
            return;
        }
        if (this.right != null && this.right.no == no && this.right.left != null && this.right.right == null) {
            this.right = this.right.left;
            return;
        }

        // 如果 B 为 A 的右子节点
        if (this.left != null && this.left.no == no && this.left.left == null && this.left.right != null) {
            this.left = this.left.right;
            return;
        }
        if (this.right != null && this.right.no == no && this.right.left == null && this.right.right != null) {
            this.right = this.right.right;
            return;
        }

        // 如果该非叶子节点 A 有左子节点 B 和右子节点 C，则让左子节点 B 替代节点 A
        if (this.left != null && this.left.no == no && this.left.left != null && this.left.right != null) {
            this.left.left.right = this.right.right;
            this.left = this.left.left;
            return;
        }
        if (this.right != null && this.right.no == no && this.right.left != null && this.right.right != null) {
            this.right.left.right = this.right.right;
            this.right = this.right.left;
            return;
        }

        // 递归向左子树查找，如果找到则删除
        if (this.left != null) {
            this.left.deleteNode2(no);
        }

        // 递归向右子树查找，如果找到则删除
        if (this.right != null) {
            this.right.deleteNode2(no);
        }
    }

    /**
     * 修改英雄编号为 no 的信息
     * @param no 要修改的英雄编号
     * @return 修改成功返回 true，否则返回 false
     */
    public boolean modifyNode(int no) {
        // 假设使用前序查找
        HeroNode resNode = this.preOrderSearch(no);
        Scanner scanner = new Scanner(System.in);
        if (resNode != null) {
            System.out.println("请输入要修改的英雄的姓名：");
            String name = scanner.next();
            resNode.setName(name);
            return true;
        } else {
            return false;
        }
    }

    public int getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
