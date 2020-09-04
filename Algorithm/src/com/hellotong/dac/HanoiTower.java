package com.hellotong.dac;

/**
 * 分治算法
 * 解决汉诺塔问题
 * 
 * @author hellotong
 * @date 2020-09-04 12:09
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');
    }

    /**
     * 汉诺塔移动方法，借助塔 b 将塔 a 上的盘移动到塔 c
     * @param num 盘片的数量
     * @param a 塔 A
     * @param b 塔 B
     * @param c 塔 C
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        // 如果只有一个盘，则直接从 a 移动到 c
        if (num == 1) {
            System.out.println("第 1 个盘从 " + a + "->" + c);
        } else {
            // 如果盘片数 num >= 2，那么总是可以看作是两个盘
            // 1. 最下边的一个盘 2. 除最下边的一个盘之外上面的所有盘
            // 三个步骤
            // 1. 先把上面的所有盘借助塔 C 从 A -> B
            hanoiTower(num - 1, a, c, b);
            // 2. 把最下边的一个盘从 A -> C
            System.out.println("第 " + num + " 个盘从 " + a + "->" + c);
            // 3. 把 B 塔上的所有盘借助塔 A 从 B -> C
            hanoiTower(num - 1, b, a, c);
        }
    }
}
