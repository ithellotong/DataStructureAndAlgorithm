package com.hellotong.dynamic;

/**
 * 动态规划算法
 * 解决 01 背包问题，01 指的是背包中放入的物品不能重复
 * 
 * @author hellotong
 * @date 2020-09-04 17:38
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        // 每个商品的重量
        int[] w = {1, 4, 3};
        // 每个商品的价值
        int[] val = {1500, 3000, 2000};
        // 背包的可容纳的重量
        int m = 4;
        // 商品的数量
        int n = w.length;
        
        // 二维数组 v[i][j] 表示在前 i 个物品中能够装入容量为 j 的背包中的最大价值
        // n + 1 和 m + 1 是为了让 i 的下标和第几个商品对应，方便理解，第一行和第一列置 0
        int[][] v = new int[n + 1][m + 1];
        // 定义一个 path 数组，用于记录商品存入到背包的情况
        int[][] path = new int[n + 1][m + 1];
        
        // 初始化第一行和第一列为 0，其实这里不初始化默认也是 0，主要就是为了体现思路
        // 初始化第一列为 0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        // 初始化第一行为 0
        for (int j = 0; j < v[0].length; j++) {
            v[0][j] = 0;
        }
        
        // 使用动态规划算法求解背包问题
        // i 和 j 直接从下标为 1 开始，因为第一行和第一列都是 0
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                // 当准备加入新增的商品的容量大于 当前背包的容量时，就直接使用上一个单元格的装入策略
                // 因为这里下标从 1 开始，所以 i 需要减 1 与数组 w 和 val 下标对应 int[] w = {1, 4, 3};
                if (w[i - 1]> j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    //  w[i] <= j
                    // v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i -1 ]]);
                    // 为了记录商品存入背包的情况，如果使用 max 才并不知道具体是哪个才是最大值，所以这里改用 if-else 来处理
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i -1 ]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i -1 ]];
                        // 当前情况就是最优的情况，把当前情况标记为 1
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
        
        // 输出表格即二维数组 v
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
        
        // 遍历 path，且我们只需要最后放入的情况，即最优解，输出
        // 行的最大下标
        int i = path.length - 1;
        // 列的最大下标
        int j = path[0].length - 1;
        // path 数组从后往前找
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.println("第 " + i + " 个商品放入背包");
                // 背包容量减去商品 i 的容量，看剩余的背包容量可以存放的商品最优解
                j -= w[i - 1]; 
            }
            // 商品数量从大到小寻找
            i--; 
        }
    }
}
