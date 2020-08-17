package com.hellotong.linkedlist;

/**
 * 使用带 head 头节点的双向链表实现 – 水浒英雄排行榜管理，完成对英雄人物的增删改查操作
 * 参照单链表的实现，不同之处进行修改
 * 
 * @author hellotong
 * @date 2020-08-17 12:40
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        // 创建双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        // 创建数据节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "智多星", "吴用");
        HeroNode2 hero4 = new HeroNode2(4, "公孙胜", "入云龙");
        // 添加节点到链表的尾部
        // doubleLinkedList.add(hero1);
        // doubleLinkedList.add(hero2);
        // doubleLinkedList.add(hero3);
        // doubleLinkedList.add(hero4);
        
        // 按照编号从小到大的顺序插入到链表中
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero2);
        
        // 遍历
        doubleLinkedList.list();
        
        // 修改
        HeroNode2 newHeroNode = new HeroNode2(4, "关胜", "大刀");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表");
        doubleLinkedList.list();
        
        // 删除
        System.out.println("删除后的链表");
        doubleLinkedList.delete(1);
        doubleLinkedList.list();
    }
}

/**
 * 定义双向链表 DoubleLinkedList，管理英雄
 */
class DoubleLinkedList {
    /**
     * 初始化链表的头节点，不存放数据
     */
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 添加节点到双向链表尾部
     * 添加操作与单链表一样
     * @param heroNode 要添加的节点
     */
    public void add(HeroNode2 heroNode) {
        // 1. 找到双向链表的最后一个元素
        // 借助一个辅助指针遍历链表寻找
        HeroNode2 temp = head;
        // 当前元素的 next 域不为空继续查找，直到找到最后一个元素
        while (temp.next != null) {
            temp = temp.next;
        }

        // 退出循环，temp 就是单链表最后一个元素
        // 2. 将元素添加到单链表末尾
        // temp.next = heroNode; // 单向链表添加
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    /**
     * 将要添加的节点按照编号从小到大的顺序插入到链表中
     * 思路：
     * 1. 找到要插入位置上的元素，比较编号，如果要添加节点的编号小于该元素的编号，则插入，否则继续往后遍历
     * 2. 如果该编号存在，则添加失败，给出提示信息。
     * @param heroNode 要添加的节点
     */
    public void addByOrder(HeroNode2 heroNode) {
        // 借助一个辅助指针遍历单链表寻找
        HeroNode2 temp = head.next;
        // 标记编号是否存在，默认值为不存在
        boolean flag = false;
        
        // 链表为空时，直接调用 add 添加第一个元素
        if (head.next == null) {
            add(heroNode);
            return;
        }
        
        while (true) {
            if (temp != null) {
                // 找到位置了
                if (heroNode.no < temp.no) {
                    break;
                } else if (heroNode.no == temp.no) {
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
            heroNode.next = temp;
            heroNode.pre = temp.pre;
            temp.pre.next = heroNode;
            temp.pre = heroNode;
        }
    }

    /**
     * 按照英雄编号删除英雄节点
     * 思路：
     * 1. 如果要删除的英雄编号存在，则找到待删除节点 temp，然后
     * temp.next.pre = temp.pre;
     * temp.pre = temp.next;
     * 待删除的节点就会被垃圾回收机制回收
     * 2. 要删除的英雄编号不存在，删除失败，给出提示
     * @param no 要删除的英雄编号
     */
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        // HeroNode temp = head; // 单链表
        // 直接从第一个节点开始找，区别与单链表
        HeroNode2 temp = head.next; 
        // 标记是否找到待删除的节点
        boolean flag = false;

        while (true) {
            // 链表已经遍历完
            if (temp == null) {
                break;
            }

            // 找到了，temp 即为要删除节点
            if (temp.no == no) {
                flag = true;
                break;
            }

            temp = temp.next;
        }

        // 找到了 no，可以删除
        if (flag) {
            // temp.next = temp.next.next; // 单链表
            // 这条语句可能会引起空指针异常
            // 当 temp 为最后一个节点，则 temp.next 为空，temp.next.pre 就出现了空指针异常
            // 所有需要做判断
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
            
            temp.pre.next = temp.next;
        } else {
            System.out.println("要删除的英雄的编号" + no + "不存存在，无法删除");
        }
    }

    /**
     * 按照英雄的编号修改英雄的信息（姓名和昵称），英雄的编号不能修改，如果可以修改编号就相当于添加了
     * 思路：
     * 1. 查找要修改的英雄的编号是否存在
     * 2. 存在即可修改，否则无法修改
     * @param newHeroNode 要修改的节点
     */
    public void update(HeroNode2 newHeroNode) {
        // 借助一个辅助指针遍历单链表寻找
        HeroNode2 temp = head.next;
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
     * 遍历单链表
     */
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        // 定义一个辅助指针用于遍历链表
        // 初始值指向链表第一个元素
        HeroNode2 temp = head.next;

        // 当前元素不为空进入循环
        while (temp != null) {
            System.out.println(temp);

            // 指针后移
            temp = temp.next;
        }
    }
}

/**
 * 英雄节点
 * 成员变量使用 public 修饰，方便操作
 */
class HeroNode2 {
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

    // 指针域，默认值都为 null
    /**
     * 指向下一个节点
     */
    public HeroNode2 next;

    /**
     * 指向前一个节点
     */
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;

        // 没有初始化 next，则 next 默认为 null
    }

    public HeroNode2() {
    }

    /**
     * 重写 toString 方法
     * @return 打印 HeroNode2
     */
    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
