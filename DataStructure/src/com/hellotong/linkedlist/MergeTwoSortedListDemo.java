package com.hellotong.linkedlist;

/**
 * 练习：合并两个有序的单链表，合并之后的链表依然有序
 * 
 * @author hellotong
 * @date 2020-08-17 10:00
 */
public class MergeTwoSortedListDemo {
    public static void main(String[] args) {
        // 创建两个链表 SingleLinkedList
        SortedSingleLinkedList list = new SortedSingleLinkedList();
        SortedSingleLinkedList list2 = new SortedSingleLinkedList();
        
        // 添加数据到链表 1
        Node node1 = new Node(1, "宋江", "及时雨");
        Node node3 = new Node(3, "智多星", "吴用");
        Node node4 = new Node(4, "公孙胜", "入云龙");
        Node node6 = new Node(6, "林冲", "豹子头");
        Node node7 = new Node(7, "霹雳火", "秦明");
        list.addByOrder(node1);
        list.addByOrder(node6);
        list.addByOrder(node3);
        list.addByOrder(node4);
        list.addByOrder(node7);

        // 添加数据到链表 2
        Node node2 = new Node(2, "卢俊义", "玉麒麟");
        Node node5 = new Node(5, "关胜", "大刀");
        list2.addByOrder(node5);
        list2.addByOrder(node2);

        System.out.println("合并前的链表 1：");
        list.list();
        System.out.println("合并前的链表 2：");
        list2.list();
        
        // 合并
        System.out.println("合并后的新链表：");
        Node newHead = MergeTwoSortedList2(list.getHead(), list2.getHead());
        Node temp = newHead.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 合并两个有序单链表为一个新的有序单链表
     * 思路：
     * 1. 如果两个链表都不为空，先分别找出两个链表中的编号最小的节点，再比较二者的编号更小的节点将其添加到新链表中。
     *    如果最小的编号相等则依次添加（这里可以设置其他的比较条件）。
     * 2. 再寻找的过程中如果某一个链表遍历完了，则直接将另一个链表中剩下的节点添加到新链表中即可。
     * 3. 返回新链表的头节点
     * @param head 有序单链表 1 的头节点
     * @param head2 有序单链表 2 的头节点
     * @return 返回合并后的有序单链表的头节点
     */
    public static Node MergeTwoSortedList(Node head, Node head2) {
        // 先判断传入的链表是否为空
        if (head.next == null && head2.next == null) {
            return null;
        } else if (head.next == null && head2.next != null) {
            return head2;
        } else if (head.next != null && head2.next == null){
            return head;
        }
        
        // 两个链表都不为空
        // head.next != null && head2.next != null
        
        // 定义一个新链表的头节点
        Node newHead = new Node(0, "", "");
        // 定义一个辅助指针指向新链表的尾节点，初始值指向头节点
        Node newTail = newHead;
        // 定义辅助指针用于遍历链表
        Node cur = head.next;
        Node cur2 = head2.next;
        // 记录当前节点的下一个节点
        Node curNext;
        Node cur2Next;
        
        while (true) {
            // 两个链表都没有遍历完
            if (cur != null && cur2 != null) {
                // cur 为最小节点，则添加 cur
                if (cur.no < cur2.no) {
                    // 记录当前节点的下一个节点
                    curNext = cur.next;
                    // 将 cur 从原链表中取下来加到新链表的末尾
                    cur.next = newTail.next;
                    newTail.next = cur;
                    newTail = cur;
                    
                    // cur 后移
                    cur = curNext;
                } else if (cur.no > cur2.no) {
                // cur2 为最小节点，则添加 cur2
                    // 记录当前节点的下一个节点
                    cur2Next = cur2.next;
                    // 将 cur2 从原链表中取下来加到新链表的末尾
                    cur2.next = newTail.next;
                    newTail.next = cur2;
                    newTail = cur2;
                    
                    // cur2 后移
                    cur2 = cur2Next;
                } else if (cur.no == cur2.no) {
                // 两个都是最小节点，则先添加 cur，后添加 cur2
                    // 记录当前节点的下一个节点
                    curNext = cur.next;
                    // 将 cur 从原链表中取下来加到新链表的末尾
                    cur.next = newTail.next;
                    newTail.next = cur;
                    newTail = cur;
                    
                    // cur 后移
                    cur = curNext;

                    // 记录当前节点的下一个节点
                    cur2Next = cur2.next;
                    // 将 cur2 从原链表中取下来加到新链表的末尾
                    cur2.next = newTail.next;
                    newTail.next = cur2;
                    newTail = cur2;

                    // cur2 后移
                    cur2 = cur2Next;
                }
            } else if (cur == null) {
            // head 链表先遍历完了，将 head2 链表剩下的节点直接添加到新链表中即可
                newTail.next = cur2;
                break;
            } else if (cur2 == null) {
            // head2 链表先遍历完了，将 head 链表剩下的节点直接添加到新链表中即可
                newTail.next = cur;
                break;
            }
        }
        
        return newHead;
    }

    /**
     * 抽取 add 方法，简洁版代码
     * @param head 有序单链表 1 的头节点
     * @param head2 有序单链表 2 的头节点
     * @return 返回合并后的有序单链表的头节点
     */
    public static Node MergeTwoSortedList2(Node head, Node head2) {
        if (head.next == null && head2.next == null) {
            return null;
        } else if (head.next == null && head2.next != null) {
            return head2;
        } else if (head.next != null && head2.next == null){
            return head;
        }

        Node newHead = new Node(0, "", "");
        Node newTail = newHead;
        Node cur = head.next;
        Node cur2 = head2.next;
        Node curNext;
        Node cur2Next;

        while (true) {
            if (cur != null && cur2 != null) {
                if (cur.no < cur2.no) {
                    curNext = cur.next;
                    newTail = add(cur, newTail);
                    cur = curNext;
                } else if (cur.no > cur2.no) {
                    cur2Next = cur2.next;
                    newTail = add(cur2, newTail);
                    cur2 = cur2Next;
                } else if (cur.no == cur2.no) {
                    curNext = cur.next;
                    newTail = add(cur, newTail);
                    cur = curNext;
                    cur2Next = cur2.next;
                    newTail = add(cur2, newTail);
                    cur2 = cur2Next;
                }
            } else if (cur == null) {
                newTail.next = cur2;
                break;
            } else if (cur2 == null) {
                newTail.next = cur;
                break;
            }
        }

        return newHead;
    }

    /**
     * 将当前节点 cur 添加到链表的末尾，即尾节点 tail 的后面
     * @param cur 要添加的节点
     * @param tail 链表的尾节点
     * @return 更新链表的尾节点
     */
    /**
     * 
     * @param cur
     * @param tail
     * @return
     */
    public static Node add(Node cur, Node tail) {
        cur.next = tail.next;
        tail.next = cur;
        tail = cur;
        return tail;
    }
    
}

class SortedSingleLinkedList {

