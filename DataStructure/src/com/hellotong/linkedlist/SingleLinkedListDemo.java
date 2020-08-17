package com.hellotong.linkedlist;

import java.util.Scanner;
import java.util.Stack;

/**
 * 使用带 head 头节点的单向链表实现 – 水浒英雄排行榜管理，完成对英雄人物的增删改查操作
 * 以及几道面试题
 * 
 * @author hellotong
 * @date 2020-08-15 13:21
 */
public class SingleLinkedListDemo {
    
    public static void main(String[] args) {
        // 初始化节点数据
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "智多星", "吴用");
        HeroNode hero4 = new HeroNode(4, "公孙胜", "入云龙");
        
        // 创建链表 SingleLinkedList
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        
        // 添加数据到单链表的尾部
        /*singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);*/

        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        // 添加重复数据
        singleLinkedList.addByOrder(hero2);
        
        // 遍历单链表
        singleLinkedList.list();

        // 修改节点的信息
        System.out.println("修改后的链表为：");
        HeroNode newHeroNode = new HeroNode(4, "公孙胜2", "入云龙2");
        singleLinkedList.update(newHeroNode);
        singleLinkedList.list();
        
        // 删除节点
        System.out.println("修改后的链表为：");
        // singleLinkedList.delete(2);
        // singleLinkedList.delete(1);
        // singleLinkedList.delete(4);
        // singleLinkedList.delete(3);
        singleLinkedList.list();
        
        // 获取单链表中有效节点的个数
        System.out.println("链表中有效节点的个数为：" + getLength(singleLinkedList.getHead()));
        
        // 取单链表中倒数第 k 个节点，检查 k 的范围
        /*int k;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入 k 的值: ");
            k = scanner.nextInt();
            if (k > getLength(singleLinkedList.getHead()) || k <= 0) {
                System.out.println("输入的 k 的范围不合法，请重新输入");
            } else {
                HeroNode node = findLastIndexNode(singleLinkedList.getHead(), k);
                System.out.println(node);
                break;
            }
        }

        scanner.close();*/

        System.out.println("反转后的链表为：");
        // 单链表反转 1
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();
        
        // 单链表反转 2
        /*HeroNode newHead = reverseList2(singleLinkedList.getHead());
        HeroNode temp = newHead.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }*/
        
