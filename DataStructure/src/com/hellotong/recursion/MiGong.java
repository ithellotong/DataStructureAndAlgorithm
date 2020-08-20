package com.hellotong.recursion;

/**
 * 使用递归解决迷宫问题
 * 
 * @author hellotong
 * @date 2020-08-20 10:56
 */
public class MiGong {
    public static void main(String[] args) {
        // 使用二位数组模拟地图
        int[][] map = new int[8][7];
        // 约定 1 为墙
        // 初始化上下两面墙 - 第 1 行和第 8 行
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        
        // 初始化左右两面墙 - 第 1 列和第 7 列
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        
        // 初始化挡板
        map[3][1] = 1;
        map[3][2] = 1;

        System.out.println("原始地图：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        
        setWay(map, 1, 1);
        System.out.println("小球走过并标识过的地图：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        
        
    }

    /**
     * 方法的作用：当前位置小球是否可以走通？
     * 使用递归回溯给小球找路
     * 约定：
     * 1. 0 表示没有走过，1 表示墙，2 表示通路，3 表示是死路
     * 2. 小球找路的策略：下 -> 右 -> 上 -> 左
     * @param map 地图
     * @param i 从哪一行开始找
     * @param j 从哪一列开始找
     * @return 如果当前位置可以走通就返回 true，否则返回 false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        // 如果已经到达终点就结束
        if (map[6][5] == 2) {
            return true;
        } else {
            // 表示当前位置没有走过，可以走
            if (map[i][j] == 0) {
                // 假定当前位置是通路
                map[i][j] = 2;
                // 向下走，如果走得通返回 true
                if (setWay(map, i + 1, j)) {
                    return true;
                } else if (setWay(map, i, j + 1)) {
                // 向右走，如果走得通返回 true
                    return true;
                } else if (setWay(map, i-1, j)) {
                // 向上走，如果走得通返回 true
                    return true;
                } else if (setWay(map, i, j - 1)) {
                // 向左走，如果走得通返回 true
                    return true;
                } else {
                // 上下左右都走不通说明当前位置是死路，将其置为 3
                    map[i][j] = 3;
                    return false;
                }
            } else {
            // map[i][j] == 1、2、3，
                // 1 是墙不能走，2 表示已经走过了的通路，3 是死路
                // 都不能走了，直接返回 false
                return false;
            }
        }
    }
}
