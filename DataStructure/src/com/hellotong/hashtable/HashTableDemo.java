package com.hellotong.hashtable;

import java.util.Scanner;

/**
 * 使用哈希表完成对雇员的管理
 * 
 * @author hellotong
 * @date 2020-08-27 18:31
 */
public class HashTableDemo {
    public static void main(String[] args) {
        // 初始化 7 条链表
        HashTable hashTable = new HashTable(7);

        Scanner scanner = new Scanner(System.in);
        
        // 创建一个菜单
        String key;
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("addByOrder:  按序添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("del:  删除雇员");
            System.out.println("exit: 退出程序");
            
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.print("输入 id：");
                    int id = scanner.nextInt();
                    System.out.print("输入姓名：");
                    String name = scanner.next();
                    hashTable.add(new Emp(id, name));
                    break;
                case "addByOrder":
                    System.out.print("输入 id：");
                    id = scanner.nextInt();
                    System.out.print("输入姓名：");
                    name = scanner.next();
                    hashTable.addByOrder(new Emp(id, name));
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.print("输入要查找的雇员 id：");
                    id = scanner.nextInt();
                    hashTable.findEmpById(id);
                    break;
                case "del":
                    System.out.print("输入要删除的雇员 id：");
                    id = scanner.nextInt();
                    hashTable.deleteEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}

/**
 * 哈希表，用于管理链表数组 / 多条链表
 */
class HashTable {
    /**
     * 链表数组
     */
    private EmpLinkedList[] empLinkedListArray;

    /**
     * 链表数组的大小
     */
    private int size;

    /**
     * 构造器
     * @param size 链表数组的大小
     */
    public HashTable(int size) {
        this.size = size;
        this.empLinkedListArray = new EmpLinkedList[size];
        
        // !!!注意：初始化完链表数组后，还要初始化每条链表，否则会出现空指针异常
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加雇员
     * @param emp 要添加的雇员
     */
    public void add(Emp emp) {
        // 调用散列函数，确定 emp 要添加到哪条链表上
        // 获取位置，即确定挂到哪条链表上
        int empLinkedListNO = hashFun(emp.id);
        // 添加
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    /**
     * 按照雇员 id 从小到大添加雇员
     * @param emp 要添加的雇员
     */
    public void addByOrder(Emp emp) {
        // 调用散列函数，确定 emp 要添加到哪条链表上
        // 获取位置，即确定挂到哪条链表上
        int empLinkedListNO = hashFun(emp.id);
        // 添加
        empLinkedListArray[empLinkedListNO].addByOrder(emp);
    }

    /**
     * 遍历哈希表
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    /**
     * 通过雇员 id 查找雇员，如果找到打印雇员信息，否则输出不存在信息
     * @param id 雇员 id
     */
    public void findEmpById(int id) {
        // 首先确定当前 id 在哪条链表上
        int empLinkedListNO = hashFun(id);
        Emp findEmp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (findEmp == null) {
            System.out.println("哈希表中不存在 id = " + id + " 的雇员");
        } else {
            System.out.println("在哈希表的第 " + (empLinkedListNO + 1) +  " 条链表中中找到了 id = " + id + " 的雇员");
        }
    }

    /**
     * 根据雇员 id 删除雇员，如果删除成功给出提示信息，否则输出不存在信息
     * @param id 要删除的雇员的 id
     */
    public void deleteEmpById(int id) {
        // 首先确定当前 id 在哪条链表上
        int empLinkedListNO = hashFun(id);
        boolean isDelete = empLinkedListArray[empLinkedListNO].deleteEmpById(id);
        if (isDelete) {
            System.out.println("在第 " + (empLinkedListNO + 1) + " 条链表上 id = " + id + " 的雇员，删除成功");
        } else {
            System.out.println("哈希表中不存在 id = " + id + " 的雇员，删除失败");
        }
    }

    /**
     * 根据员工的 id 确定要添加的位置，使用最简单的取模法来作为散列算法
     * @param id 要添加的雇员的 id
     * @return 要添加的位置
     */
    public int hashFun(int id) {
        return id % size;
    }
}

/**
 * 雇员链表，用于管理雇员
 */
class EmpLinkedList {
    /**
     * 链表头指针，指向第一个实际存在的元素（雇员），而不仅仅是一个标记
     */
    private Emp head;

    /**
     * 添加雇员到链表尾部
     * @param emp 要添加的雇员
     */
    public void add(Emp emp) {
        // 如果添加的是第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        
        // 如果添加的不是第一个雇员，则需要借助辅助指针找到链表的最后一个元素
        Emp curEmp = head;
        while (curEmp.next != null) {
            curEmp = curEmp.next;
        }
        
        // 循环结束，curEmp 指向的就是链表最后一个元素
        // 添加即可，因为 emp 的 next 默认为 null，所以一句话搞定
        curEmp.next = emp;
    }

    /**
     * 按照雇员 id 从小到大添加雇员
     * @param emp 要添加的雇员
     */
    public void addByOrder(Emp emp) {
        // 如果添加的是第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        
        // 如果添加的不是第一个雇员，则需要借助辅助指针判断要添加到哪个位置
        // 如果要添加的雇员 id 小于头节点的 id，则插入到链表第一个位置，使用头插法
        // head 不为空
        if (emp.id < head.id) {
            emp.next = head;
            head = emp;
            return;
        }
        
        // 如果 emp 要插入的位置不在第一个元素前，从第二个节点开始寻找
        Emp curEmpPre = head;
        Emp curEmp = head.next;
        while (curEmp != null) {
            // 插入成功，插入在 curEmp 的前面
            if (emp.id <= curEmp.id) {
                emp.next = curEmp;
                curEmpPre.next = emp;
                return;
            }

            curEmpPre = curEmpPre.next;
            curEmp = curEmp.next;
        }
        
        // 如果 while 循环中没插入成功，说明 emp.id 最大，则直接添加最链表末尾
        add(emp);
    }

    /**
     * 遍历链表
     * @param no 链表编号
     */
    public void list(int no) {
        // 链表为空
        if (head == null) {
            System.out.println("第 " + (no + 1) + " 条链表为空，无法遍历!");
            return;
        }
        
        // 链表上至少有一个元素
        System.out.print("第 " + (no + 1) + " 条链表信息为：");
        Emp curEmp = head;
        while (curEmp != null) {
            // 先输出再后移
            System.out.print("=> " + curEmp + "\t");
            curEmp = curEmp.next;
        }
        
        System.out.println();
    }

    /**
     * 通过雇员 id 查找雇员
     * @param id 雇员 id
     * @return 如果找到返回雇员，否则返回 null
     */
    public Emp findEmpById(int id) {
        Emp curEmp = head;
        boolean flag = false;
        while (curEmp != null) {
            if (curEmp.id == id) {
                // 说明找到了
                flag = true;
                break;
            }
            curEmp = curEmp.next;
        }
        
        // 找到了
        if (flag) {
            return curEmp;
        } else {
            // 没找到
            return null;
        }
    }

    /**
     * 根据雇员 id 删除雇员
     * @param id 要删除的雇员的 id
     * @return true or false
     */
    public boolean deleteEmpById(int id) {
        Emp findEmp = findEmpById(id);
        // 雇员不存在，删除失败
        if (findEmp == null) {
            return false;
        } else {
        // 删除成功
            // 如果要删除的雇员是链表的第一个元素
            if (head.id == id) {
                head = null;
            } else {
                // 如果要删除的雇员不是链表的第一个元素
                Emp curEmp = head.next;
                Emp curEmpPre = head;
                while (curEmp != null) {
                    // 找到了要删除的雇员
                    if (curEmp.id == id) {
                        // 删除 curEmp 节点
                        curEmpPre.next = curEmp.next;
                        break;
                    }

                    // 指针后移
                    curEmp = curEmp.next;
                    curEmpPre = curEmpPre.next;
                }
            }
            return true;
        }
    }
}

/**
 * 雇员类
 */
class Emp {
    /**
     * 雇员 id
     */
    public int id;
    
    /**
     * 雇员姓名
     */
    public String name;
    
    /**
     * 指向下一个雇员，默认为 null
     */
    public Emp next;

    /**
     * 构造器
     * @param id 雇员 id
     * @param name 雇员姓名
     */
    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
