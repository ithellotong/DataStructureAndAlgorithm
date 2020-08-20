package com.hellotong.recursion;

/**
 * 八皇后问题
 * 八皇后问题，是一个古老而著名的问题，是回溯算法的典型案例。该问题是国际西洋棋棋手马克斯·贝瑟尔于
 * 1848 年提出：在 8×8 格的国际象棋上摆放八个皇后，使其不能互相攻击，即：任意两个皇后都不能处于同一行、
 * 同一列或同一斜线上，问有多少种摆法(92)。
 * 
 * 解题思路：递归 + 回溯
 * 1)第一个皇后先放第一行第一列
 * 2)第二个皇后放在第二行第一列、然后判断是否 OK， 如果不 OK，继续放在第二列、第三列、依次把所有列都
 * 放完，找到一个合适
 * 3)继续第三个皇后，还是第一列、第二列……直到第 8 个皇后也能放在一个不冲突的位置，算是找到了一个正确
 * 解
 * 4)当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放到第一列的所有正确解，
 * 全部得到.
 * 5)然后回头继续第一个皇后放第二列，后面继续循环执行 1,2,3,4 的步骤
 *
 * @author hellotong
 * @date 2020-08-20 12:26
 */
public class Queue8 {

    /**
     * 表示八个皇后
     */
    int max = 8;
    /**
     * 存放的是每个皇后放的位置，下标表示行，值表示列，遍历得到的就是一个八皇后问题的一种解法
     */
    int[] array = new int[max];
    /**
     * 记录一共有多少种解法
     */
    static int count = 0;
    /**
     * 记录一共进行了多少次冲突判断
     */
    static int judgeCount = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        // 放入第一个皇后，之后的皇后会按照不冲突的条件自动放入，直到全部放入
        // 同时回溯找出所有满足条件的解法
        queue8.check(0);
        System.out.println("一共有 " + count + " 种解法");
        System.out.println("一共进行冲突判断 " + judgeCount + " 次");
    }

    /**
     * 放置第 n 个皇后
     *
     * @param n
     */
    private void check(int n) {
        // 如果 n = 8 表示放置第 9 个皇后，而总共就 8 个皇后
        // 那意味着一种解法就形成了，我们打印输出，同时结束返回
        if (n == max) {
            print();
            return;
        }

        // 放置第 n 个皇后
        // 每一行有 8 个位置，都需要测试，递归 + 回溯，这一行测试完，会回溯到上一行继续测试
        for (int i = 0; i < max; i++) {
            // 每个皇后第一次先会放在第 1 个位置上，如果当前位置冲突，在后移
            // 代表把第 n 个皇后放在了第 i+1 列
            array[n] = i;
            // 如果不冲突，那就继续放下一个皇后，形成递归
            // if (judge(array[n])) {
            // 判断放置第 n 个皇后是否会和前 [0 - n-1] 个皇后冲突
            if (judge(n)) {
                check(n + 1);
            }

            // 如果冲突，循环继续
        }
    }

    /**
     * 判断放置第 n 个皇后是否会和前 [0 - n-1] 个皇后冲突
     *
     * @param n 第 n 个皇后，从 0 开始，0 表示第 1 个皇后
     * @return 不冲突返回 true，冲突返回 false
     */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            // 冲突 -> 同一行、同一列、同一斜线
            // array[i] 存放的就是列，i 表示行，也表示第 i + 1 个皇后
            // array[i] == array[n] 判断的是是否在同一列
            // Math.abs(n-i) == Math.abs(array[n] - array[i]) 是一个小算法，判断的是是否在同一斜线
            // 不需要判断是否在同一行，因为每次比较的是 n 和 i[0, n-1]，所以不可能相同
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印每一种解法 / 摆放位置
     */
    private void print() {
        count++;
        for (int i = 0; i < max; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}


