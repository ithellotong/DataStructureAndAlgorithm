package com.hellotong.tree;

import java.util.Scanner;

/**
 * 二叉树的增删改查
 *
 * @author hellotong
 * @date 2020-08-28 9:32
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 先创建一棵二叉树
        BinaryTree binaryTree = new BinaryTree();

        // 创建树节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊乂");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        HeroNode node6 = new HeroNode(6, "李逵");

        // 手动创建二叉树，即建立节点关系，之后会使用递归创建
        // 设置二叉树的根节点
        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);

        // 前序遍历
        System.out.println("前序遍历");
        // 1, 2, 3, 5, 4
        binaryTree.preOrderList();

        // 中序遍历
        System.out.println("中序遍历");
        // 2, 1, 5, 3, 4
        binaryTree.infixOrderList();

        // 后序遍历
        System.out.println("后序遍历");
        // 2, 5, 4, 3, 1
        binaryTree.postOrderList();

        // 前序查找
        System.out.println("前序查找");
        int no = 5;
        HeroNode resNode = binaryTree.preOrderSearch(no);
        // 找到了
        if (resNode != null) {
            System.out.println("找到了，英雄信息为：" + resNode);
        } else {
            System.out.println("没找到 no = " + no + " 的英雄");
        }

        // 中序查找
        System.out.println("中序查找");
        no = 5;
        resNode = binaryTree.infixOrderSearch(no);
        // 找到了
        if (resNode != null) {
            System.out.println("找到了，英雄信息为：" + resNode);
        } else {
            System.out.println("没找到 no = " + no + " 的英雄");
        }

        // 后序查找
        System.out.println("前序查找");
        no = 5;
        resNode = binaryTree.postOrderSearch(no);
        // 找到了
        if (resNode != null) {
            System.out.println("找到了，英雄信息为：" + resNode);
        } else {
            System.out.println("没找到 no = " + no + " 的英雄");
        }

        /*System.out.println("删除前，前序遍历:");
        binaryTree.preOrderList();
        binaryTree.deleteNode(5);
        System.out.println("删除后，前序遍历:");
        binaryTree.preOrderList();*/

        node2.setRight(node6);
        System.out.println("删除前，前序遍历:");
        binaryTree.preOrderList();
        binaryTree.deleteNode2(4);
        System.out.println("删除后，前序遍历:");
        binaryTree.preOrderList();

        System.out.println("修改前，前序遍历:");
        binaryTree.preOrderList();
        boolean result = binaryTree.modifyNode(2);
        if (result) {
            System.out.println("修改成功");
            System.out.println("修改后，前序遍历:");
            binaryTree.preOrderList();    
        } else {
            System.out.println("修改失败，不存在编号为 no = " + no + " 的英雄");
        }
    }
}

/**
 * 定义一棵二叉树
 */
class BinaryTree {
    /**
     * 树的根节点
     */
    private HeroNode root;

    /**
     * 设置一棵二叉树的根节点
     *
     * @param root
     */
    public void setRoot(HeroNode root) {
        this.root = root;
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
 * 树节点 / 英雄节点
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

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