        // 单链表逆序打印
        System.out.println("链表逆序打印：");
        reversePrint(singleLinkedList.getHead());
    }
    
    /**
     * 百度面试题：逆序打印链表
     * 思路：
     * 方式一：先反转单链表，然后遍历实现逆序打印，调用 reverseList 即可
     * 方式二：利用栈这个数据结构，将各个节点压入栈中，然后利用栈先进后出的特点，实现逆序打印的效果
     * @param head
     */
    public static void reversePrint(HeroNode head) {
        // 创建一个栈
        Stack<HeroNode> stack = new Stack<>();
        
        // 判断单链表是否为空
        if (head.next == null) {
            return;
        }
        
        // 如果单链表只有一个元素，直接打印
        if (head.next.next == null) {
            System.out.println(head.next);
        }

        // 如果单链表不止一个元素，逆序打印
        // 遍历单链表，将每个节点压入栈中
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        
        // 出栈实现逆序打印
        while (stack.size() > 0) {
            HeroNode popNode = stack.pop();
            System.out.println(popNode);
        }
    }

    /**
     * 单链表反转
     * 思路：
     * 1. 先定义一个节点 reverseHead = new HeroNode()
     * 2. 从头到尾遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表 reverseHead 的最前端
     * 3. 原来链表的 head.next = reverseHead.next
     *
     * @param head 链表的头节点
     * @return 反转后的链表
     */
    public static void reverseList(HeroNode head) {
        // 如果链表为空或只有一个元素，则不需要反转
        if (head.next == null || head.next.next == null) {
            return ;
        }

        // 定义一个新的节点 reverseHead
        HeroNode reverseHead = new HeroNode(0, "", "");
        // 定义辅助指针遍历链表，并获取每一个节点
        HeroNode cur = head.next;
        // 记录 cur 节点的下一个节点，因为取下 cur 之后，cur 之后的就找不到了
        HeroNode curNext = null;
        
        // 遍历原链表，取下每一个节点，并放在新的链表 reverseHead 的最前端
        while (cur != null) {
            // 先保存 cur 节点的下一个节点
            curNext = cur.next;
            
            // 头插法插入
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            
            // 指针后移
            cur = curNext;
        }
        
        // 最后
        head.next = reverseHead.next;
    }

    /**
     * 腾讯面试题：单链表反转
     * 思路：
     * 1. 创建一个带头节点的新链表
     * 2. 遍历传入的链表，每遍历一个节点就使用头插法将该节点插入到新链表中
     * 3. 遍历完了，新链表也建成了
     * 4. 新链表即反转后的链表，返回即可
     * 
     * @param head 链表的头节点
     * @return 反转后的链表
     */
    public static HeroNode reverseList2(HeroNode head) {
        // 判断链表是否为空
        if (head.next == null) {
            return null;
        }
        
        // 创建一个新链表
        HeroNode newHead = new HeroNode(0, "", "");
        HeroNode newFirstNode = null;
        newHead.next = newFirstNode;

        HeroNode cur = head.next;
        while (cur != null) {
            HeroNode temp = new HeroNode();
            temp.no = cur.no;
            temp.name = cur.name;
            temp.nickName = cur.nickName;
            // 表示这是第一个节点，也是新链表的最后一个节点
            /*if (temp == head.next) {
                temp.next = null;
            }*/
            
            temp.next = newFirstNode;
            newHead.next = temp;
            newFirstNode = temp;
            
            cur = cur.next;
        }
        
        return newHead;
    }
    
    /**
     * 新浪面试题：获取单链表中倒数第 k 个节点
     * 思路：
     * 1. 编写一个方法，接收 head 节点，同时接收一个 index
     * 2. index 表示是倒数第 index 个节点
     * 3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
     * 4. 得到 size 后，我们从链表的第一个开始遍历 (size - index) 个，就可以得到
     * 5. 如果找到了，则返回该节点，否则返回 null
     * @param head 链表的头节点
     * @param index 倒数第几个
     * @return 找到则返回节点，否则返回 null
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        // 判断如果链表为空，返回 null，没找到
        if (head.next == null) {
            return null;
        }
        
        // 第一个遍历得到链表的长度（有效节点个数）
        int size = getLength(head);
        // 第二次遍历 size - index 个就是我们倒数第 k 个节点
        // 定义辅助指针，for 循环定位倒数第 k 个节点
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        
        return cur;
    }
    
    /**
     * 获取单链表有效节点的个数
     * @param head 单链表的头节点
     * @return 有效节点的个数
     */
    public static int getLength(HeroNode head) {
        // 链表为空
        if (head.next == null) {
            return 0;
        }
        
        HeroNode temp = head;
        // 单链表有效节点的个数
        int length = 0;
        while (temp.next != null) {
            length++;
            temp = temp.next;
        }
        
        return length;
    }
}

/**
 * 定义单向链表 SingleLinkedList，管理英雄
 */
class SingleLinkedList {

    /**
     * 初始化链表的头节点，不存放数据
     */
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单链表尾部
     * @param heroNode 要添加的节点
     */
    public void add(HeroNode heroNode) {
        // 1. 找到单链表的最后一个元素
        // 借助一个辅助指针遍历单链表寻找
        HeroNode temp = head;
        // 当前元素的 next 域不为空继续查找，直到找到最后一个元素
        while (temp.next != null) {
            temp = temp.next;
        }
        
        // 退出循环，temp 就是单链表最后一个元素
        // 2. 将元素添加到单链表末尾
        temp.next = heroNode;
    }