    /**
     * 初始化链表的头节点，不存放数据
     */
    private Node head = new Node(0, "", "");

    public Node getHead() {
        return head;
    }

    /**
     * 将要添加的节点按照编号从小到大的顺序插入到单链表中
     * 思路：
     * 1. 找到要插入位置的下一个元素，比较编号，如果要添加节点的编号小于下一个元素的编号，则插入，否则继续遍历
     * 2. 如果该编号存在，则添加失败，给出提示信息。
     * @param node 要添加的节点
     */
    public void addByOrder(Node node) {
        // 借助一个辅助指针遍历单链表寻找
        Node temp = head;
        // 标记编号是否存在，默认值为不存在
        boolean flag = false;

        while (true) {
            if (temp.next != null) {
                // 找到位置了
                if (node.no < temp.next.no) {
                    break;
                } else if (node.no == temp.next.no) {
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
            System.out.println("要添加英雄的编号 " + node.no + " 已经存在");
        } else {
            // 插入成功
            node.next = temp.next;
            temp.next = node;
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
        Node temp = head.next;

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
class Node {
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
     *
     */
    public Node next;

    public Node(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;

        // 没有初始化 next，则 next 默认为 null
    }

    public Node() {
    }

    /**
     * 重写 toString 方法，不打印 next，因为会嵌套打印
     * @return 打印 HeroNode
     */
    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