    /**
     * 将要添加的节点按照编号从小到大的顺序插入到单链表中
     * 思路：
     * 1. 找到要插入位置的下一个元素，比较编号，如果要添加节点的编号小于下一个元素的编号，则插入，否则继续遍历
     * 2. 如果该编号存在，则添加失败，给出提示信息。
     * @param heroNode 要添加的节点
     */
    public void addByOrder(HeroNode heroNode) {
        // 借助一个辅助指针遍历单链表寻找
        HeroNode temp = head;
        // 标记编号是否存在，默认值为不存在
        boolean flag = false;
        
        while (true) {
            if (temp.next != null) {
                // 找到位置了
                if (heroNode.no < temp.next.no) {
                    break;
                } else if (heroNode.no == temp.next.no) {
                // 编号存在，插入失败
                    flag = true;
                    break;
                }
            } else {
                // 说明 temp 已经在链表的最后
                break;
            }
            
            // temp 后移，遍历单链表
            temp = temp.next;
        }
        
        // 编号存在，插入失败
        if (flag) {
            System.out.println("要添加英雄的编号 " + heroNode.no + " 已经存在");
        } else {
        // 插入成功
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 按照英雄的编号修改英雄的信息（姓名和昵称），英雄的编号不能修改，如果可以修改编号就相当于添加了
     * 思路：
     * 1. 查找要修改的英雄的编号是否存在
     * 2. 存在即可修改，否则无法修改
     * @param newHeroNode 要修改的节点
     */
    public void update(HeroNode newHeroNode) {
        // 借助一个辅助指针遍历单链表寻找
        HeroNode temp = head.next;
        // 标记编号是否存在
        boolean flag = false;

        if (temp == null) {
            System.out.println("链表为空");
            return;
        }
        
        while (true) {
            // 链表遍历完，说明没有找到
            if (temp == null) {
                break;
            }
            
            // 找到了
            if (newHeroNode.no == temp.no) {
                flag = true;
                break;
            }

            temp = temp.next;
        }
        
        // 找到了
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.println("要修改的英雄的编号" + newHeroNode.no + "不存在，无法修改");
        }
    }

    /**
     * 按照英雄编号删除英雄节点
     * 思路：
     * 1. 如果要删除的英雄编号存在，则找到待删除节点的前一个位置 temp，然后
     * temp.next = temp.next.next，待删除的节点就会被垃圾回收机制回收
     * 2. 要删除的英雄编号不存在，删除失败，给出提示
     * @param no 要删除的英雄编号
     */
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        
        HeroNode temp = head;
        // 标记是否找到待删除的节点
        boolean flag = false;
        
        while (true) {
            // 链表已经遍历完
            if (temp.next == null) {
                break;
            }
            
            // 找到了，temp 即为要删除节点的前一个节点
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            
            temp = temp.next;
        }
        
        // 找到了 no，可以删除
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("要删除的英雄的编号" + no + "不存存在，无法删除");
        }
    }
    
    /**
     * 遍历单链表
     */
    public void list() {
        // 判断单链表是否为空
        if (isEmpty()) {
            System.out.println("链表为空");
            return;
        }
        
        // 定义一个辅助指针用于遍历链表
        // 初始值指向单链表第一个元素
        HeroNode temp = head.next;
        
        // 当前元素不为空进入循环
        while (temp != null) {
            System.out.println(temp);
            
            // 指针后移
            temp = temp.next;
        }
    }

    /**
     * 判断单链表是否为空
     * @return true or false
     */
    public boolean isEmpty() {
        return head.next == null;
    }
}

/**
 * 英雄节点
 * 成员变量使用 public 修饰，方便操作
 */
class HeroNode {
    // 数据域
    /**
     * 英雄编号
     */
    public int no;

    /**
     * 名字
     */
    public String name;

    /**
     * 绰号
     */
    public String nickName;
    
    // 指针域
    /**
     * 指向下一个节点
     */
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
        
        // 没有初始化 next，则 next 默认为 null
    }

    public HeroNode() {
    }

    /**
     * 重写 toString 方法，不打印 next，因为会嵌套打印
     * @return 打印 HeroNode
     */
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' + 
                '}';
    }
}
